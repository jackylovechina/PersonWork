package com.pw.internettask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class PicTaskByHttpClient {
	private static String url = "http://192.168.40.128:8080/Test/uploadbyclient.do";

	public static void uploadFile(File file) {

		try {
			FileBody bin = null;
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			if (file != null) {
				bin = new FileBody(file);
			}

			StringBody username = new StringBody("13696900475", ContentType.DEFAULT_TEXT);
			StringBody password = new StringBody("13696900475", ContentType.DEFAULT_TEXT);

			// MultipartEntity reqEntity = new MultipartEntity();
			MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
			reqEntity.addPart("username", username);
			reqEntity.addPart("password", password);
			reqEntity.addPart("data", bin);

			httppost.setEntity(reqEntity.build());
			//Log.d("MyLog","执行: " + httppost.getRequestLine());
			//发送post请求
			HttpResponse response = httpclient.execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){
				//Log.d("MyLog","getStatusLine:"+response.getStatusLine());
				//Log.d("MyLog", "send success!");
			}
			
			HttpEntity resEntity = response.getEntity();
			
			if (resEntity != null) {
				//Log.d("MyLog","返回长度: " + resEntity.getContentLength());
				//Log.d("MyLog","返回类型: " + resEntity.getContentType());
				
				InputStream in = resEntity.getContent();
				//Log.d("MyLog","in is " + in);
				// System.out.println(IoStreamUtil.getStringFromInputStream(in));
			}
			if (resEntity != null) {
				resEntity.consumeContent();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
