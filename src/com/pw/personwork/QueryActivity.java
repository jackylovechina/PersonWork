package com.pw.personwork;

import com.pw.internettask.QueryTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class QueryActivity extends Activity {
	private EditText name, passWord;
	private Button sub;
	private String nameStr, passWordStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		name = (EditText) findViewById(R.id.name);
		passWord = (EditText) findViewById(R.id.pword);
		sub = (Button) findViewById(R.id.sub);
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				switch (v.getId()) {
				case R.id.sub:

					new Thread(new Runnable() {

						@Override
						public void run() {
							nameStr = name.getText().toString();
							passWordStr = passWord.getText().toString();
							new QueryTask(nameStr, passWordStr).SubOrder();

						}
					}).start();

					break;

				default:
					break;
				}

			}
		});
	}

}
