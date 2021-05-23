package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppListTypeClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 5;
		
		EAppListTypeClient.execute(flag);
		
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : list(); break;
			case 2 : get(); break;
			case 3 : save(); break;
			case 4 : edit(); break;
			case 5 : del(); break;
		}
	}

	private static void del() {
		String url = DOMAIN + "/res-api/applisttype/del/20";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void edit() {
		String url = DOMAIN + "/res-api/applisttype/edit/20";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("className", "ceshi-AAAAAA");
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

	private static void save() {
		String url = DOMAIN + "/res-api/applisttype/save";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("className", "ceshi0000001");
		map.put("description", "啊啊啊啊02");
		map.put("enable", 1);
		
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
		String url = DOMAIN + "/res-api/applisttype/get/1";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  请求调用
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void list() {
		String url = "http://192.168.0.117:9500/res-api/applisttype/list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", 0);
		// map.put("resName", "大学生压力的来源");
		map.put("pageSize", 10);
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
