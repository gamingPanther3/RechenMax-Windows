package com.oracle.truffle.js.runtime.array;

import java.nio.*;
import java.lang.invoke.*;

final class VarHandleNativeOrderByteArrayAccess extends ByteArrayAccess
{
    private static final VarHandle INT16;
    private static final VarHandle INT32;
    private static final VarHandle INT64;
    private static final VarHandle FLOAT;
    private static final VarHandle DOUBLE;
    static final ByteArrayAccess INSTANCE;
    
    @Override
    public int getInt16(final byte[] buffer, final int byteIndex) {
        return VarHandleNativeOrderByteArrayAccess.INT16.get(buffer, byteIndex);
    }
    
    @Override
    public int getInt32(final byte[] buffer, final int byteIndex) {
        return VarHandleNativeOrderByteArrayAccess.INT32.get(buffer, byteIndex);
    }
    
    @Override
    public long getInt64(final byte[] buffer, final int byteIndex) {
        return VarHandleNativeOrderByteArrayAccess.INT64.get(buffer, byteIndex);
    }
    
    @Override
    public float getFloat(final byte[] buffer, final int byteIndex) {
        return VarHandleNativeOrderByteArrayAccess.FLOAT.get(buffer, byteIndex);
    }
    
    @Override
    public double getDouble(final byte[] buffer, final int byteIndex) {
        return VarHandleNativeOrderByteArrayAccess.DOUBLE.get(buffer, byteIndex);
    }
    
    @Override
    public void putInt16(final byte[] buffer, final int byteIndex, final int value) {
        VarHandleNativeOrderByteArrayAccess.INT16.set(buffer, byteIndex, (short)value);
    }
    
    @Override
    public void putInt32(final byte[] buffer, final int byteIndex, final int value) {
        VarHandleNativeOrderByteArrayAccess.INT32.set(buffer, byteIndex, value);
    }
    
    @Override
    public void putInt64(final byte[] buffer, final int byteIndex, final long value) {
        VarHandleNativeOrderByteArrayAccess.INT64.set(buffer, byteIndex, value);
    }
    
    @Override
    public void putFloat(final byte[] buffer, final int byteIndex, final float value) {
        VarHandleNativeOrderByteArrayAccess.FLOAT.set(buffer, byteIndex, value);
    }
    
    @Override
    public void putDouble(final byte[] buffer, final int byteIndex, final double value) {
        VarHandleNativeOrderByteArrayAccess.DOUBLE.set(buffer, byteIndex, value);
    }
    
    static {
        INT16 = MethodHandles.byteArrayViewVarHandle((Class)short[].class, ByteOrder.nativeOrder());
        INT32 = MethodHandles.byteArrayViewVarHandle((Class)int[].class, ByteOrder.nativeOrder());
        INT64 = MethodHandles.byteArrayViewVarHandle((Class)long[].class, ByteOrder.nativeOrder());
        FLOAT = MethodHandles.byteArrayViewVarHandle((Class)float[].class, ByteOrder.nativeOrder());
        DOUBLE = MethodHandles.byteArrayViewVarHandle((Class)double[].class, ByteOrder.nativeOrder());
        INSTANCE = new VarHandleNativeOrderByteArrayAccess();
    }
}
