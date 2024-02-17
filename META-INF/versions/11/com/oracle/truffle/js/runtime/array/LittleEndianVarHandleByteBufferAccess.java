package com.oracle.truffle.js.runtime.array;

import java.nio.*;
import java.lang.invoke.*;

final class LittleEndianVarHandleByteBufferAccess extends ByteBufferAccess
{
    private static final VarHandle INT16;
    private static final VarHandle INT32;
    private static final VarHandle INT64;
    private static final VarHandle FLOAT;
    private static final VarHandle DOUBLE;
    static final ByteBufferAccess INSTANCE;
    
    @Override
    public int getInt16(final ByteBuffer buffer, final int index) {
        return LittleEndianVarHandleByteBufferAccess.INT16.get(buffer, index);
    }
    
    @Override
    public int getInt32(final ByteBuffer buffer, final int index) {
        return LittleEndianVarHandleByteBufferAccess.INT32.get(buffer, index);
    }
    
    @Override
    public long getInt64(final ByteBuffer buffer, final int index) {
        return LittleEndianVarHandleByteBufferAccess.INT64.get(buffer, index);
    }
    
    @Override
    public float getFloat(final ByteBuffer buffer, final int index) {
        return LittleEndianVarHandleByteBufferAccess.FLOAT.get(buffer, index);
    }
    
    @Override
    public double getDouble(final ByteBuffer buffer, final int index) {
        return LittleEndianVarHandleByteBufferAccess.DOUBLE.get(buffer, index);
    }
    
    @Override
    public void putInt16(final ByteBuffer buffer, final int index, final int value) {
        LittleEndianVarHandleByteBufferAccess.INT16.set(buffer, index, (short)value);
    }
    
    @Override
    public void putInt32(final ByteBuffer buffer, final int index, final int value) {
        LittleEndianVarHandleByteBufferAccess.INT32.set(buffer, index, value);
    }
    
    @Override
    public void putInt64(final ByteBuffer buffer, final int index, final long value) {
        LittleEndianVarHandleByteBufferAccess.INT64.set(buffer, index, value);
    }
    
    @Override
    public void putFloat(final ByteBuffer buffer, final int index, final float value) {
        LittleEndianVarHandleByteBufferAccess.FLOAT.set(buffer, index, value);
    }
    
    @Override
    public void putDouble(final ByteBuffer buffer, final int index, final double value) {
        LittleEndianVarHandleByteBufferAccess.DOUBLE.set(buffer, index, value);
    }
    
    static {
        INT16 = MethodHandles.byteBufferViewVarHandle((Class)short[].class, ByteOrder.LITTLE_ENDIAN);
        INT32 = MethodHandles.byteBufferViewVarHandle((Class)int[].class, ByteOrder.LITTLE_ENDIAN);
        INT64 = MethodHandles.byteBufferViewVarHandle((Class)long[].class, ByteOrder.LITTLE_ENDIAN);
        FLOAT = MethodHandles.byteBufferViewVarHandle((Class)float[].class, ByteOrder.LITTLE_ENDIAN);
        DOUBLE = MethodHandles.byteBufferViewVarHandle((Class)double[].class, ByteOrder.LITTLE_ENDIAN);
        INSTANCE = new LittleEndianVarHandleByteBufferAccess();
    }
}
