package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EResItemClient {
	
	public static void main(String[] args) {

		int flag = 1;
		
		EResItemClient.execute(flag);
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
		String url = "http://192.168.0.117:9500/res-api/resitem/del/42828";
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
		String url = "http://192.168.0.117:9500/res-api/resitem/edit/42828";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("realName", "ceshi-AAAAAA.mp4");
		map.put("eResourceId", 1038133);
		
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
		String url = "http://192.168.0.117:9500/res-api/resitem/save";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("realName", "测试来源.mp4");
		map.put("fileName", "测试来源");
		map.put("filePath", "/home/resource/测试来源.mp4");
		map.put("suffix", "mp4");
		map.put("eResourceId", 1038133);
		
		
		
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
		String url = "http://192.168.0.117:9500/res-api/resitem/get/669";
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
		String url = "http://192.168.0.117:9500/res-api/resitem/list";
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
