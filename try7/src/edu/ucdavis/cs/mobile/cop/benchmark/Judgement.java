package edu.ucdavis.cs.mobile.cop.benchmark;

public class Judgement {
	public static final String isleep = "sleep";
	public static final String ieat = "eat";
	public static final String iwork = "work";
	
	/*
	 * 
	 * the status of sleeping descriped as 0
	 * as before, out = 1, work = 2
	 * 
	 */
	
	public static boolean icheck(String standard, String str){
		String[] tokens = str.split(" ");
		for (int i=0; i<tokens.length; i++){
			if(standard.equalsIgnoreCase(tokens[i])){
				return true;
			}
		}
		return false;
	}
	
	public static int check(String str){
		if(icheck(isleep, str))
			return 0;
		else if(icheck(ieat, str))
			return 1;
		else if(icheck(iwork, str))
			return 2;
		else
			return -1;
			
	}
	
}
