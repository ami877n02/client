package com.bbjob.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class SplitRunnable implements Runnable {
	
    int byteSize;
    String partFileName;
    File originFile;
    int startPos;
	
    public SplitRunnable(int byteSize, int startPos, String partFileName, File originFile) {
    	this.startPos = startPos;
    	this.byteSize = byteSize;
    	this.partFileName = partFileName;
    	this.originFile = originFile;
    }

	public void run() {
		RandomAccessFile rFile;
		OutputStream os;
		
		try {
			rFile = new RandomAccessFile(originFile, "r");
            byte[] b = new byte[byteSize];
            rFile.seek(startPos);// �ƶ�ָ�뵽ÿ���Ρ���ͷ
            int s = rFile.read(b);
            os = new FileOutputStream(partFileName);
            os.write(b, 0, s);
            os.flush();
            os.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
