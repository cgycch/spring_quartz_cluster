package org.cch.demo;

public class BaseStepTwoDemo {
	
	public static final String STATUS_S = "S";
	public static final String STATUS_F = "F";
	public static final String STATUS_W = "W";
	
	public static final String SITUATION_ALL_DONE = "all done";
	public static final String SITUATION_ONE_FAIL = "one fail";
	public static final String SITUATION_WAIT = "wait processs";

	public static void main(String[] args) {
		demo(SITUATION_WAIT);
		System.out.println();
		
		demo(SITUATION_ALL_DONE);
		System.out.println();
		
		demo(SITUATION_ONE_FAIL);
	}
	
	public static void demo(String message) {
		String currentStatus = tempJsonProcess(message);
		
		if(STATUS_S.equals(currentStatus)) {
			updateStatus(STATUS_S);
		}else if(STATUS_F.equals(currentStatus)){
			updateStatus(STATUS_F);
		}else {
			System.out.println("still need waitting for all has done");
		}
	}

	private static void updateStatus(String status) {
		System.out.println("update status on db to: " + status);
	}

	private static String tempJsonProcess(String message) {
		
		System.out.println("update tempJson on db when tempJsonProcess");
		
		String currentStatus = STATUS_W;
		if(SITUATION_ALL_DONE.equals(message)) {
			currentStatus = STATUS_S;
		}else if(SITUATION_ONE_FAIL.equals(message)) {
			currentStatus = STATUS_F;
		}
		return currentStatus;
	}

}
