package com.GameArc.TerBot;


public class Listen implements Runnable{
	byte buff[];
	public void run() {
		try{
			while(true){
				buff = new byte[Bot.in.read()];
				Bot.in.skip(3);
				Bot.in.read(buff);
				for(byte b : buff){
					//System.out.print(b);
				}
			}
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
