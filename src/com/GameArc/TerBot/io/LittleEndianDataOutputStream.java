package com.GameArc.TerBot.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

/*
 * Taken from:
 * http://www.cafeaulait.org/books/javaio/ioexamples/07/LittleEndianOutputStream.java
 */

/**
 * The Class LittleEndianDataOutputStream.
 */
public class LittleEndianDataOutputStream extends FilterOutputStream {

	  /**
	   * The number of bytes written so far to the little endian output stream. 
	   */
	  protected int written;

	  /**
  	 * Creates a new little endian output stream and chains it to the
  	 * output stream specified by the out argument.
  	 *
  	 * @param out the out
  	 * @see     java.io.FilterOutputStream#out
  	 */
	  public LittleEndianDataOutputStream(OutputStream out) {
	    super(out);
	  }

	  /**
  	 * Writes the specified byte value to the underlying output stream.
  	 *
  	 * @param b the b
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public synchronized void write(int b) throws IOException {
	    out.write(b);
	    written++;
	  }

	  /**
  	 * Writes <code>length</code> bytes from the specified byte array
  	 * starting at <code>offset</code> to the underlying output stream.
  	 *
  	 * @param data the data
  	 * @param offset the offset
  	 * @param length the length
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public synchronized void write(byte[] data, int offset, int length) 
	   throws IOException {
	    out.write(data, offset, length);
	    written += length;
	  }


	  /**
  	 * Writes a <code>boolean</code> to the underlying output stream as
  	 * a single byte. If the argument is true, the byte value 1 is written.
  	 * If the argument is false, the byte value <code>0</code> in written.
  	 *
  	 * @param b the b
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeBoolean(boolean b) throws IOException {
	  
	    if (b) this.write(1);
	    else this.write(0);

	  }

	  /**
  	 * Writes out a <code>byte</code> to the underlying output stream.
  	 *
  	 * @param b the b
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeByte(int b) throws IOException {
	    out.write(b);
	    written++;
	  }

	  /**
  	 * Writes a two byte <code>short</code> to the underlying output stream in
  	 * little endian order, low byte first.
  	 *
  	 * @param s the s
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeShort(int s) throws IOException {

	    out.write(s & 0xFF);
	    out.write((s >>> 8) & 0xFF);
	    written += 2;
	    
	  }

	  /**
  	 * Writes a two byte <code>char</code> to the underlying output stream
  	 * in little endian order, low byte first.
  	 *
  	 * @param c the c
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeChar(int c) throws IOException {

	    out.write(c & 0xFF);
	    out.write((c >>> 8) & 0xFF);
	    written += 2;
	    
	  }

	  /**
  	 * Writes a four-byte <code>int</code> to the underlying output stream
  	 * in little endian order, low byte first, high byte last.
  	 *
  	 * @param i the i
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeInt(int i) throws IOException {

	    out.write(i & 0xFF);
	    out.write((i >>> 8) & 0xFF);
	    out.write((i >>> 16) & 0xFF);
	    out.write((i >>> 24) & 0xFF);
	    written += 4;
	    
	  }

	  /**
  	 * Writes an eight-byte <code>long</code> to the underlying output stream
  	 * in little endian order, low byte first, high byte last.
  	 *
  	 * @param l the l
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeLong(long l) throws IOException {

	    out.write((int) l & 0xFF);
	    out.write((int) (l >>> 8) & 0xFF);
	    out.write((int) (l >>> 16) & 0xFF);
	    out.write((int) (l >>> 24) & 0xFF);
	    out.write((int) (l >>> 32) & 0xFF);
	    out.write((int) (l >>> 40) & 0xFF);
	    out.write((int) (l >>> 48) & 0xFF);
	    out.write((int) (l >>> 56) & 0xFF);
	    written += 8;

	  }

	 /**
 	 * Writes a 4 byte Java float to the underlying output stream in
 	 * little endian order.
 	 *
 	 * @param f the f
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	  public final void writeFloat(float f) throws IOException {
	  
	    this.writeInt(Float.floatToIntBits(f));
	    
	  }

	 /**
 	 * Writes an 8 byte Java double to the underlying output stream in
 	 * little endian order.
 	 *
 	 * @param d the d
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	  public final void writeDouble(double d) throws IOException {
	  
	    this.writeLong(Double.doubleToLongBits(d));
	    
	  }

	  /**
  	 * Writes a string to the underlying output stream as a sequence of
  	 * bytes. Each character is written to the data output stream as
  	 * if by the <code>writeByte()</code> method.
  	 *
  	 * @param s the s
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 * @see        java.io.LittleEndianOutputStream#writeByte(int)
  	 * @see        java.io.LittleEndianOutputStream#out
  	 */
	  public void writeBytes(String s) throws IOException {

	    int length = s.length();
	    for (int i = 0; i < length; i++) {
	      out.write((byte) s.charAt(i));
	    }
	    written += length;
	    }

	  /**
  	 * Writes a string to the underlying output stream as a sequence of
  	 * characters. Each character is written to the data output stream as
  	 * if by the <code>writeChar</code> method.
  	 *
  	 * @param s the s
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 * @see        java.io.LittleEndianOutputStream#writeChar(int)
  	 * @see        java.io.LittleEndianOutputStream#out
  	 */
	  public void writeChars(String s) throws IOException {

	    int length = s.length();
	    for (int i = 0; i < length; i++) {
	      int c = s.charAt(i);
	      out.write(c & 0xFF);
	      out.write((c >>> 8) & 0xFF);
	    }
	    written += length * 2;
	    
	  }

	  /**
  	 * Writes a string of no more than 65,535 characters
  	 * to the underlying output stream using UTF-8
  	 * encoding. This method first writes a two byte short
  	 * in <b>big</b> endian order as required by the
  	 * UTF-8 specification. This gives the number of bytes in the
  	 * UTF-8 encoded version of the string, not the number of characters
  	 * in the string. Next each character of the string is written
  	 * using the UTF-8 encoding for the character.
  	 *
  	 * @param s the s
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 */
	  public void writeUTF(String s) throws IOException {

	    int numchars = s.length();
	    int numbytes = 0;

	    for (int i = 0 ; i < numchars ; i++) {
	      int c = s.charAt(i);
	      if ((c >= 0x0001) && (c <= 0x007F)) numbytes++;
	      else if (c > 0x07FF) numbytes += 3;
	      else numbytes += 2;
	    }

	    if (numbytes > 65535) throw new UTFDataFormatException();     

	    out.write((numbytes >>> 8) & 0xFF);
	    out.write(numbytes & 0xFF);
	    for (int i = 0 ; i < numchars ; i++) {
	      int c = s.charAt(i);
	      if ((c >= 0x0001) && (c <= 0x007F)) {
	        out.write(c);
	      }
	      else if (c > 0x07FF) {
	        out.write(0xE0 | ((c >> 12) & 0x0F));
	        out.write(0x80 | ((c >>  6) & 0x3F));
	        out.write(0x80 | (c & 0x3F));
	        written += 2;
	      } 
	      else {
	        out.write(0xC0 | ((c >>  6) & 0x1F));
	        out.write(0x80 | (c & 0x3F));
	        written += 1;
	      }
	    }
	    
	    written += numchars + 2;
	    
	  }

	  /**
	   * Returns the number of bytes written to this little endian output stream.
	   * (This class is not thread-safe with respect to this method. It is 
	   * possible that this number is temporarily less than the actual 
	   * number of bytes written.)
	   * @return  the value of the <code>written</code> field.
	   * @see     java.io.LittleEndianOutputStream#written
	   */
	  public int size() {
	    return this.written;
	  }

	public void writeString(String str) throws IOException {
		this.writeBytes(str);
	}

	public void writeUnsignedByte(int id) throws IOException {
        out.write(new byte[]{(byte)id});
}

	  
	}