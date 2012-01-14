package com.GameArc.TerBot;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bot {
	static Socket c, s;
	static ServerSocket ss;
	static OutputStream out;
	static InputStream in;
	
	public static void main(String args[]) throws Exception{
		setInit();
		while(true) { 
			//out.write(Player.MakePacket());
			Thread.sleep(10);
		}
	}
	
	public static void setInit(){
		try {	
			c = new Socket("localhost", 7777);
			out = c.getOutputStream();
			in = c.getInputStream();
			out.write(new byte[]{
				0x0b, 0x00, 0x00, 0x00, 0x01, 0x54, 0x65, 0x72, 0x72, 0x61, 0x72, 0x69, 0x61, 0x33, 0x37
			});
			
			byte buf[] = new byte[in.read()];
			in.skip(3);
			in.read(buf);
			Storage.PlayerID = buf[1];
			new Thread(new Listen()).start();
			out.write(Storage.CharacterData);
			out.write(Storage.Health);
			out.write(Storage.Mana);
			out.write(Storage.Buff);
			out.write(new byte[]{
					0x01, 0x00, 0x00, 0x00, 0x06
			});
			out.write(new byte[]{
					0x09, 0x00, 0x00, 0x00, 0x08, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
			});
			out.write(new byte[]{
					0x0A, 0x00, 0x00, 0x00, 0x0C, Storage.PlayerID,(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
			});
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
	}
	
}
