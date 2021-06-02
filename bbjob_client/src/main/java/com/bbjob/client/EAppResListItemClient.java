package com.bbjob.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppResListItemClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 3;
		
		EAppResListItemClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : save(); break;
			case 3 : del(); break;
			
		}
	}

	private static void del() {
		String url = DOMAIN + "/res-api/appreslistitem/del/1";
		List<Integer> resIds = new ArrayList<Integer>();	
		resIds.add(3);
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doPost(url, resIds);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void save() {
		String url = DOMAIN + "/res-api/appreslistitem/add/3";
		
		List<Integer> resIds = new ArrayList<Integer>();	
		resIds.add(1000002);
		resIds.add(1000005);
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, resIds);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void get() {
		String url = DOMAIN + "/res-api/appreslistitem/get/3";
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
