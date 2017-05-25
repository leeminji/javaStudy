package com.util;

public class SearchUtil {
	private String sfl;
	private String stx;
	
	public SearchUtil(String sfl, String stx){
		this.sfl = sfl;
		this.stx = stx;
	}
	
	public String getSfl() {
		return sfl;
	}
	public void setSfl(String sfl) {
		this.sfl = sfl;
	}
	public String getStx() {
		return stx;
	}
	public void setStx(String stx) {
		this.stx = stx;
	}
	
	@Override
	public String toString() {
		return "&sfl=" + sfl + "&stx=" + stx;
	}
	
	
}
