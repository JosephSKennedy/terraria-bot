package com.GameArc.TerBot;

import java.io.FileWriter;

public class Listen implements Runnable{
	byte buff[];
	FileWriter f;
	public void run() {
		try{
			f = new FileWriter("C:\\Users\\Lasse\\Desktop\\Log.txt");
			while(true){
				buff = new byte[Bot.in.read()];
				Bot.in.skip(3);
				Bot.in.read(buff);
				for(byte b : buff){
					f.write("0x" + Integer.toHexString(b)+ ", ");
				}
				System.out.println();
			}
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			try {
				f.close();
			} catch (Exception e1) {}
		}
	}
}
