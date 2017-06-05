package com.arms.rong.router;

/**
 * Created by rong on 2017/6/2.
 */

public class ModuleTest {

	public static void main(String[] args) {

		IRouter router = new RouterImpl();
		router.inject(AModule.class);
	}
}
