package com.kit.pictureselectorext;

import android.app.Activity;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.PictureSelectionModelExt;

/**
 * 使用示例manager管理类做相应的配置项操作
 * <p>
 * <p>
 * Created by Zhao on 2017/3/19.
 */
public class PictureSelectorManager {

    private static PictureSelectorManager pictureSelectorManager;

    public static PictureSelectorManager getInstance() {
        if (pictureSelectorManager == null) {
            pictureSelectorManager = new PictureSelectorManager();
        }

        return pictureSelectorManager;
    }


    /**
     * 使用示例
     * <p>
     * 实际上是用PictureSelectionModelExt来做界面跳转 自定义参数之类 的 自定义特性
     *
     * @param activity
     * @param mimeType
     * @param width
     * @param height
     * @param isShowCamera
     * @param enablePreview
     * @param enableCrop
     * @return
     */
    public PictureSelectionModelExt getPicSelector(Activity activity, int mimeType, int width, int height, boolean isShowCamera, boolean enablePreview, boolean enableCrop) {

        PictureSelector selector = PictureSelector.create(activity);
        PictureSelectionModel model = selector.openGallery(mimeType) //1图片 or 2视频 LocalMediaLoader.TYPE_IMAGE,TYPE_VIDEO
//                .cropWH(width, height) //裁剪大小
                .withAspectRatio(width, height)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .compress(true) //是否压缩
                .minimumCompressSize(2 * 1024)
                .maxSelectNum(1) //最大可选数量
                .selectionMode(PictureConfig.SINGLE)//2单选 or 1多选 MODE_MULTIPLE MODE_SINGLE
                .isCamera(isShowCamera) //是否显示相机
                .previewImage(enablePreview)// 是否预览
                .enableCrop(enableCrop); //是否裁剪

        return PictureSelectionModelExt.create(model);

    }

}
