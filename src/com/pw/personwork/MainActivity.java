package com.pw.personwork;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	private RadioGroup radioGroup;
	private RadioButton radioButton_query, radioButton_insert, radioButton_set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioButton_query = (RadioButton) findViewById(R.id.radioButton_query);
		radioButton_insert = (RadioButton) findViewById(R.id.radioButton_insert);
		radioButton_set = (RadioButton) findViewById(R.id.radioButton_set);

		Intent queryIntent = new Intent();
		queryIntent.setClass(MainActivity.this, QueryActivity.class);

		Intent insertIntent = new Intent();
		insertIntent.setClass(MainActivity.this, InsertActivity.class);

		Intent setIntent = new Intent();
		setIntent.setClass(MainActivity.this, SetActivity.class);

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("tag1").setIndicator("0").setContent(queryIntent));
		tabHost.addTab(tabHost.newTabSpec("tag2").setIndicator("1").setContent(insertIntent));
		tabHost.addTab(tabHost.newTabSpec("tag3").setIndicator("2").setContent(setIntent));

		CheckListener checkRadio = new CheckListener();
		radioGroup.setOnCheckedChangeListener(checkRadio);

	}

	public class CheckListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.radioButton_query:
				tabHost.setCurrentTab(0);
				break;
			case R.id.radioButton_insert:
				tabHost.setCurrentTab(1);
				break;
			case R.id.radioButton_set:
				tabHost.setCurrentTab(2);
				break;

			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
