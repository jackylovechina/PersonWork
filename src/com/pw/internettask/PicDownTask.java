package com.pw.internettask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class PicDownTask extends AsyncTask<String, Integer, Bitmap> {
	private String fileName;
	private ImageView imageView;

	public PicDownTask(String fileName, ImageView imageView) {
		super();
		this.fileName = fileName;
		this.imageView = imageView;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		String urlStr = "http://192.168.40.128:8080/Test/upload/";
		urlStr = urlStr + fileName;

		InputStream is;
		Bitmap tmpBitmap = null;
		try {
			is = new URL(urlStr).openStream();
			tmpBitmap = BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return tmpBitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		imageView.setImageBitmap(result);
		super.onPostExecute(result);
	}

}
