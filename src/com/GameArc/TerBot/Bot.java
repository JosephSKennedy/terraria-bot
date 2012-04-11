package com.GameArc.TerBot;

import java.net.ServerSocket;
import java.net.Socket;

import com.GameArc.TerBot.AI.AI;
import com.GameArc.TerBot.AI.Follower;
import com.GameArc.TerBot.io.LittleEndianDataInputStream;
import com.GameArc.TerBot.io.LittleEndianDataOutputStream;

public class Bot {
	
	static String ServerIP = "localhost";
	
	static Socket c, s;
	public static Player bot, player;
	public static AI BotAI;
	static ServerSocket ss;
	public static LittleEndianDataOutputStream out;
	public static LittleEndianDataInputStream in;
	static boolean Active = false;
	static int i;
	static String tmp = "";
	
	public static void main(String args[]) throws Exception{
		player = new Player();
		if(args.length  == 2){
			ServerIP = args[0];
			player.Name = args[1];
		}else {
			player.Name = "GamerEngage";
			//System.exit(1);
		}
		setInit();
		BotAI = new Follower();
		while(!Active)Thread.sleep(250);
		out.write(new byte[]{0x01, 0x00, 0x00, 0x00, 0x0F});
		while(true) { 
			BotAI.logic();
			Thread.sleep(10);
		}
	}
	
	public static void setInit() throws Exception{
		try {	
			bot = new Player(); player = new Player();
			c = new Socket(ServerIP, 7777);
			out = new LittleEndianDataOutputStream(c.getOutputStream());
			in = new LittleEndianDataInputStream(c.getInputStream());
			out.write(new byte[]{
				0x0B, 0x00, 0x00, 0x00, 0x01
			});
			out.writeString("Terraria39");
			
			
			byte buf[] = new byte[in.readInt()];
			in.readFully(buf);
			new Thread(new Listen()).start();
			bot.PlayerID = buf[1];
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
