package com.anuode.build_andriod.modules.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;


public class IntentCenter {


    /**
     * 打开相册Intent
     *
     * @return Intent
     */
    public static Intent getAlbumIntent(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        return intent;
    }

    /**
     * 打开相机Intent
     *
     * @param dst 保存相片的文件
     * @return Intent
     */
    public static Intent getCameraIntent(Uri dst){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //相机照片存入dstUri对应的文件。
        intent.putExtra(MediaStore.EXTRA_OUTPUT, dst);
//		intent.putExtra("camerasensortype", 2); // 调用前置摄像头
        intent.putExtra("autofocus", true); // 自动对焦
        intent.putExtra("fullScreen", false); // 全屏
        intent.putExtra("showActionIcons", false);
        return intent;
    }

    /**
     * 打开裁图工具的Intent
     *
     * @param src 被裁剪的图片
     * @param dst 裁剪后保存的图片
     * @return Intent
     */
    public static Intent getClipperIntent(Uri src,Uri dst){
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(src, "image/*");	// src 图片源
        intent.putExtra("output", dst);	// mUriAvatar 裁剪后图片
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);	// 横向拉伸比例
        intent.putExtra("aspectY", 1);
//		intent.putExtra("outputX", VCardActivity.AVATAR_WIDTH);	// 横向输出值
//		intent.putExtra("outputY", VCardActivity.AVATAR_WIDTH);
        intent.putExtra("scale", true);	// 是否拉伸
        intent.putExtra("return-data", false);	// Uri获取图片
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());	//输出格式
        intent.putExtra("noFaceDetection", true); // 不进行人脸识别

        return intent;
    }
}
