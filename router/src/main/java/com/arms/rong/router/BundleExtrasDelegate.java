package com.arms.rong.router;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by rong on 2017/6/5.
 */

public class BundleExtrasDelegate {

	/**
	 * 根据参数类型来判断调用bundle的putXXX方法传入值
	 * @param paramType
	 * @return
	 *
	int PUT_NO           = 0;
	int PUT_ALL_BUNDLE   = 1;
	int PUT_ALL_PERSISTBUNDLE = 26;
	int PUT_BOOLEAN      = 2;
	int PUT_BYTE         = 3;
	int PUT_CHAR         = 4;
	int PUT_SHORT        = 5;
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
	 */

	public @ExtraType.ValueExtraType int findExtraTypeByParamClazz(Type paramType) {

		switch (paramType.toString()) {
			case "int":
				return ExtraType.PUT_INT;
			case "short":
				return ExtraType.PUT_SHORT;
			case "char":
				return ExtraType.PUT_CHAR;
			case "byte":
				return ExtraType.PUT_BYTE;
			case "boolean":
				return ExtraType.PUT_BOOLEAN;
			case "long":
				return ExtraType.PUT_LONG;
			case "float":
				return ExtraType.PUT_FLOAT;
			case "double":
				return ExtraType.PUT_DOUBLE;
			case "class java.lang.String":
				return ExtraType.PUT_STRING;
			case "java.util.ArrayList<java.lang.Integer>":
				return ExtraType.PUT_INTEGER_ARRAYLIST;
			case "java.util.ArrayList<java.lang.String>":
				return ExtraType.PUT_STRING_ARRAYLIST;
			case "java.util.ArrayList<java.lang.CharSequence>":
				return ExtraType.PUT_CHAR_SEQUENCE_ARRAYLIST;
			case "class [Z":
			case "boolean[]":
				return ExtraType.PUT_BOOLEAN_ARRAY;
			case "class [B":
			case "byte[]":
				return ExtraType.PUT_BYTE_ARRAY;
			case "class [S":
			case "short[]":
				return ExtraType.PUT_SHORT_ARRAY;
			case "class [C":
			case "char[]":
				return ExtraType.PUT_CHAR_ARRAY;
			case "class [I":
			case "int[]":
				return ExtraType.PUT_INT_ARRAY;
			case "class [J":
			case "long[]":
				return ExtraType.PUT_LONG_ARRAY;
			case "class [F":
			case "float[]":
				return ExtraType.PUT_FLOAT_ARRAY;
			case "class [D":
			case "double[]":
				return ExtraType.PUT_DOUBLE_ARRAY;
			case "class [Ljava.lang.String;":
			case "java.lang.String[]":
				return ExtraType.PUT_STRING_ARRAY;
			case "class [Ljava.lang.CharSequence;":
			case "java.lang.CharSequence[]":
				return ExtraType.PUT_CHAR_SQUENCE_ARRAY;
			case "class android.os.Bundle":
				return ExtraType.PUT_ALL_BUNDLE;
			case "class android.os.PersistableBundle":
				return ExtraType.PUT_ALL_PERSISTBUNDLE;
			case "class [Landroid.os.Parcelable":
			case "android.os.Parcelable[]":
				return ExtraType.PUT_PARCELABLE_ARRAY;
			case "java.util.ArrayList<android.os.Parcelable>":
				return ExtraType.PUT_PARCELALE_ARRAY_LIST;
			case "class android.util.Size":
				return ExtraType.PUT_SIZE;
			case "class android.util.SizeF":
				return ExtraType.PUT_SIZE_F;
		}

		if(paramType instanceof Serializable) {
			return ExtraType.PUT_SERIALIZABLE;
		} else if(paramType instanceof Parcelable) {
			return ExtraType.PUT_PARCELABLE;
		}
		return ExtraType.PUT_NO;
	}

	/**
	 * 根据类型来传值
	 * @param type
	 * @param key
	 * @param extra
	 * @param extrasBundle
	 *
	int PUT_NO           = 0;
	int PUT_ALL_BUNDLE   = 1;
	int PUT_ALL_PERSISTBUNDLE = 26;
	int PUT_BOOLEAN      = 2;
	int PUT_BYTE         = 3;
	int PUT_CHAR         = 4;
	int PUT_SHORT        = 5;
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
	 */

	public void putExtra(@ExtraType.ValueExtraType int type, String key, Object extra, Bundle extrasBundle) {

		switch (type) {
			case ExtraType.PUT_ALL_BUNDLE:
				extrasBundle.putAll((Bundle) extra);
				break;
			case ExtraType.PUT_ALL_PERSISTBUNDLE:
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					extrasBundle.putAll((PersistableBundle) extra);
				}
				break;
			case ExtraType.PUT_BOOLEAN:
				extrasBundle.putBoolean(key, (boolean) extra);
				break;
			case ExtraType.PUT_BYTE:
				extrasBundle.putByte(key, (byte) extra);
				break;
			case ExtraType.PUT_CHAR:
				extrasBundle.putChar(key, (char) extra);
				break;
			case ExtraType.PUT_SHORT:
				extrasBundle.putShort(key, (short) extra);
				break;
			case ExtraType.PUT_INT:
				extrasBundle.putInt(key, (int) extra);
				break;
			case ExtraType.PUT_LONG:
				extrasBundle.putLong(key, (long) extra);
				break;
			case ExtraType.PUT_FLOAT:
				extrasBundle.putFloat(key, (float) extra);
				break;
			case ExtraType.PUT_DOUBLE:
				extrasBundle.putDouble(key, (double) extra);
				break;
			case ExtraType.PUT_STRING:
				extrasBundle.putString(key, (String) extra);
				break;
			case ExtraType.PUT_CHAR_SEQUENCE:
				extrasBundle.putCharSequence(key, (CharSequence) extra);
				break;
			case ExtraType.PUT_INTEGER_ARRAYLIST:
				extrasBundle.putIntegerArrayList(key, (ArrayList<Integer>) extra);
				break;
			case ExtraType.PUT_STRING_ARRAYLIST:
				extrasBundle.putStringArrayList(key, (ArrayList<String>) extra);
				break;
			case ExtraType.PUT_CHAR_SEQUENCE_ARRAYLIST:
				extrasBundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) extra);
				break;
			case ExtraType.PUT_SERIALIZABLE:
				extrasBundle.putSerializable(key, (Serializable) extra);
				break;
			case ExtraType.PUT_BOOLEAN_ARRAY:
				extrasBundle.putBooleanArray(key, (boolean[]) extra);
				break;
			case ExtraType.PUT_BYTE_ARRAY:
				extrasBundle.putByteArray(key, (byte[]) extra);
				break;
			case ExtraType.PUT_SHORT_ARRAY:
				extrasBundle.putShortArray(key, (short[]) extra);
				break;
			case ExtraType.PUT_CHAR_ARRAY:
				extrasBundle.putCharArray(key, (char[]) extra);
				break;
			case ExtraType.PUT_INT_ARRAY:
				extrasBundle.putIntArray(key, (int[]) extra);
				break;
			case ExtraType.PUT_LONG_ARRAY:
				extrasBundle.putLongArray(key, (long[]) extra);
				break;
			case ExtraType.PUT_FLOAT_ARRAY:
				extrasBundle.putFloatArray(key, (float[]) extra);
				break;
			case ExtraType.PUT_DOUBLE_ARRAY:
				extrasBundle.putDoubleArray(key, (double[]) extra);
				break;
			case ExtraType.PUT_STRING_ARRAY:
				extrasBundle.putStringArray(key, (String[]) extra);
				break;
			case ExtraType.PUT_CHAR_SQUENCE_ARRAY:
				extrasBundle.putCharSequenceArray(key, (CharSequence[]) extra);
				break;
			case ExtraType.PUT_PARCELABLE:
				extrasBundle.putParcelable(key, (Parcelable) extra);
				break;
			case ExtraType.PUT_PARCELABLE_ARRAY:
				extrasBundle.putParcelableArray(key, (Parcelable[]) extra);
				break;
			case ExtraType.PUT_PARCELALE_ARRAY_LIST:
				extrasBundle.putParcelableArrayList(key, (ArrayList<Parcelable>) extra);
				break;
			case ExtraType.PUT_SIZE:
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					extrasBundle.putSize(key, (Size) extra);
				}
				break;
			case ExtraType.PUT_SIZE_F:
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					extrasBundle.putSizeF(key, (SizeF) extra);
				}
				break;

		}
	}
}
