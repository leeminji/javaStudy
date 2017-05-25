package com.util;

import java.io.File;

public class DirListUtil {
	private String file_path;
	
	public DirListUtil(){
	}
	
	public DirListUtil(String file_path){
		this.file_path = file_path;
	}
	
	public String getFile_path() {
		return file_path;
	}
	
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public String[] getDirList(){
		File dir = new File(this.file_path);
		String [] file_list = dir.list();
		return file_list;
	}
}
