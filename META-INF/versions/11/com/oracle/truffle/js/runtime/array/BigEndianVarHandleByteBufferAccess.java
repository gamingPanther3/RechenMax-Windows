package com.oracle.truffle.js.runtime.array;

import java.nio.*;
import java.lang.invoke.*;

final class BigEndianVarHandleByteBufferAccess extends ByteBufferAccess
{
    private static final VarHandle INT16;
    private static final VarHandle INT32;
    private static final VarHandle INT64;
    private static final VarHandle FLOAT;
    private static final VarHandle DOUBLE;
    static final ByteBufferAccess INSTANCE;
    
    @Override
    public int getInt16(final ByteBuffer buffer, final int index) {
        return BigEndianVarHandleByteBufferAccess.INT16.get(buffer, index);
    }
    
    @Override
    public int getInt32(final ByteBuffer buffer, final int index) {
        return BigEndianVarHandleByteBufferAccess.INT32.get(buffer, index);
    }
    
    @Override
    public long getInt64(final ByteBuffer buffer, final int index) {
        return BigEndianVarHandleByteBufferAccess.INT64.get(buffer, index);
    }
    
    @Override
    public float getFloat(final ByteBuffer buffer, final int index) {
        return BigEndianVarHandleByteBufferAccess.FLOAT.get(buffer, index);
    }
    
    @Override
    public double getDouble(final ByteBuffer buffer, final int index) {
        return BigEndianVarHandleByteBufferAccess.DOUBLE.get(buffer, index);
    }
    
    @Override
    public void putInt16(final ByteBuffer buffer, final int index, final int value) {
        BigEndianVarHandleByteBufferAccess.INT16.set(buffer, index, (short)value);
    }
    
    @Override
    public void putInt32(final ByteBuffer buffer, final int index, final int value) {
        BigEndianVarHandleByteBufferAccess.INT32.set(buffer, index, value);
    }
    
    @Override
    public void putInt64(final ByteBuffer buffer, final int index, final long value) {
        BigEndianVarHandleByteBufferAccess.INT64.set(buffer, index, value);
    }
    
    @Override
    public void putFloat(final ByteBuffer buffer, final int index, final float value) {
        BigEndianVarHandleByteBufferAccess.FLOAT.set(buffer, index, value);
    }
    
    @Override
    public void putDouble(final ByteBuffer buffer, final int index, final double value) {
        BigEndianVarHandleByteBufferAccess.DOUBLE.set(buffer, index, value);
    }
    
    static {
        INT16 = MethodHandles.byteBufferViewVarHandle((Class)short[].class, ByteOrder.BIG_ENDIAN);
        INT32 = MethodHandles.byteBufferViewVarHandle((Class)int[].class, ByteOrder.BIG_ENDIAN);
        INT64 = MethodHandles.byteBufferViewVarHandle((Class)long[].class, ByteOrder.BIG_ENDIAN);
        FLOAT = MethodHandles.byteBufferViewVarHandle((Class)float[].class, ByteOrder.BIG_ENDIAN);
        DOUBLE = MethodHandles.byteBufferViewVarHandle((Class)double[].class, ByteOrder.BIG_ENDIAN);
        INSTANCE = new BigEndianVarHandleByteBufferAccess();
    }
}
