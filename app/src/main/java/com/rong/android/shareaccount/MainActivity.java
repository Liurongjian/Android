package com.rong.android.shareaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arms.rong.router.IRouter;
import com.rong.android.shareaccount.di.component.DaggerActivityComponent;
import com.rong.common.arms.mvp.BaseActivity;
import com.rong.common.utils.PermissionUtil;
import com.rong.common.utils.Toasts;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

	@Inject
	IRouter router;

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
				PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
					@Override
					public void onRequestPermissionSuccess() {
						Toasts.shortSuccess(MainActivity.this, "成功了");
						Intent intent = router.findIntent(MainActivity.this, "/module/a/main", null);
						startActivity(intent);
					}
				}, MainActivity.this, MainActivity.this);
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
