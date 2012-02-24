package com.GameArc.TerBot;

import java.net.ServerSocket;
import java.net.Socket;

import com.GameArc.TerBot.io.LittleEndianDataInputStream;
import com.GameArc.TerBot.io.LittleEndianDataOutputStream;

public class Bot {
	static Socket c, s;
	static ServerSocket ss;
	static LittleEndianDataOutputStream out;
	static LittleEndianDataInputStream in;
	
	public static void main(String args[]) throws Exception{
		setInit();
		while(true) { 
			//out.write(Player.MakePacket());
			Thread.sleep(10);
		}
	}
	
	public static void setInit() throws Exception{
		try {	
			c = new Socket("localhost", 7777);
			out = new LittleEndianDataOutputStream(c.getOutputStream());
			in = new LittleEndianDataInputStream(c.getInputStream());
			out.write(new byte[]{
				0x0b, 0x00, 0x00, 0x00, 0x01
			});
			out.writeString("Terraria39");
			
			
			byte buf[] = new byte[in.readInt()];
			in.readFully(buf);
			new Thread(new Listen()).start();
			Storage.PlayerID = buf[1];
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
			e.printStackTrace();
		}
	}
	
}
