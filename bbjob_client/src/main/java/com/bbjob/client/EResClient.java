package com.bbjob.client;

import java.util.HashMap;
import java.util.Map;

import com.bbjob.util.HttpClientTool;

public class EResClient {
	
	public static void main(String[] args) {

		int flag = 1;
		
		EResClient.execute(flag);
		
		// EResController.java
		/* 1.0  ----- -- --  ��ȡ��Դ�б�   list  POST
		String url = "http://192.168.0.117:9500/solr-api/resource/list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", 0);
		// map.put("resName", "��ѧ��ѹ������Դ");
		map.put("pageSize", 10);
		*/
		
		/* 2.0  ----- -- --  ��ѯ��Դ����   get/{id}  GET
		String url = "http://192.168.0.117:9500/res-api/res/get/1038041";
		Map<String, Object> map = new HashMap<String, Object>();
		*/
		
		/* 3.0  ----- -- --  ������Դ   add  POST 
		String url = "http://192.168.0.117:9500/res-api/res/add";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resName", "ceshi0000002");
		map.put("description", "��������02");
		*/
		/*
		try {
			// POST �������
			String result = HttpClientTool.doPost(url, map);
			
			// GET  �������
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
			case 3 : add(); break;
			case 4 : edit(); break;
			case 5 : del(); break;
		}
	}

	private static void del() {
		String url = "http://192.168.0.117:9500/res-api/res/del/1038134";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  �������
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void edit() {
		String url = "http://192.168.0.117:9500/res-api/res/edit/1038134";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resName", "ceshi-AAAAAA");
		map.put("description", "A20162836");
		
		try {
			// POST �������
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void add() {
		String url = "http://192.168.0.117:9500/res-api/res/add";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resName", "ceshi0000002");
		map.put("description", "��������02");
		
		try {
			// POST �������
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void get() {
		String url = "http://192.168.0.117:9500/res-api/res/get/1038041";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			// GET  �������
			String result = HttpClientTool.doGet(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void list() {
		String url = "http://192.168.0.117:9500/solr-api/resource/list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", 0);
		// map.put("resName", "��ѧ��ѹ������Դ");
		map.put("pageSize", 10);
		
		try {
			// POST �������
			String result = HttpClientTool.doPost(url, map);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
