package com.example.EAS.thread;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExecutor {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(20);
	
	public static void executeTask(AsyncExecutorTaskInterface task,Object object,String rules){
		executor.submit(new Runnable(){

			@Override
			public void run() {
				boolean success=task.run(object);
				if(success){
					return;
				}
				List<Integer> retyTimes=getRetryTimes(rules);
				for(Integer retyTime : retyTimes){
					try {
						Thread.sleep(retyTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					success=task.run(object);
					if(success){
						return;
					}
				}
			}
			
		});
	}
	
	private static List<Integer> getRetryTimes(String rules){
		List<Integer> retyTimes=new ArrayList<Integer>();
		if(StringUtils.isNotBlank(rules)){
			String[] arrStrs=rules.split(",");
			for(String arrStr : arrStrs){
				retyTimes.add(Integer.valueOf(arrStr));
			}
		}
		
		return retyTimes;
	}

}
