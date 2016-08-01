package com.pw.internettask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class QueryTask {
	private String name;
	private String password;

	public QueryTask(String name, String password) {
		super();
		this.name = name;
		this.password = password;

	}

	public void SubOrder() {
		String urlStr = "http://192.168.40.128:8080/Test/inserttest.do";
		HttpClient httpCient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlStr);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("password", password));
		HttpResponse httpResponse = null;
		try {
			UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(params, "utf-8");
			httpPost.setEntity(urlEntity);
			httpResponse = httpCient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				//Log.d("MyLog", "1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
