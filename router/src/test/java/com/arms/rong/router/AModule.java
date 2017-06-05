package com.arms.rong.router;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rong on 2017/6/2.
 */

//@Path("AModule")
public interface AModule {

	@Path("/user/main")
	@Target(BaseActivity.class)
	Intent main(@Query("int") int i,
	            @Query("short") short sh,
	            @Query("char") char ch,
	            @Query("byte") byte byt,
	            @Query("boolean") boolean boo,
	            @Query("long") long lon,
	            @Query("float") float flo,
	            @Query("double") double doub,
	            @Query("String") String str,
	            @Query("CharSequence") CharSequence charSequence,
	            @Query("IntegerArrayList") ArrayList<Integer> integers,
	            @Query("StringArrayList") ArrayList<String> strings,
	            @Query("CharSequenceArrayList") ArrayList<CharSequence> charSequences,
	            @Query("Serializable") Serializable serializable,
	            @Query("BooleanArray") boolean[] booleanArray,
	            @Query("ByteArray") byte[] byteArray,
	            @Query("ShortArray") short[] shortArray,
	            @Query("CharArray") char[] charArray,
	            @Query("IntArray") int[] intArray,
	            @Query("LongArray") long[] longArray,
	            @Query("FloatArray") float[] floatArray,
	            @Query("DoubleArray") double[] doublesArray,
	            @Query("StringArray") String[] stringArray,
	            @Query("CharSequenceArray") CharSequence[] charSequencesArray,
	            @Query("All") Bundle bundle,
	            @Query("All") PersistableBundle persistableBundle,
	            @Query("parcelable") Parcelable par,
	            @Query("ParcelableArray") Parcelable[] pars,
	            @Query("ParcelableArrayList") ArrayList<Parcelable> parcelableArrayList,
	            @Query("Size")Size size,
	            @Query("Sizef")SizeF sizeF);
}
