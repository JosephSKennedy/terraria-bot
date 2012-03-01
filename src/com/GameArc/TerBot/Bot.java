package com.GameArc.TerBot;

import java.net.ServerSocket;
import java.net.Socket;

import com.GameArc.TerBot.io.LittleEndianDataInputStream;
import com.GameArc.TerBot.io.LittleEndianDataOutputStream;

public class Bot {
	static Socket c, s;
	public static Player player, teamate;
	static ServerSocket ss;
	static LittleEndianDataOutputStream out;
	static LittleEndianDataInputStream in;
	static boolean Active = false;
	static int i;
	static String tmp = "";
	
	public static void main(String args[]) throws Exception{
		setInit();
		while(!Active)Thread.sleep(250);
		out.write(new byte[]{0x01, 0x00, 0x00, 0x00, 0x0F});
		WriteChat("Hello :D");
		while(true) { 			
			if(player.X < 10.0f){
				player.X = teamate.X;
				player.Y = teamate.Y;
			}
			
			if(player.X < teamate.X - 12){
				player.X += 3f;
				player.X = 3f;
				player.Control = 8;
			}else{
				player.vX = 0;
			}
			
			if(player.X > teamate.X + 12){
				player.X -= 3f;
				player.X = -3f;
				player.Control = 4;
			}else{
				player.vX = 0;
			}
			
			player.Y = teamate.Y;
			
			out.write(new byte[]{0x14, 0x00, 0x00, 0x00, 0x0D});
			out.writeByte(Storage.PlayerID);
			out.writeByte(player.Control);
			out.writeByte(player.SelectedItem);
			out.writeFloat(player.X);
			out.writeFloat(player.Y);
			out.writeFloat(player.vX);
			out.writeFloat(player.vY);
			Thread.sleep(1);
		}
	}
	
	public static void setInit() throws Exception{
		try {	
			player = new Player(); teamate = new Player();
			c = new Socket("172.16.1.18", 7777);
			out = new LittleEndianDataOutputStream(c.getOutputStream());
			in = new LittleEndianDataInputStream(c.getInputStream());
			out.write(new byte[]{
				0x0B, 0x00, 0x00, 0x00, 0x01
			});
			out.writeString("Terraria39");
			
			
			byte buf[] = new byte[in.readInt()];
			in.readFully(buf);
			new Thread(new Listen()).start();
			player.PlayerID = buf[1];
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
	
	public static void WriteChat(String tmp) throws Exception{
		out.writeInt(tmp.length() + 5);
		out.write(0x19);
		out.writeByte(player.PlayerID);
		out.write(new byte[]{(byte) 0xFF,0x00,(byte) 0xFF});
		out.writeString(tmp);
	}
	
}
