package com.arms.rong.remodule;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.view.View;

import com.arms.rong.remodule.di.component.DaggerActivityComponent;
import com.arms.rong.router.IRouter;
import com.rong.common.arms.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

public class AModuleMainActivity extends BaseActivity {

	@Inject
	IRouter router;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmodule_main);
		DaggerActivityComponent.builder().appComponent(ApplicationDelegate.getInstance()
				.getAppComponent()).build().inject(this);
		findViewById(R.id.start_sec_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<Integer> ints = new ArrayList<>();
				ints.add(1);
				ints.add(2);
				Map<String, Object> params = new ArrayMap<>();
				params.put("int", 1);
				params.put("short", (short)2);
				params.put("char", 'd');
				params.put("byte", (byte)3);
				params.put("boolean", true);
				params.put("long", 4l);
				params.put("float", 5f);
				params.put("double", 6d);
				params.put("String", "ddd");
				params.put("CharSequence", "dddd");
				params.put("IntegerArrayList", ints);
				boolean[] booleen = new boolean[2];
				booleen[0] = true;
				booleen[1] = true;
				params.put("BooleanArray", booleen);

				double startTime = SystemClock.currentThreadTimeMillis();
				Intent intent = router.findIntent(AModuleMainActivity.this, "/module/b/main", params);
				System.out.println("cost time = " + (SystemClock.currentThreadTimeMillis() - startTime));
				startActivity(intent);
			}
		});
	}
}
