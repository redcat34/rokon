package com.stickycoding.rokon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.os.Build;

/**
 * BufferObject.java
 * Some functions for Buffers
 * 
 * @author Richard 
 */

public class BufferObject {

	protected ByteBuffer byteBuffer;
	protected IntBuffer intBuffer;
	protected int size;
	
	public int getSize() {
		return size;
	}
	
	public BufferObject(int length) {
		if(Build.VERSION.SDK == "3")
			byteBuffer = ByteBuffer.allocate(length*4);
		else
			byteBuffer = ByteBuffer.allocateDirect(length*4);
		byteBuffer.order(ByteOrder.nativeOrder());	
		size = length;
	}
	
	public BufferObject(float[] floats) {
		if(Build.VERSION.SDK == "3")
			byteBuffer = ByteBuffer.allocate(floats.length * 4);
		else
			byteBuffer = ByteBuffer.allocateDirect(floats.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		updateRaw(floats);
		size = floats.length;
	}
	
	public void free() {
		byteBuffer.clear();
		byteBuffer = null;
	}
	
	public void updateRaw(float[] floats) {
		byteBuffer.position(0);
		for(int i = 0; i < floats.length; i++) {
			byteBuffer.putFloat(floats[i]);
		}
		byteBuffer.position(0);
	}
	
	public void update(float x, float y, float width, float height) {
		byteBuffer.position(0);
		byteBuffer.putFloat(x);
		byteBuffer.putFloat(y);
		byteBuffer.putFloat(x + width);
		byteBuffer.putFloat(y);
		byteBuffer.putFloat(x);
		byteBuffer.putFloat(y + height);
		byteBuffer.putFloat(x + width);
		byteBuffer.putFloat(y + height);
		byteBuffer.position(0);
	}
	
	public IntBuffer getIntBuffer() {
		return intBuffer;
	}
	
	public ByteBuffer get() {
		return byteBuffer;
	}
	
}