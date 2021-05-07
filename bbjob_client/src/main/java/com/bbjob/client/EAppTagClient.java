package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppTagClient {
	
	public static void main(String[] args) {

		int flag = 1;
		
		EAppTagClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : list(); break;
			case 2 : get(); break;
			case 3 : add(); break;
			case 4 : edit(); break;
			case 5 : del(); break;
		}
	}

	private static void del() {
		String url = "http://192.168.0.117:9500/res-api/apptag/del/17";
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
		String url = "http://192.168.0.117:9500/res-api/apptag/edit/17";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", "ceshi-AAAAAA");
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void add() {
		String url = "http://192.168.0.117:9500/res-api/apptag/add";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", "14");
		map.put("pId", 12);
		map.put("enable", 0);
		map.put("tagName", "测试5-1");
		
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
		String url = "http://192.168.0.117:9500/res-api/apptag/get/1";
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
		String url = "http://192.168.0.117:9500/res-api/apptag/list";
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("pageNum", 0);
		// map.put("resName", "大学生压力的来源");
		// map.put("pageSize", 10);
		
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
