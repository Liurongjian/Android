package com.rong.android.shareaccount;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rong.android.shareaccount.di.component.DaggerActivityComponent;
import com.rong.android.shareaccount.pojo.User;
import com.rong.common.arms.mvp.BaseActivity;
import com.rong.common.utils.DevUtil;
import com.rong.common.utils.ProgressUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

public class SecondActivity extends BaseActivity {

	@Inject
	User user;
	@Inject
	EventBus eventBus;

	@Inject
	ProgressUtil progressUtil;

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

				progressUtil.showProgress(true);

			}
		});
	}

}
