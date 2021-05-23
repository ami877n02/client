package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class YxVideoClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		YxVideoClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : resitem(); break;
			case 3 : save(); break;
			case 4 : edit(); break;
			case 5 : delete(); break;
			case 6 : delbyresid(); break;
		}
	}

	private static void delbyresid() {
		String url = DOMAIN + "/res-api/video/delbyresid/42827";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void delete() {
		String url = DOMAIN + "/res-api/video/delete/28742";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void save() {
		String url = DOMAIN + "/res-api/video/save";
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("eResItemId", 42827);
		map.put("vid", 0);
		map.put("videoName", "诶诶诶诶诶");
		map.put("description", "测测测测测");
		map.put("typeName", "二维码平台");
		
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void resitem() {
		String url = DOMAIN + "/res-api/video/resitem/669";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void edit() {
		String url = DOMAIN + "/res-api/video/edit/28742";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("videoName", "ceshi-AAAAAA");
		map.put("description", "A20162836");
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void get() {
		String url = DOMAIN + "/res-api/video/get/211";
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
