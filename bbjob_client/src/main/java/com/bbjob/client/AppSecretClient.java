package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class AppSecretClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		AppSecretClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : reset(); break;
			case 3 : appkey(); break;
		}
	}

	private static void appkey() {
		String url = DOMAIN + "/res-api/app/appkey/1";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void reset() {
		String url = DOMAIN + "/res-api/app/reset/1";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void get() {
		String url = DOMAIN + "/res-api/app/get/1";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
