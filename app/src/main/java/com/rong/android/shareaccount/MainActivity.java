package com.rong.android.shareaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rong.android.shareaccount.di.component.DaggerActivityComponent;
import com.rong.android.shareaccount.http.UserService;
import com.rong.common.arms.mvp.BaseActivity;
import com.rong.common.utils.PermissionUtil;
import com.rong.common.utils.Toasts;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

	@Inject
	UserService userService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

//				String key = "2fc4d10375506a529265f447b0d1fa6d";
//				userService.histDay(1, 10, 1, key)
//						.subscribeOn(Schedulers.io())
//						.observeOn(AndroidSchedulers.mainThread())
//						.subscribe(new Consumer<ResponseBody<List<HistResult>>>() {
//							@Override
//							public void accept(@NonNull ResponseBody<List<HistResult>> listResponseBody) throws Exception {
//								Gson gson = new Gson();
//								DevUtil.d("lrj", listResponseBody == null ? "null" : gson.toJson(
//										listResponseBody.getResult().get(0)));
//							}
//						});

//				DevUtil.d("lrj", "sec name = " + user.getName() + "@" + user);
//
//				startActivity(new Intent(MainActivity.this, SecondActivity.class));

				PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
					@Override
					public void onRequestPermissionSuccess() {
						Toasts.shortSuccess(MainActivity.this, "成功了");
						startActivity(new Intent(MainActivity.this, SecondActivity.class));
					}
				}, new RxPermissions(MainActivity.this), MainActivity.this);
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
