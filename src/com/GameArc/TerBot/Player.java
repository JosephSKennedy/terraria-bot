package com.GameArc.TerBot;

public class Player {
	public float X = Storage.SpawnX, Y = Storage.SpawnY;
	public float vX = 0, vY = 0;
	public byte Control = 0x04;
	public boolean Left = false;
	public byte SelectedItem = 0;
	public byte PlayerID = 0;
	public String Name = "";
	
	public static class States {
		public static byte IdleLeft = 0;
		public static byte FirstLeft = 68;
		public static byte WalkLeft = 4;
		public static byte IdleRight = 64;
		public static byte FirstRight = 8;
		public static byte WalkRight = 72;
	}
}
