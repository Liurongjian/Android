package com.rong.common.utils;

import android.content.Context;
import android.webkit.WebView;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rong on 2017/6/27.
 */

@Singleton
public class CrashReportUtil {

	private String BUGLY_APP_ID = "07bf5d0d56";

	@Inject
	public CrashReportUtil() {

	}

	public void initCrashReport(Context context, boolean isDebug, String userId, Map<String, String> userExtraData) {

		Bugly.init(context, BUGLY_APP_ID, isDebug);
		CrashReport.setUserId(userId);

		if(userExtraData != null && !userExtraData.keySet().isEmpty()) {
			for(String key : userExtraData.keySet()) {
				CrashReport.putUserData(context, key, userExtraData.get(key));
			}
		}
	}


	public void  monitorWebView(WebView webView, boolean isDebug) {

		CrashReport.setJavascriptMonitor(webView, true);

	}

}
