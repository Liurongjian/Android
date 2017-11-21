package com.arms.rong.routermoduleb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Size;
import android.util.SizeF;

import com.arms.rong.router.NotNull;
import com.arms.rong.router.Path;
import com.arms.rong.router.Query;
import com.arms.rong.router.Target;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rong on 2017/6/5.
 */

@Path("/module/b/")
public interface RouterModule {

	@Path("main")
	@Target(ModuleBActivity.class)
	Intent mainActivity(@Query("int") int i,
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
	                      @Query("CharArray")
//			                      @NotNull //如果添加了NotNull的注解，则表示这个参数不能为空
			                    char[] charArray,
	                      @Query("IntArray") int[] intArray,
	                      @Query("LongArray") long[] longArray,
	                      @Query("FloatArray") float[] floatArray,
	                      @Query("DoubleArray") double[] doublesArray,
	                      @Query("StringArray") String[] stringArray,
	                      @Query("CharSequenceArray") CharSequence[] charSequencesArray,
	                      @Query("AllBundle") Bundle bundle,
	                      @Query("AllPersistableBundle") PersistableBundle persistableBundle,
	                      @Query("Parcelable") Parcelable par,
	                      @Query("ParcelableArray") Parcelable[] pars,
	                      @Query("ParcelableArrayList") ArrayList<Parcelable> parcelableArrayList,
	                      @Query("Size") Size size,
	                      @Query("Sizef") SizeF sizeF);
}
