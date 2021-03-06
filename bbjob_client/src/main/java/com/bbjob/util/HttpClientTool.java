package com.bbjob.util;

import java.io.File;
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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpClientTool {
	
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";
	private static final String APPKEY = "4A27A728";
	private static final String APPSECRET = "61fef93eb7d6";
	
	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(100000).setSocketTimeout(100000).build();
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
	
	public static String doPost(String url, File file, Map<String, Object> params) throws IOException {
	
		return doPost(url, file, params, CHARSET);
	}
	
	private static String doPost(String url, File file, Map<String, Object> params, String charset2) throws IOException {
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
		httpPost.setHeader("ip", "");
		// httpPost.addHeader("Content-type","multipart/form-data");  
		// httpPost.setHeader("Accept", "application/json");
		
		CloseableHttpResponse response = null;
	    String result = null;
	
	    try {
	    	/*
	    	MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
	    	mEntityBuilder.addBinaryBody("file", file);
	    	httpPost.setEntity(mEntityBuilder.build());
	    	*/
	    	
	    	MultipartEntity reqEntity = new MultipartEntity();
	    	FileBody bin = new FileBody(file);
	    	reqEntity.addPart("file", bin);
	    	if (params.get("name") != null) {
	    		reqEntity.addPart("name", new StringBody(params.get("name").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	if (params.get("total") != null ) {
	    		reqEntity.addPart("total", new StringBody(params.get("total").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	if (params.get("index") != null) {
	    		reqEntity.addPart("index", new StringBody(params.get("index").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	if (params.get("fileMd5") != null) {
	    		reqEntity.addPart("fileMd5", new StringBody(params.get("fileMd5").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	if (params.get("md5") != null) {
	    		reqEntity.addPart("md5", new StringBody(params.get("md5").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	if (params.get("resourceid") != null) {
	    		reqEntity.addPart("resourceid", new StringBody(params.get("resourceid").toString(), Charset.forName(charset2)));
	    	}
	    	
	    	// reqEntity
	    	
	    	httpPost.setEntity(reqEntity);
	    	response = httpClient.execute(httpPost);
	    	
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("httpClient, error status code: " + statusCode);
			}
			HttpEntity entity = response.getEntity();
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
		httpPost.setHeader("ip", "");
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
	 * HTTP Get ????????
	 * @param url ????url????  ???????????
	 * @param params ??????????
	 * @param charset ????????
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
		httpGet.setHeader("ip", "");
		try{
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
				// ????url????
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
	 * HTTP POST ????????
	 * @param url ????url????
	 * @param params ????????
	 * @param charset ????????
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
		httpPost.setHeader("ip", "");
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
