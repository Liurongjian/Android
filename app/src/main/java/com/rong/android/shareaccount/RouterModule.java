package com.rong.android.shareaccount;

import android.content.Intent;

import com.arms.rong.router.Path;
import com.arms.rong.router.Target;

/**
 * Created by rong on 2017/6/5.
 */

@Path("/main/")
public interface RouterModule {

	@Path("first")
	@Target(MainActivity.class)
	Intent secondActivity();
}
