package com.GameArc.TerBot.AI;

import com.GameArc.TerBot.Bot;
import com.GameArc.TerBot.Player.States;

public abstract class AI {
	
	public abstract void closeAI();
	public abstract void openAI();
	public abstract void logic();
	boolean isAlive = false;
	
	public AI(){
		isAlive = true;
		openAI();
	}
	
	public void stop(){
		isAlive = false;
		closeAI();
	}
	public void KillNPC(short id){
		
	}
	public void UpdatePlayer(){
		try{
		Bot.out.write(new byte[]{0x14, 0x00, 0x00, 0x00, 0x0D});
		Bot.out.writeByte(Bot.player.PlayerID);
		Bot.out.writeByte(Bot.bot.Control);
		Bot.out.writeByte(Bot.bot.SelectedItem);
		Bot.out.writeFloat(Bot.bot.X);
		Bot.out.writeFloat(Bot.bot.Y);
		Bot.out.writeFloat(Bot.bot.vX);
		Bot.out.writeFloat(Bot.bot.vY);
		}catch(Exception e){e.printStackTrace();}
	}
	public void GotoPosition(float x, float y){
		Bot.bot.X = x;
		Bot.bot.Y = y;
	}
	public void FollowPlayer(){
		if(Bot.bot.X < 10.0f){
			Bot.bot.X = Bot.player.X;
			Bot.bot.Y = Bot.player.Y;
		}
		
		if(Bot.bot.X < Bot.player.X - 12){
			Bot.bot.X += 1f;
			Bot.bot.vX = 3f;
		}else{
			Bot.bot.vX = 0;
			Bot.bot.Control = States.IdleRight;
		}
		
		if(Bot.bot.X > Bot.player.X + 12){
			Bot.bot.X -= 1f;
			Bot.bot.vX = -3f;
		}else{
			Bot.bot.vX = 0;
			Bot.bot.Control = States.IdleLeft;
		}
		
		if(Bot.bot.vX == -3f) {
			Bot.bot.Control = States.FirstLeft;
			UpdatePlayer();
			Bot.bot.Control = States.WalkLeft;
		}
		else {
			Bot.bot.Control = States.FirstRight;
			UpdatePlayer();
			Bot.bot.Control = States.WalkRight;
		}
		
		Bot.bot.Y = Bot.player.Y;
		UpdatePlayer();
	}
	public void DestroyTile(int x, int y){
		try {
			Bot.out.writeInt(13);
			Bot.out.writeByte(0x11);
			Bot.out.writeByte(0x00);
			Bot.out.writeInt(x);
			Bot.out.writeInt(y);
			Bot.out.writeByte(0x00);
			Bot.out.writeBoolean(false);
			Bot.out.writeByte(0x00);
		} catch (Exception e) {e.printStackTrace();}
	}
	public void PlaceTile(int x, int y, byte tile){
		try {
			Bot.out.writeInt(13);
			Bot.out.writeByte(0x11);
			Bot.out.writeByte(0x01);
			Bot.out.writeInt(x);
			Bot.out.writeInt(y);
			Bot.out.writeByte(tile);
			Bot.out.writeBoolean(false);
			Bot.out.writeByte(0x01);
		} catch (Exception e) {e.printStackTrace();}
	}
	public void DestroyWall(int x, int y){
		try {
			Bot.out.writeInt(13);
			Bot.out.writeByte(0x11);
			Bot.out.writeByte(0x02);
			Bot.out.writeInt(x);
			Bot.out.writeInt(y);
			Bot.out.writeByte(0x00);
			Bot.out.writeBoolean(false);
			Bot.out.writeByte(0x02);
		} catch (Exception e) {e.printStackTrace();}
	}
	public void PlaceWall(int x, int y, byte tile){
		try {
			Bot.out.writeInt(13);
			Bot.out.writeByte(0x11);
			Bot.out.writeByte(0x03);
			Bot.out.writeInt(x);
			Bot.out.writeInt(y);
			Bot.out.writeByte(tile);
			Bot.out.writeBoolean(false);
			Bot.out.writeByte(0x03);
		} catch (Exception e) {e.printStackTrace();}
	}
	public void WriteChat(String tmp) {
		try {
			Bot.out.writeInt(tmp.length() + 5);
			Bot.out.write(0x19);
			Bot.out.writeByte(Bot.bot.PlayerID);
			Bot.out.write(new byte[]{(byte) 0xFF,0x00,(byte) 0xFF});
			Bot.out.writeString(tmp);
		} catch(Exception e){e.printStackTrace();}
	}

}
