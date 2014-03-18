package edu.ucdavis.cs.mobile.cop.support; 

public class SleepState {

	private static boolean activeStatus = false;

	public static boolean isActive() {
		return activeStatus;
	}

	public static void setActive(boolean status) {
		activeStatus = status;
	}
}
