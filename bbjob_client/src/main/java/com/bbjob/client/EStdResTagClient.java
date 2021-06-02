package com.bbjob.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EStdResTagClient {
	
	private static final String DOMAIN = "http://101.201.81.238:9500";
	
	public static void main(String[] args) {

		int flag = 1;
		
		EStdResTagClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : save(); break;
			
		}
	}

	private static void save() {
		String url = DOMAIN + "/res-api/stdrestag/add/1000002";
		
		List<Integer> tagIds = new ArrayList<Integer>();	
		tagIds.add(3);
		tagIds.add(4);
		
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
		String url = DOMAIN + "/res-api/stdrestag/get/1000002";
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
