package org.cch.jobs;

public class AllPushMessageService {

	public void enforceAllPush(AllPushMessage allPushMessage) {
		System.out.println("#### id is : " + String.valueOf(allPushMessage.getPush_id()));
		System.out.println("#### cron is : " + allPushMessage.getCronExpression());
	}

}
