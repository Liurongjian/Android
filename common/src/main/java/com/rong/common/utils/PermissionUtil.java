package com.rong.common.utils;

import android.Manifest;
import android.app.Activity;

import com.rong.common.arms.mvp.IView;
import com.rong.common.arms.mvp.MessageType;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by jess on 17/10/2016 10:09
 * Contact with jess.yan.effort@gmail.com
 */

public class PermissionUtil {
    public static final String TAG = "Permission";

    public interface RequestPermission {
        void onRequestPermissionSuccess();
    }

    public static abstract class PermissionObserver implements Observer<Boolean>{

        private IView view;
        public PermissionObserver(IView view) {
            this.view = view;
        }
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onError(@NonNull Throwable e) {

            if(view != null) {
                view.showMessage(e.getMessage(), MessageType.TOAST_NORMAL);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    /**
     * 请求摄像头权限
     */
    public static void launchCamera(final RequestPermission requestPermission, Activity activity, final IView view) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        //先确保是否已经申请过摄像头，和写入外部存储的权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                        rxPermissions
                                .isGranted(Manifest.permission.CAMERA);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.CAMERA)
                    .subscribe(new PermissionObserver(view) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                DevUtil.d(TAG, "request WRITE_EXTERNAL_STORAGE and CAMERA success");
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                view.showMessage("request permissons failure", MessageType.TOAST_WARNING);
                            }
                        }
                    });
        }
    }



    /**
     * 请求外部存储的权限
     */
    public static void externalStorage(final RequestPermission requestPermission, Activity activity, final IView view) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        //先确保是否已经申请过摄像头，和写入外部存储的权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new PermissionObserver(view) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                view.showMessage("request permissons failure", MessageType.TOAST_WARNING);
                            }
                        }
                    });
        }
    }



    /**
     * 请求发送短信权限
     */
    public static void sendSms(final RequestPermission requestPermission, Activity activity, final IView view) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        //先确保是否已经申请过权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.SEND_SMS);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.SEND_SMS)
                    .subscribe(new PermissionObserver(view) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                view.showMessage("request permissons failure", MessageType.TOAST_WARNING);
                            }
                        }
                    });
        }
    }


    /**
     * 请求打电话权限
     */
    public static void callPhone(final RequestPermission requestPermission, Activity activity, final IView view) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        //先确保是否已经申请过权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.CALL_PHONE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.CALL_PHONE)
                    .subscribe(new PermissionObserver(view) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                view.showMessage("request permissons failure", MessageType.TOAST_WARNING);
                            }
                        }
                    });
        }
    }


    /**
     * 请求获取手机状态的权限
     */
    public static void readPhonestate(final RequestPermission requestPermission, Activity activity, final IView view) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        //先确保是否已经申请过权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.READ_PHONE_STATE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.READ_PHONE_STATE)
                    .subscribe(new PermissionObserver(view) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                            }
                        }
                    });
        }
    }

}

