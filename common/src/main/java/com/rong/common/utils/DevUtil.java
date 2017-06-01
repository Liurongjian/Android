package com.rong.common.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by rong on 2017/5/23.
 */
public class DevUtil {
	private static final int LOG_CHUNK_SIZE = 4000;
	private static boolean isDebug = true;

	public static void d(String tag, Object msg) {
		if (isDebug && msg != null) {
			String message = msg.toString();
			for (int i = 0, len = message.length(); i < len; i += LOG_CHUNK_SIZE) {
				int end = Math.min(len, i + LOG_CHUNK_SIZE);
				Log.d(tag, message.substring(i, end));
			}
		}
	}

	public static void e(String tag, Object msg) {
		if (isDebug && msg != null) {
			String message = msg.toString();
			for (int i = 0, len = message.length(); i < len; i += LOG_CHUNK_SIZE) {
				int end = Math.min(len, i + LOG_CHUNK_SIZE);
				Log.e(tag, message.substring(i, end));
			}
		}
	}

	public static void setDebug(boolean debug) {
		DevUtil.isDebug = debug;
	}

	public static void init(Context context, boolean isDebug) {
		DevUtil.isDebug = isDebug;
	}

	public static boolean isDebug() {
		return isDebug;
	}

	public static boolean isAddShortCut(Context context, int appNameRes) {

		boolean isInstallShortcut = false;
		final ContentResolver cr = context.getContentResolver();

		int versionLevel = android.os.Build.VERSION.SDK_INT;
		String AUTHORITY = "com.android.launcher2.settings";

		// 2.2以上的系统的文件文件名字是不一样的
		if (versionLevel >= 8) {
			AUTHORITY = "com.android.launcher2.settings";
		} else {
			AUTHORITY = "com.android.launcher.settings";
		}

		final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
		Cursor c = cr.query(CONTENT_URI, new String[]{"title", "iconResource"}, "title=?",
				new String[]{context.getString(appNameRes)}, null);

		if (c != null && c.getCount() > 0) {
			isInstallShortcut = true;
		}
		return isInstallShortcut;
	}

	/**
	 * 1、可以先调用isAddShortCut判断是否已经添加快捷方式<br/>
	 * 2、在启动的activity中加一个intent-filter <intent-filter> <action
	 * android:name="android.intent.action.CREATE_SHORTCUT"></action>
	 * </intent-filter> <br>
	 * 3、需要权限 :<uses-permission
	 * android:name="com.android.launcher.permission.READ_SETTINGS"/>
	 * <uses-permission
	 * android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
	 *
	 * @param context
	 * @param clzz
	 * @param iconResId
	 */
	public static void addShortCut(Context context, Class<?> clzz, int iconResId, int appNameRes) {
		// where this is a context (e.g. your current activity)
		final Intent shortcutIntent = new Intent(context, clzz);
		shortcutIntent.setAction("com.dada.mobile.android");
		final Intent intent = new Intent();
		intent.putExtra("duplicate", false);
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// Sets the custom shortcut's title
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(appNameRes));
		// Set the custom shortcut icon
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(context, iconResId));
		// add the shortcut
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		context.sendBroadcast(intent);

		// Intent shortcut = new Intent(
		// "com.android.launcher.action.INSTALL_SHORTCUT");
		// // 设置属性
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources()
		// .getString(appNameRes));
		// ShortcutIconResource iconRes =
		// Intent.ShortcutIconResource.fromContext(
		// context.getApplicationContext(), iconResId);
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconRes);
		//
		// // 是否允许重复创建
		// shortcut.putExtra("duplicate", false);
		//
		// // 设置桌面快捷方式的图标
		// Parcelable icon = Intent.ShortcutIconResource.fromContext(context,
		// iconResId);
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		//
		// // 点击快捷方式的操作
		// Intent intent = new Intent(Intent.ACTION_MAIN);
		// intent.setAction("com.android.action.dada");
		// intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		// intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
		// intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// intent.setClass(context, clzz);
		//
		// // 设置启动程序
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		//
		// // 广播通知桌面去创建
		// context.sendBroadcast(shortcut);
	}

	/**
	 * 删除程序的快捷方式
	 */
	public static void delShortcut(Activity context, Class<?> clzz, int iconResId, int appNameRes) {
		// Intent shortcut = new
		// Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		// // 快捷方式的名称
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
		// activity.getString(appNameRes));
		// // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
		// // 注意: ComponentName的第二个参数必须是完整的类名（包名+类名），否则无法删除快捷方式
		// String appClass = activity.getPackageName() + "." +
		// activity.getLocalClassName();
		// DevUtil.d("zqt", "appClass="+appClass);
		// ComponentName comp = new ComponentName(activity.getPackageName(),
		// appClass);
		// shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new
		// Intent(Intent.ACTION_MAIN).setComponent(comp));
		// activity.sendBroadcast(shortcut);
		// where this is a context (e.g. your current activity)
		final Intent shortcutIntent = new Intent(context, clzz);
		shortcutIntent.setAction("com.dada.mobile.android");
		final Intent intent = new Intent();
		intent.putExtra("duplicate", false);
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// Sets the custom shortcut's title
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(appNameRes));
		// Set the custom shortcut icon
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(context, iconResId));
		// add the shortcut
		intent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
		context.sendBroadcast(intent);
	}

}
