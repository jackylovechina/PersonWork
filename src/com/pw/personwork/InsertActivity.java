package com.pw.personwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.pw.internettask.PicDownTask;
import com.pw.internettask.PicTaskByHttpClient;
import com.pw.internettask.PicUtils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class InsertActivity extends Activity {
	private File file = null;
	private ImageView iv_subpic, iv_downpic;
	private static int SELECT_PIC_KITKAT = 4;
	private static int SELECT_PIC = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		iv_subpic = (ImageView) findViewById(R.id.iv_subpic);
		iv_downpic = (ImageView) findViewById(R.id.iv_downpic);

	}

	public void loadPicClick(View v) {
		/*
		 * Intent intent = new Intent(); // 开启Pictures画面Type设定为image /
		 * intent.setType("image/*"); // 使用Intent.ACTION_GET_CONTENT这个Action
		 * intent.setAction(Intent.ACTION_GET_CONTENT); // 取得相片后返回本画面
		 * startActivityForResult(intent, 1);
		 */
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);// ACTION_OPEN_DOCUMENT
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/jpeg");
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			startActivityForResult(intent, SELECT_PIC_KITKAT);
		} else {
			startActivityForResult(intent, SELECT_PIC);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			// Log.d("MyLog",
			// "resultCode:"+resultCode+",requestCode:"+requestCode);
			Uri uri = data.getData();

			String fileStr = PicUtils.getPath(InsertActivity.this, uri);

			file = new File(fileStr);

			ContentResolver cr = this.getContentResolver();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
				// ImageView imageView = (ImageView) findViewById(R.id.iv01);
				/* 将Bitmap设定到ImageView */
				iv_subpic.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				Log.d("MyLog", e.getMessage(), e);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void subPicClick(View v) {

		new Thread(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				/*
				 * String s = PicTaskByHttpURLConnection.uploadFile(file);
				 * Log.d("MyLog", s);
				 */
				PicTaskByHttpClient.uploadFile(file);

			}
		}).start();

	}

	public void uploadPicClick(View v) {
		String fileName="51cc1e8433b4f2aefeb3ef2402eea9f9.jpg";
		PicDownTask pdt=new PicDownTask(fileName, iv_downpic);
		pdt.execute("");
	}

}
