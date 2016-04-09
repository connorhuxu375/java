package com.arock.create;

import java.io.File;

public class DoBakFile {
	static String bakDir = "E:/生成文件备份/";
	public static void bakAll(){
		Config config = new Config();
		String dirs[] = {config.javaOutputFileRootDir,config.jspOutputRootDir};
		for (int i = 0; i < dirs.length; i++) {
			File file = new File(dirs[i]);
			bakOne(file);
		}
	}
	
	public static void bakOne(File file){
		if(file.isDirectory()){
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(files[i].isDirectory()){
					bakOne(files[i]);
				}else{
					String fileName = files[i].getName();
					if(fileName.matches(".*\\.((jsp)|(java)|(xml)){1}[0-9]{14}$")){
						boolean move = files[i].renameTo(new File(bakDir+fileName));
						if(move){
							System.out.println("备份成功:"+bakDir+fileName);
						}
					}
				}
			}
		}
	}
}
