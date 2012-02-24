package com.GameArc.TerBot.io;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * This class was borrowed from:
 * http://www.peterfranza.com/2008/09/26/little-endian-input-stream/
 */

/**
 * The Class LittleEndianDataInputStream.
 */
public class LittleEndianDataInputStream extends InputStream implements
		DataInput {

	/**
	 * Instantiates a new little endian data input stream.
	 *
	 * @param i the i
	 */
	public LittleEndianDataInputStream(InputStream i) {
		BufferedInputStream in = new BufferedInputStream(i);
		this.in = in;
		this.d = new DataInputStream(in);
		w = new byte[8];
	}
	
	/* (non-Javadoc)
	 * @see java.io.InputStream#available()
	 */
	public int available() throws IOException {
		return d.available();
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readShort()
	 */
	public final short readShort() throws IOException {
		d.readFully(w, 0, 2);
		return (short) ((w[1] & 0xff) << 8 | (w[0] & 0xff));
	}

	/**
	 * Note, returns int even though it reads a short.
	 *
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final int readUnsignedShort() throws IOException {
		d.readFully(w, 0, 2);
		return ((w[1] & 0xff) << 8 | (w[0] & 0xff));
	}

	/**
	 * like DataInputStream.readChar except little endian.
	 *
	 * @return the char
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final char readChar() throws IOException {
		d.readFully(w, 0, 2);
		return (char) ((w[1] & 0xff) << 8 | (w[0] & 0xff));
	}

	/**
	 * like DataInputStream.readInt except little endian.
	 *
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final int readInt() throws IOException {
		d.readFully(w, 0, 4);
		return (w[3]) << 24 | (w[2] & 0xff) << 16 | (w[1] & 0xff) << 8
				| (w[0] & 0xff);
	}

	/**
	 * like DataInputStream.readLong except little endian.
	 *
	 * @return the long
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final long readLong() throws IOException {
		d.readFully(w, 0, 8);
		return (long) (w[7]) << 56 | (long) (w[6] & 0xff) << 48
				| (long) (w[5] & 0xff) << 40 | (long) (w[4] & 0xff) << 32
				| (long) (w[3] & 0xff) << 24 | (long) (w[2] & 0xff) << 16
				| (long) (w[1] & 0xff) << 8 | (long) (w[0] & 0xff);
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readFloat()
	 */
	public final float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readDouble()
	 */
	public final double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}

	/* (non-Javadoc)
	 * @see java.io.InputStream#read(byte[], int, int)
	 */
	public final int read(byte b[], int off, int len) throws IOException {
		return in.read(b, off, len);
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readFully(byte[])
	 */
	public final void readFully(byte b[]) throws IOException {
		d.readFully(b, 0, b.length);
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readFully(byte[], int, int)
	 */
	public final void readFully(byte b[], int off, int len) throws IOException {
		d.readFully(b, off, len);
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#skipBytes(int)
	 */
	public final int skipBytes(int n) throws IOException {
		return d.skipBytes(n);
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readBoolean()
	 */
	public final boolean readBoolean() throws IOException {
		return d.readBoolean();
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readByte()
	 */
	public final byte readByte() throws IOException {
		return d.readByte();
	}

	/* (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	public int read() throws IOException {
		return in.read();
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readUnsignedByte()
	 */
	public final int readUnsignedByte() throws IOException {
		return d.readUnsignedByte();
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readLine()
	 */
	@Deprecated
	public final String readLine() throws IOException {
		return d.readLine();
	}

	/* (non-Javadoc)
	 * @see java.io.DataInput#readUTF()
	 */
	public final String readUTF() throws IOException {
		return d.readUTF();
	}

	/* (non-Javadoc)
	 * @see java.io.InputStream#close()
	 */
	public final void close() throws IOException {
		d.close();
	}

	/** The d. */
	private DataInputStream d; // to get at high level readFully methods of
	// DataInputStream
	/** The in. */
	private InputStream in; // to get at the low-level read methods of
	// InputStream
	/** The w. */
	private byte w[]; // work array for buffering input
	public String readString() throws IOException {
		int len = readUnsignedByte();
		byte[] b = new byte[len];
		readFully(b,0,len);
		return new String(b,0,len);
	}
}
