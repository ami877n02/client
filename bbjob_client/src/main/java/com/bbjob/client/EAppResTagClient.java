package com.bbjob.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EAppResTagClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		EAppResTagClient.execute(flag);
		
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : add(); break;
		}
	}

	private static void add() {
		String url = DOMAIN + "/res-api/apprestag/add/1000002";
		List<Integer> tagIds = new ArrayList<Integer>();	
		tagIds.add(6);
		tagIds.add(15);
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, tagIds);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void get() {
		String url = DOMAIN + "/res-api/apprestag/get/1000002";
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
