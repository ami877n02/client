package com.bbjob.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppResListGroupClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		EAppResListGroupClient.execute(flag);
		
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : list(); break;
			case 2 : get(); break;
			case 3 : add(); break;
			case 4 : edit(); break;
			case 5 : del(); break;
			case 6 : delByPid(); break;
		}
	}

	private static void delByPid() {
		String url = DOMAIN + "/res-api/appreslistgroup/del/6";
		List<Integer> resIds = new ArrayList<Integer>();
		resIds.add(10002);
		
		try {
			
			// POST  请求调用
			String result = HttpClientTool.doPost(url, resIds);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void del() {
		String url = DOMAIN + "/res-api/appreslistgroup/del/3";
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
		String url = DOMAIN + "/res-api/appreslistgroup/edit/4";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "string");
		
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
		String url = DOMAIN + "/res-api/appreslistgroup/save";
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> resList = new ArrayList<Integer>();
		resList.add(10002);
		map.put("level",1);
		map.put("name","测试章节-2");
		map.put("resListId",1);
		map.put("resList",resList);
		map.put("parentId", 4);
		
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
		String url = DOMAIN + "/res-api/appreslistgroup/get/3";
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
		String url = DOMAIN + "/res-api/appreslistgroup/list";
		Map<String, Object> map = new HashMap<String, Object>();
		
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
