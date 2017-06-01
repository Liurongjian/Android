package com.rong.android.shareaccount;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rong.android.shareaccount.di.component.DaggerActivityComponent;
import com.rong.android.shareaccount.pojo.User;
import com.rong.common.arms.lifecycle.AppManager;
import com.rong.common.arms.mvp.BaseActivity;
import com.rong.common.utils.DevUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

public class SecondActivity extends BaseActivity {

	@Inject
	User user;
	@Inject
	EventBus eventBus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		BaseApplication application = (BaseApplication) getApplication();
		DaggerActivityComponent.builder().appComponent(application.getAppComponent()).build().inject(this);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();

				DevUtil.d("lrj ", "sec name = " + user.getName() + "@" + user);
				Message exitApp = Message.obtain();
				exitApp.what = AppManager.APP_EXIT;

				eventBus.post(exitApp, AppManager.APPMANAGER_MESSAGE);
			}
		});
	}

}
