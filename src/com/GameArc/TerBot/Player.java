package com.GameArc.TerBot;

public class Player {
	static float X = Extra.BytesToFloat(Storage.SpawnX), Y = Extra.BytesToFloat(Storage.SpawnY);
	static float vX = 0, vY = 0;
	
	static byte[] MakePacket(){
		return new byte[]{
				0x14, 0x00, 0x00, 0x00, 0x0D, Storage.PlayerID, 0x00, 0x00,
				Extra.FloatToBytes(X)[0], Extra.FloatToBytes(X)[1], Extra.FloatToBytes(X)[2], Extra.FloatToBytes(X)[3],
				Extra.FloatToBytes(Y)[0], Extra.FloatToBytes(Y)[1], Extra.FloatToBytes(Y)[2], Extra.FloatToBytes(Y)[3],
				Extra.FloatToBytes(vX)[0], Extra.FloatToBytes(vX)[1], Extra.FloatToBytes(vX)[2], Extra.FloatToBytes(vX)[3],
				Extra.FloatToBytes(vY)[0], Extra.FloatToBytes(vY)[1], Extra.FloatToBytes(vY)[2], Extra.FloatToBytes(vY)[3]
		};
	}

}
