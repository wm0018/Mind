package com.wu.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;


import net.sf.json.JSONObject;

@Component
public class HelpUtil {

	/**
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce,String token){
		String[] arr = new String[]{token,timestamp,nonce};
		//token,timestamp,nonce排序
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		//组成一个字符串
		for(int i = 0;i<arr.length;i++){
			sb.append(arr[i]);
		}
		//sha1加密
		try {
			/*MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] bresult = md.digest(sb.toString().getBytes());
			String sresult = byteToStr(bresult);*/
			String sresult = DigestUtils.sha1Hex(sb.toString());
			if(sresult!=null&&sresult.equalsIgnoreCase(signature)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static String httpPost(String url, List<NameValuePair> param){
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(param,Consts.UTF_8));
		JSONObject jsonObject = null;
		StringBuilder sb = new StringBuilder();
		try {
			HttpResponse resp = client.execute(post);
			HttpEntity entity = resp.getEntity();
			if(entity!=null) {
				InputStream is = entity.getContent();
				InputStreamReader inputStreamReader = new InputStreamReader(is,"utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String str = null;
				while((str=bufferedReader.readLine())!=null){
					sb.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				is.close();
				is=null;
//				jsonObject = JSONObject.fromObject(sb.toString());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("key", "6d4a57da1487477791eaef48f1e8f707"));
		list.add(new BasicNameValuePair("info", "红烧排骨"));
		list.add(new BasicNameValuePair("userid", "123"));
		String ret = httpPost("http://www.tuling123.com/openapi/api", list);
//		System.out.println(ret.toString());
	}
}
