package com.anuode.build_andriod.modules.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileUtil {
    public static final String ROOT_SD = Environment.getExternalStorageDirectory().getPath();
    public static final String DIR_CAMPUS = ROOT_SD + "/campus";
    public static final String DIR_IMAGES = DIR_CAMPUS + "/images";

    public static boolean initDirs() {
        String[] pashs={DIR_CAMPUS,DIR_IMAGES};
        boolean b=false;
        for(String path:pashs){
            File file = new File(path);
            if(!file.exists()){
                b= file.mkdirs();
            }

        }
        return b;
    }
    public static void copt(String src,String dst){
        if(!TextUtils.isEmpty(src)||!TextUtils.isEmpty(dst)){
            byte[] buffer=new byte[1024];
            try {
                FileInputStream fis=new FileInputStream(src);
                FileOutputStream fos=new FileOutputStream(dst);
                int len =0;
                while((len =fis.read(buffer))!=-1){
                    fos.write(buffer,0,len);
                    System.out.println("file");
                }
                fis.close();
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

    }
    public static File[] getFils(){
        File file=new File(DIR_IMAGES);
        return file.listFiles();
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(DIR_IMAGES);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File myCaptureFile = new File(DIR_IMAGES + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        bos.flush();
        bos.close();
    }

    /**
     * 根据相册中图片在数据库中的uri获取图片的真实地址
     * @param uri
     * @param context
     * @return
     */
    public static String getPath(Uri uri, Context context){
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(uri, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String picturePath = c.getString(columnIndex);
        c.close();
        return picturePath;
    }

    /**
     * 编码url中的中文
     */
    public static String enCodeUrl (String url){
        String path_ ,tempPath_ = "";
        try{
            path_ = URLEncoder.encode(url.substring(url.indexOf("_") + 1), "utf-8");
            tempPath_="";
//            tempPath_ = Txjc.PICHOST + url.replace(url.substring(url.indexOf("_") + 1), path_);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tempPath_;
    }
}