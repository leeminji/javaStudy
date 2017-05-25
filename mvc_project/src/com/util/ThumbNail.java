package com.util;

import java.awt.Image;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

public class ThumbNail {
	private String path; //경로
	private String oriFile;
	private String thumbFile;
	private int width;
	private int height;
	
	public ThumbNail(){};
	public ThumbNail(String path, String thumb_path, String oriFile, int width, int height) {
		this.path = path;
		this.oriFile = path+oriFile;
		this.thumbFile = thumb_path+oriFile;
		this.width = width;
		this.height = height;
	}
	
	public String getOriFile() {
		return oriFile;
	}
	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}
	public String getThumbFile() {
		return thumbFile;
	}
	public void setThumbFile(String thumbFile) {
		this.thumbFile = thumbFile;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	//썸네일 생성
	public void createThumbnail(){
		try{
		   // 0 : 소스파일
		   // 1 : 생성될 썸네일 파일
		   // 2 : 썸네일 파일의 Width
		   // 3 : 썸네일 파일의 Height
		   // 4 : 비율고정여부(true/false)
			Image thumbnail = JimiUtils.getThumbnail(oriFile, width, height, Jimi.IN_MEMORY);
			Jimi.putImage(thumbnail, thumbFile);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
