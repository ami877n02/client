package com.bbjob.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class AppResClient {
	
	public static void main(String[] args) {

		int flag = 1;
		
		AppResClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : get(); break;
			case 2 : add(); break;
			case 3 : del(); break;
		}
	}

	private static void del() {
		String url = "http://192.168.0.117:9500/res-api/appres/del/15";
		List<Integer> resList = new ArrayList<Integer>();
		resList.add(1000002);
		resList.add(1000005);
		try {
			
			// POST  请求调用
			String result = HttpClientTool.doPost(url, resList);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void add() {
		String url = "http://192.168.0.117:9500/res-api/appres/add/15";
		List<Integer> resList = new ArrayList<Integer>();
		resList.add(1000002);
		resList.add(1000005);
		resList.add(1000008);
		resList.add(1000010);
		
		try {
			// POST 请求调用
			String result = HttpClientTool.doPost(url, resList);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void get() {
		String url = "http://192.168.0.117:9500/res-api/appinfo/get/15";
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
