package org.driver.check.util;

import java.util.Random;
import java.util.UUID;

public class RandomDC {
	
	private static Random random = new Random();    

	public static int getRandomInt(int min, int max){
	  return random.nextInt(max - min + 1) + min;
	}
	
	public static int getRandomInt(int max){
		int min = 0;		
		return random.nextInt(max - min + 1) + min;
	}
	
	public static int getRandomInt(){
		int min = 0;
		int max = 1000;
		return random.nextInt(max - min + 1) + min;
	}
	
	public static String getUniqueId(){
		//return UUID.randomUUID().toString();
		
		// for testing use 1 - 500
		return ""+getRandomInt(1, 500);
	}
}
