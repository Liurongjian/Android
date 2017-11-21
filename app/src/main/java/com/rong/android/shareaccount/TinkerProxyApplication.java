package com.rong.android.shareaccount;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by rong on 2017/6/27.
 */

public class TinkerProxyApplication extends TinkerApplication {

	public TinkerProxyApplication() {
		super(ShareConstants.TINKER_ENABLE_ALL, "com.rong.android.shareaccount.BaseApplication",
				"com.tencent.tinker.loader.TinkerLoader", false);
	}
}
