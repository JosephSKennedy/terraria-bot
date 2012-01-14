package com.GameArc.TerBot;

public class Extra {
	
	public static final byte[] float2Byte(float inData) {
		int j = 0;
		byte[] outData=new byte[4];
		int data=Float.floatToIntBits(inData);
		outData[j++]=(byte)(data>>>24);
		outData[j++]=(byte)(data>>>16);
		outData[j++]=(byte)(data>>>8);
		outData[j++]=(byte)(data>>>0);
		return outData;
	}
	
}
