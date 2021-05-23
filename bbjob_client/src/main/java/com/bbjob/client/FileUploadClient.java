package com.bbjob.client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.bbjob.entity.FileEntity;
import com.bbjob.util.FileUtil;
import com.bbjob.util.HttpClientTool;
import com.bbjob.util.Split;
import com.bbjob.util.SplitRunnable;

public class FileUploadClient {
	
	private static final String DOMAIN = "http://101.201.81.238:8888";
	private static final String PARTPATH = "G:/resource/part/";
	private static final Integer PARTLENGTH = 4 * 1024 * 1024;

	public static void main(String[] args) {

		int flag = 1;
		
		FileUploadClient.execute(flag);
	}

	private static void execute(int flag) {
		switch(flag) {
			case 1 : upload(); break;
		}
	}

	private static void upload() {
		
		File file = new File("G:/resource/阿达.zip");
		
		if (!file.exists()) {
			
			System.out.println("文件不存在");
			return;
		}
		
		String url = DOMAIN + "upload";
		Integer resourceid = 1038167;
		List<FileEntity> parts = splitBySize(file);
		
		if (!file.exists()) {
			
			System.out.println("file is not exists");
		} else {
			try {
				for (FileEntity entity : parts) {
					Map<String, Object> params = new HashMap<String, Object>();
					File partFile = new File(entity.getFilePath());
					params.put("name", entity.getName());
					params.put("total", entity.getTotal());
					params.put("index", entity.getIndex());
					params.put("fileMd5", entity.getFileMd5());
					params.put("md5", FileUtil.getMD5(partFile));
					params.put("resourceid", resourceid);
					
					String result = HttpClientTool.doPost(url, partFile, params);
					
					System.out.println(result);
				}
				
				// System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static List<FileEntity> splitBySize(File file) {
		List<FileEntity> parts = new ArrayList<FileEntity>();
		
		int count = (int) Math.ceil(file.length() / (double) PARTLENGTH);
		String fileMd5 = FileUtil.getMD5(file);
		
		// ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 12, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4 * 2));
		
		Split split = new Split(PARTLENGTH, file);
		FileEntity entity = null;
		for (int i = 0; i < count; i++) {
			entity = new FileEntity();
			entity.setIndex(i + 1);
			entity.setName(file.getName());
			entity.setTotal(count);
			entity.setFileMd5(fileMd5);
			
			String partFileName = PARTPATH + file.getName() + "." + (i+1) + ".part";
			entity.setFilePath(partFileName);			
			split.run(i * PARTLENGTH, partFileName);
			// threadPool.execute(new SplitRunnable(PARTLENGTH, i * PARTLENGTH, partFileName, file));
			
			parts.add(entity);
		}
		
		return parts;
	}
}
