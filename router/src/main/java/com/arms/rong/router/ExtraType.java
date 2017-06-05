package com.arms.rong.router;

import android.support.annotation.IntDef;

import java.lang.annotation.*;

/**
 * Created by rong on 2017/6/3.
 */

@java.lang.annotation.Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtraType {
	@ValueExtraType int value() default 0;

	int PUT_NO           = 0;
	int PUT_ALL_BUNDLE   = 1;           // bundle.putAll(Bundle bundle)
	int PUT_ALL_PERSISTBUNDLE = 26;     // bundle.putAll(PersistableBundle bundle)
	int PUT_BOOLEAN      = 2;           // bundle.putBoolean(String key, boolean boolean);
	int PUT_BYTE         = 3;           // bundle.putByte(String key, byte byte)
	int PUT_CHAR         = 4;           // bundle.putChar(String key, char char)
	int PUT_SHORT        = 5;           // bundle.putShort(String key, short short)
	int PUT_INT          = 6;
	int PUT_LONG         = 7;
	int PUT_FLOAT        = 8;
	int PUT_DOUBLE       = 9;
	int PUT_STRING       = 10;
	int PUT_CHAR_SEQUENCE = 11;
	int PUT_INTEGER_ARRAYLIST = 12;
	int PUT_STRING_ARRAYLIST = 13;
	int PUT_CHAR_SEQUENCE_ARRAYLIST = 14;
	int PUT_SERIALIZABLE     = 15;
	int PUT_BOOLEAN_ARRAY = 16;
	int PUT_BYTE_ARRAY    = 17;
	int PUT_SHORT_ARRAY   = 18;
	int PUT_CHAR_ARRAY    = 19;
	int PUT_INT_ARRAY     = 20;
	int PUT_LONG_ARRAY    = 21;
	int PUT_FLOAT_ARRAY   = 22;
	int PUT_DOUBLE_ARRAY  = 23;
	int PUT_STRING_ARRAY  = 24;
	int PUT_CHAR_SQUENCE_ARRAY = 25;
	int PUT_PARCELABLE = 27;
	int PUT_PARCELABLE_ARRAY = 28;
	int PUT_PARCELALE_ARRAY_LIST = 29;
	int PUT_SIZE = 30;
	int PUT_SIZE_F = 31;

	@IntDef({PUT_NO, PUT_ALL_BUNDLE, PUT_ALL_PERSISTBUNDLE, PUT_BOOLEAN, PUT_BYTE, PUT_CHAR, PUT_SHORT,
			PUT_INT, PUT_LONG, PUT_FLOAT,PUT_DOUBLE, PUT_STRING, PUT_CHAR_SEQUENCE,
			PUT_INTEGER_ARRAYLIST, PUT_STRING_ARRAYLIST, PUT_CHAR_SEQUENCE_ARRAYLIST,
			PUT_SERIALIZABLE, PUT_BOOLEAN_ARRAY, PUT_BYTE_ARRAY, PUT_SHORT_ARRAY, PUT_CHAR_ARRAY,
			PUT_INT_ARRAY, PUT_LONG_ARRAY, PUT_FLOAT_ARRAY, PUT_DOUBLE_ARRAY, PUT_STRING_ARRAY,
			PUT_CHAR_SQUENCE_ARRAY, PUT_PARCELABLE, PUT_PARCELABLE_ARRAY, PUT_PARCELALE_ARRAY_LIST,
			PUT_SIZE, PUT_SIZE_F})
	@Retention(RetentionPolicy.SOURCE)
	@interface ValueExtraType {

	}
}
