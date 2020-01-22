package com.luck.picture.lib;

/**
 * PictureSelectionModel拓展类
 * <p>
 * 主要是解除PictureSelectionModel的包装，来拓展特性
 * <p>
 * 项目中需要拓展PictureSelectionModel可继承于该类 然后做相对应的自定义操作 如 添加跳转界面的策略 添加跳转动画等
 * <p>
 *
 * @author Zhao
 * @date 2017/12/14
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
        return ext;
    }


    public PictureSelectionModel model;

}
