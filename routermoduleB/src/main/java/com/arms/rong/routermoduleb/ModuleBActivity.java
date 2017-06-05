package com.arms.rong.routermoduleb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arms.rong.router.IRouter;
import com.arms.rong.routermoduleb.di.component.DaggerActivityComponent;
import com.rong.common.utils.JsonUtil;

import javax.inject.Inject;

public class ModuleBActivity extends AppCompatActivity {


	@Inject
	IRouter router;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_module_b);
		DaggerActivityComponent.builder().appComponent(ApplicationDelegate.getInstance()
				.getAppComponent()).build().inject(this);
		readParams();

		findViewById(R.id.start_main).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = router.findIntent(ModuleBActivity.this, "/main/first", null);
				startActivity(intent);
			}
		});
	}

	private void readParams() {
		Intent intent = getIntent();
		System.out.println("int = " + intent.getIntExtra("int", 0));
		System.out.println("short = " + intent.getShortExtra("short", (short)0));
		System.out.println("char = " + intent.getCharExtra("char", '0'));
		System.out.println("byte = " + intent.getByteExtra("byte", (byte) 0));
		System.out.println("boolean = " + intent.getBooleanExtra("boolean", false));
		System.out.println("long = " + intent.getLongExtra("long", 0));
		System.out.println("float = " + intent.getFloatExtra("float", 0));
		System.out.println("double = " + intent.getDoubleExtra("double", 0));
		System.out.println("String = " + intent.getStringExtra("String"));
		System.out.println("CharSequence = " + intent.getCharSequenceExtra("CharSequence"));
		System.out.println("IntegerArrayList = " + JsonUtil.toJson(intent.getIntegerArrayListExtra("IntegerArrayList")));
		System.out.println("Serializable = " + JsonUtil.toJson(intent.getSerializableExtra("Serializable")));
		System.out.println("BooleanArray = " + JsonUtil.toJson(intent.getBooleanArrayExtra("BooleanArray")));

	}
}
