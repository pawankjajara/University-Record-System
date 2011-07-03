package URS;

import java.util.Random;


public class RandomNumberGen{
		public static String randomRequestID;
		public static int randomNumber=0;
		
			
		public static String getRandomRequestID(){
			Random rand = new Random();
			randomNumber=rand.nextInt(9999);	
			randomRequestID="RID"+Integer.toString(randomNumber);
			return randomRequestID;
		}
}

