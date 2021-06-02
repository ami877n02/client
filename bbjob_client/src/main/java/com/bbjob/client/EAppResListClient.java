package com.bbjob.client;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppResListClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		EAppResListClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : list(); break;
			case 2 : get(); break;
			case 3 : save(); break;
			case 4 : edit(); break;
			case 5 : del(); break;
			case 6 : uploadCoverImg(); break;
			
		}
	}

	private static void uploadCoverImg() {
		String url = DOMAIN + "/res-api/appreslist/upload";
		Map<String, Object> map = new HashMap<String, Object>();
		
		File picFile = new File("G:/resource/1042.gif");
		
		try {
			
			// POST  请求调用
			String result = HttpClientTool.doPost(url, picFile, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void del() {
		String url = DOMAIN + "/res-api/appreslist/del/5";
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
		String url = DOMAIN + "/res-api/appreslist/edit/4";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listName", "ceshi-AAAAAA");
		
		// map.put("description", "A20162836");
		
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
		String url = DOMAIN + "/res-api/appreslist/save";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listName", "ceshi0000001");
		map.put("meta", "ceshi");
		map.put("description", "ceshi0000001");
		map.put("classId", 8);
		map.put("appId", 13);
		
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
		String url = DOMAIN + "/res-api/appreslist/get/4";
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
		String url = DOMAIN + "/res-api/appreslist/list";
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
