package com.luck.picture.lib;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import android.util.Log;

import com.luck.picture.lib.config.PictureSelectionConfig;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * PictureSelectionModel拓展类
 * <p>
 * 主要是解除PictureSelectionModel的包装，来拓展特性
 * <p>
 * 项目中需要拓展PictureSelectionModel可继承于该类 然后做相对应的自定义操作 如 添加跳转界面的策略 添加跳转动画等
 * <p>
 * Created by Zhao on 2017/12/14.
 */
public class PictureSelectionModelExt {

    public void forResult(int requestCode) {
        model.forResult(requestCode);
    }

    /**
     * 使用反射的方式 获取到PictureSelectionModel的属性 来无损创建拓展PictureSelectionModel类
     *
     * @param model
     * @return
     */
    public static PictureSelectionModelExt create(PictureSelectionModel model) {

        PictureSelectionModelExt ext = new PictureSelectionModelExt();
        ext.model = model;

        try {
            Class clazz = Class.forName(PictureSelectionModel.class.getName());
            //getDeclaredFields();返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段。
            Field[] fields = clazz.getDeclaredFields();

            for (Field f : fields) {//将属性设置为可见
                f.setAccessible(true);
//                Log.d(TAG, "field name:" + f.getName());
//                Log.d(TAG, "field value:" + f.get(model));

                Object o = f.get(model);

                if (o instanceof PictureSelector) {
                    ext.selector = (PictureSelector) o;
                }

                if (o instanceof PictureSelectionConfig) {
                    ext.selectionConfig = (PictureSelectionConfig) o;
                }
            }

            if (ext.selector != null) {
                if (ext.selector.getActivity() != null) {
                    ext.activity = new WeakReference<Activity>(ext.selector.getActivity());
                }

                if (ext.selector.getFragment() != null) {
                    ext.fragment = new WeakReference<Fragment>(ext.selector.getFragment());
                }
            }
        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return ext;
    }

    public Activity getActivity() {
        if (activity == null) {
            return null;
        }

        return activity.get();
    }


    public Fragment getFragment() {
        if (fragment == null) {
            return null;
        }

        return fragment.get();
    }


    public PictureSelectionModel model;
    public PictureSelector selector;
    public PictureSelectionConfig selectionConfig;

    protected WeakReference<Activity> activity;
    protected WeakReference<Fragment> fragment;


    public static final class PictureSelectorType {
        public static final int NORMAL = 0;
        public static final int APP_ICON = 1;
    }

    private static final String TAG = "PSMExt";

}
