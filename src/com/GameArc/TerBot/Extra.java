package com.GameArc.TerBot;

public class Extra {
	
	public static final byte[] FloatToBytes(float inData) {
		int j = 0;
		byte[] outData=new byte[4];
		int data=Float.floatToIntBits(inData);
		outData[j++]=(byte)(data>>>24);
		outData[j++]=(byte)(data>>>16);
		outData[j++]=(byte)(data>>>8);
		outData[j++]=(byte)(data>>>0);
		return outData;
	}
	
	public static final byte[] IntToBytes(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
	}
	
	public static final int BytesToInt(byte [] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
	}
	
	public static final float BytesToFloat(byte [] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
	}
}
