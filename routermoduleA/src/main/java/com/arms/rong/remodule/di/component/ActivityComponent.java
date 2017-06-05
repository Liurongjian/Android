package com.arms.rong.remodule.di.component;

import com.arms.rong.remodule.AModuleMainActivity;
import com.arms.rong.remodule.di.module.ActivityModule;
import com.arms.rong.remodule.di.scope.PreActivity;
import com.rong.common.di.component.AppComponent;

import dagger.Component;

/**
 * Created by rong on 2017/5/22.
 */

@PreActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	void inject(AModuleMainActivity main);
}
