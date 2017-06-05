package com.rong.android.shareaccount.di.component;

import com.rong.android.shareaccount.MainActivity;
import com.rong.android.shareaccount.di.module.ActivityModule;
import com.rong.android.shareaccount.di.scope.PreActivity;
import com.rong.common.di.component.AppComponent;

import dagger.Component;

/**
 * Created by rong on 2017/5/22.
 */

@PreActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	void inject(MainActivity main);
}
