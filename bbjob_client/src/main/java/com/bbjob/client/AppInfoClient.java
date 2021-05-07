package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class AppInfoClient {
	
	public static void main(String[] args) {

		int flag = 3;
		
		AppInfoClient.execute(flag);
		
		// EResController.java
		/* 1.0  ----- -- --  获取资源列表   list  POST
		String url = "http://192.168.0.117:9500/solr-api/resource/list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", 0);
		// map.put("resName", "大学生压力的来源");
		map.put("pageSize", 10);
		*/
		
		/* 2.0  ----- -- --  查询资源详情   get/{id}  GET
		String url = "http://192.168.0.117:9500/res-api/res/get/1038041";
		Map<String, Object> map = new HashMap<String, Object>();
		*/
		
		/* 3.0  ----- -- --  新增资源   add  POST 
		String url = "http://192.168.0.117:9500/res-api/res/add";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resName", "ceshi0000002");
		map.put("description", "啊啊啊啊02");
		*/
		/*
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, map);
			
			// GET  请求调用
			// String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
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
		String url = "http://192.168.0.117:9500/res-api/res/appinfo/14";
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
		String url = "http://192.168.0.117:9500/res-api/appinfo/edit/14";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appName", "ceshi-AAAAAA");
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
		String url = "http://192.168.0.117:9500/res-api/appinfo/save";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appName", "ceshi0000001");
		map.put("description", "啊啊啊啊02");
		
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
		String url = "http://192.168.0.117:9500/res-api/appinfo/get/14";
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
		String url = "http://192.168.0.117:9500/res-api/appinfo/list";
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
