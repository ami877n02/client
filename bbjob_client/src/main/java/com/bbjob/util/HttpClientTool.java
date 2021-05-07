package com.bbjob.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;








import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class HttpClientTool {
	
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";
	private static final String APPKEY = "1c294aa17db7e35e4edf6b607f20602ea72808a5";
	private static final String APPSECRET = "3544390FAD1D";
	
	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
	
	public static String doGet(String url, Map<String, Object> params) {
		
		return doGet(url, params, CHARSET);
	}
	
	public static String doPost(String url, Map<String, Object> params) throws IOException {
		
		return doPost(url, params, CHARSET);
	}

	public static String doPost(String url, List<Integer> params) throws IOException {
		
		return doPost(url, params, CHARSET);
	}
	
	private static String doPost(String url, List<Integer> params, String charset) throws IOException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		String curTime=new Date().getTime()+"";
		String nonce="abc12346";
		HttpPost httpPost=new HttpPost(url);
		httpPost.setHeader("AppKey", APPKEY);
		httpPost.setHeader("Nonce", nonce);
		httpPost.setHeader("CurTime", curTime);
		String checkSum=CheckSumBuilder.getCheckSum(APPSECRET, nonce, curTime);
		httpPost.setHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-type","application/json; charset=utf-8");  
		httpPost.setHeader("Accept", "application/json");
		
			
		String bodyStr = new Gson().toJson(params);
		httpPost.setEntity(new StringEntity(bodyStr, charset));
		
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("httpClient, error status code: " + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
		
		return null;
	}

	/**
	 * HTTP Get ��ȡ����
	 * @param url ����url��ַ  ?֮ǰ�ĵ�ַ
	 * @param params ����Ĳ���
	 * @param charset �����ʽ
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> params,
			String charset) {
		
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		String curTime=new Date().getTime()+"";
		String nonce="abc12346";
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("AppKey", APPKEY);
		httpGet.setHeader("Nonce", nonce);
		httpGet.setHeader("CurTime", curTime);
		String checkSum=CheckSumBuilder.getCheckSum(APPSECRET, nonce, curTime);
		httpGet.setHeader("CheckSum", checkSum);
		
		try{
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
				// ƴ��url����
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code:" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * HTTP POST ��ȡ����
	 * @param url ����url��ַ
	 * @param params �������
	 * @param charset �����ʽ
	 * @return
	 * @throws IOException 
	 */
	public static String doPost(String url, Map<String, Object> params,
			String charset) throws IOException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		String curTime=new Date().getTime()+"";
		String nonce="abc12346";
		HttpPost httpPost=new HttpPost(url);
		httpPost.setHeader("AppKey", APPKEY);
		httpPost.setHeader("Nonce", nonce);
		httpPost.setHeader("CurTime", curTime);
		String checkSum=CheckSumBuilder.getCheckSum(APPSECRET, nonce, curTime);
		httpPost.setHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-type","application/json; charset=utf-8");  
		httpPost.setHeader("Accept", "application/json");
		
		List<NameValuePair> pairs = null;
		if (params != null && !params.isEmpty()) {
			pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				Object value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
				}
			}
		}
		
		if (pairs != null && pairs.size() > 0) {
			String bodyStr = new Gson().toJson(params);
			httpPost.setEntity(new StringEntity(bodyStr, charset));
		} else {
			
			httpPost.setEntity(new StringEntity("{}", charset));
		}
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("httpClient, error status code: " + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
		
		return null;
	}
}