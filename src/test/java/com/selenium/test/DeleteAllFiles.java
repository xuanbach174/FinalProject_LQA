package com.selenium.test;

import java.io.File;

public class DeleteAllFiles {
	String path = "C:\\Users\\bachnx\\Documents\\Downloads\\changeDownloadPath";
	File file  = new File(path);
	String[] childFiles = file.list();
	
	
	public void delete () {
//		System.out.println(childFiles);
		for (String childFilePath :  childFiles) {
//            System.out.println(file);
			File delFile = new File (path+"\\"+childFilePath);
			delFile.delete();
			System.out.println("Delete thanh cong file "+delFile);
        }
	}
	
	public void isExist () {
		if (childFiles==null) {
			System.out.println("Download khong thanh cong");
		}
		else {
			System.out.println("Download thanh cong roi");
		}
	}
	
}
