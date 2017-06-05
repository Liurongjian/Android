package com.arms.rong.remodule;

import android.content.Intent;

import com.arms.rong.router.Path;
import com.arms.rong.router.Target;

/**
 * Created by rong on 2017/6/6.
 */

@Path("/module/a/")
public interface RouterModule {

	@Path("main")
	@Target(AModuleMainActivity.class)
	Intent mainActivity();
}