package com.arms.rong.router;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rong on 2017/6/2.
 */

@Singleton
public class RouterImpl implements IRouter {

	private ArrayMap<String, Method> urls;
	private BundleExtrasDelegate extrasDelegate;
	private boolean isDebug = false;
	@Inject
	public RouterImpl() {
		urls = new ArrayMap<>();
		extrasDelegate = new BundleExtrasDelegate();
	}

	@Override
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	@Override
	public void inject(Class module) {

		Path basePathAn = (Path) module.getAnnotation(Path.class);
		String basePath = "";
		if(basePathAn != null) {
			basePath = basePathAn.value();
		}

		Method[] methods = module.getMethods();
		for(Method method : methods) {
			checkMethod(method);

			Path pathAn = method.getAnnotation(Path.class);
			String path = basePath + pathAn.value();
			Method existMethod = urls.get(path);
			if(existMethod != null) {
				if(existMethod != method) {
					throw new RouterRuntimeException("found duplicate urls! " + path);
				}
			} else {
				urls.put(path, method);
			}
		}
	}

	/**
	 * 检查接口定义的参数类型与注解是否符合
	 * @param method
	 * @return
	 */
	private boolean checkMethod(Method method) {
		Path path = method.getAnnotation(Path.class);
		if(path == null || TextUtils.isEmpty(path.value())) {
			throw new RouterRuntimeException("method's path can't be null");
		}
		Target target = method.getAnnotation(Target.class);
		if(target == null || target.value() == null) {
			throw new RouterRuntimeException("method's target can't be null");
		}

		Annotation[][] paramsAnnotas = method.getParameterAnnotations();
		Class[] types = method.getParameterTypes();

		if(paramsAnnotas.length != types.length) {
			throw new RouterRuntimeException("method's params must has @Query annotation");
		}

		for(Annotation[] paramAnno: paramsAnnotas) {

			boolean hasQueryAnno = false;
			for(Annotation anno : paramAnno) {
				if(anno instanceof Query) {
					hasQueryAnno = true;
					break;
				}
			}

			if(!hasQueryAnno) {
				throw new RouterRuntimeException("method's params must has @Query annotation");
			}
		}

		//判断是不是可传入bundle的类型

		return true;
	}

	private void readParams(Method method) {

		Annotation[][] paramsAnnotas = method.getParameterAnnotations();
		Type[] types = method.getGenericParameterTypes();

		for(int i = 0 ; i < types.length; i++) {

			String key = ((Query)paramsAnnotas[i][0]).value();
			System.out.println(key + ":" + types[i].toString());
		}
	}

	@Override
	public Intent findIntent(Context cont, String path, Map<String, Object> params) {
		//如果为空，则不查找
		if(TextUtils.isEmpty(path)) {
			return null;
		}

		Method method = urls.get(path);
		if(method == null) {
			return null;
		}
		Intent intent = getIntent(cont, method, params);
		return intent;
	}

	/**
	 * 根据接口的相关定义，找出相应的Intent;
	 * @param method
	 * @param params
	 * @return
	 */
	private Intent getIntent(Context cont, Method method, Map<String, Object> params) {
		Class target = method.getAnnotation(Target.class).value();
		Intent intent = new Intent();
		intent.setClassName(cont, target.getName());

		if(params != null && params.size() > 0) {
			//如果参数不为空
			Map<String, ParamInfo> paramsTypeMap = generateParamsTypeMap(method);
			Bundle bundle = generateExtras(paramsTypeMap, params);
			intent.putExtras(bundle);
		}
		return intent;
	}

	/**
	 * 将参数类型与注解绑定在一起
	 * @param method
	 * @return
	 */
	private Map<String, ParamInfo> generateParamsTypeMap(Method method) {
		ArrayMap<String, ParamInfo> paramsTypeMap = new ArrayMap<>();
		Annotation[][] anns = method.getParameterAnnotations();
		Type[] types = method.getGenericParameterTypes();
		for(int i = 0 ; i < anns.length; i++) {

			Annotation[] ans = anns[i];
			ParamInfo paramInfo = new ParamInfo();
			List<Annotation> paramAns = null;
			paramInfo.setType(types[i]);
			String key = null;
			for(int j = 0 ; j < ans.length; j++) {
				if(ans[j] instanceof Query ) {
					key = ((Query)ans[j]).value();
				} else {
					//如果不是Query 则缓存住，供以后使用。
					if(paramAns == null) {
						paramAns = new ArrayList<>();
						paramInfo.setAnnotations(paramAns);
					}
					paramAns.add(ans[j]);
				}
			}

			if(key != null) {
				paramsTypeMap.put(key, paramInfo);
			}
		}

		return paramsTypeMap;
	}

	private Bundle generateExtras(Map<String, ParamInfo> paramsTypeMap, Map<String, Object> params) {

		Bundle extrasBundle = new Bundle();
		for(String key : params.keySet()) {
			ParamInfo paramInfo = paramsTypeMap.get(key);
			//如果没有注册的参数，则不处理
			if(paramInfo == null) {
				continue;
			}
			@ExtraType.ValueExtraType int extraType = ExtraType.PUT_NO;
			if(paramInfo.getAnnotations() != null) {
				for (Annotation an : paramInfo.getAnnotations()) {
					if (an instanceof ExtraType) {
						extraType = ((ExtraType) an).value();
						break;
					}
				}
			}
			if(extraType == ExtraType.PUT_NO) {
				// 如果没有ExtraType的参数注解,则根据参数类型自己做判断
				extraType = extrasDelegate.findExtraTypeByParamClazz(paramInfo.getType());
			}
			if(extraType != ExtraType.PUT_NO) {
				extrasDelegate.putExtra(extraType, key, params.get(key), extrasBundle);
			}
			//填装过的参数，从map中去除
			paramsTypeMap.remove(paramInfo);
		}

		//查看未填装的参数列表中是否有NotNull的注解参数，如果有的话，则报错
		for(String key : paramsTypeMap.keySet()) {
			ParamInfo paramInfo = paramsTypeMap.get(key);
			if(paramInfo.getAnnotations() != null) {
				for (Annotation an : paramInfo.getAnnotations()) {
					if (an instanceof NotNull) {
						if(isDebug) {
							throw new RouterRuntimeException(key + " can't be null");
						} else {
							Log.e("router", key + " can't be null");
						}
					}
				}
			}
		}

		return extrasBundle;
	}

}
