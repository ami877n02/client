package com.bbjob.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class Split {
	
    private int byteSize;
    // String partFileName;
    private File originFile;
    // private int startPos;
	
    public Split(Integer byteSize, File originFile) {
    	// this.startPos = startPos;
    	this.byteSize = byteSize;
    	// this.partFileName = partFileName;
    	this.originFile = originFile;
    }

	public void run(Integer startPos, String partFileName) {
		RandomAccessFile rFile;
		OutputStream os;
		
		try {
			rFile = new RandomAccessFile(originFile, "r");
            byte[] b = new byte[byteSize];
            rFile.seek(startPos);// 移动指针到每“段”开头
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
