package com.GameArc.TerBot;

public class Player {
	static float X = 0, Y = 0;
	static float vX = 0, vY = 0;
	
	static byte[] MakePacket(){
		return new byte[]{
				0x14, 0x00, 0x00, 0x00, 0x0D, Storage.PlayerID, 0x00, 0x00,
				Extra.float2Byte(X)[0], Extra.float2Byte(X)[1], Extra.float2Byte(X)[2], Extra.float2Byte(X)[3],
				Extra.float2Byte(Y)[0], Extra.float2Byte(Y)[1], Extra.float2Byte(Y)[2], Extra.float2Byte(Y)[3],
				Extra.float2Byte(vX)[0], Extra.float2Byte(vX)[1], Extra.float2Byte(vX)[2], Extra.float2Byte(vX)[3],
				Extra.float2Byte(vY)[0], Extra.float2Byte(vY)[1], Extra.float2Byte(vY)[2], Extra.float2Byte(vY)[3]
		};
	}

}
