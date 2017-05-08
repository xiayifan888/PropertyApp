package com.glory.bianyitong.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class ImageUtil {
    public final static String cachePath = Environment.getExternalStorageDirectory().getPath() + "/glory_bianyitong/cache/";

    /**
     * 图片保存到SdCard中
     *
     * @param --cachePath
     * @param imageURL
     * @param bitmap
     */
    public static void saveImageToSdCard(String imageURL, Bitmap bitmap) {
        File dir = new File(cachePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File bitmapFile = new File(cachePath + convertUrlToFileName(imageURL));
        if (!bitmapFile.exists()) {
            try {
                bitmapFile.createNewFile();
            } catch (IOException e) {
            }
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
        }
    }

    /**
     * 从SdCard中取图片
     *
     * @param --cachePath
     * @param imageURL
     * @return
     */
    public static Bitmap getImageFromSdCard(String imageURL) {
        String bitmapName = cachePath + convertUrlToFileName(imageURL);
        Bitmap bitmap = null;
        if (new File(bitmapName).exists()) {
            bitmap = BitmapFactory.decodeFile(bitmapName);
        }
        return bitmap;
    }

    /**
     * 获取图片名包括后缀
     *
     * @param url
     * @return
     */
    public static String convertUrlToFileName(String url) {
        if (!url.endsWith("/")) {
            return url.substring(url.lastIndexOf("/") + 1);
        }
        return "";
    }

    /**
     * 从SdCard中判断有无图片
     *
     * @param --cachePath
     * @param imageURL
     * @return
     */
    public static boolean ImageFromSdCardExist(String imageURL) {
        String bitmapName = cachePath + convertUrlToFileName(imageURL);
        if (new File(bitmapName).exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 图片保存到内存中
     *
     * @param --cachePath
     * @param imageURL
     * @param bitmap
     */
    public static void saveImageToData(String imageURL, Bitmap bitmap, Context context) {
        try {
            FileOutputStream outStream = context.openFileOutput(convertUrlToFileName(imageURL),
                    Context.MODE_WORLD_READABLE);
            Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG;
            bitmap.compress(localCompressFormat, 100, outStream);
            outStream.close();
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 内存中取图片
     *
     * @param --cachePath
     * @param imageURL
     * @param- bitmap
     */
    public static Bitmap getImageFromData(String imageURL, Context context) {
        try {
            String localIconNormal = convertUrlToFileName(imageURL);
            FileInputStream localStream = context.openFileInput(localIconNormal);
            Bitmap bitmap = BitmapFactory.decodeStream(localStream);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 下载图
     *
     * @param -path
     */
    public static void downImg(final Context context, final String[] urls) {
        final boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        for (int i = 0; i < urls.length; i++) {
            String path = urls[i];
            boolean download = false;
            if (sdCardExist) {
                if (!ImageFromSdCardExist(path) && null == getImageFromData(path, context)) {
                    //如果sdcard和内存没有图片则下载
                    download = true;
                }
            } else {
                if (null == getImageFromData(path, context)) {
                    //如果内存没有图片则下载
                    download = true;
                }
            }
            if (download) {
                byte[] data = null;
                try {
                    data = getImage(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (data != null && data.length != 0) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                    if (sdCardExist) {
                        saveImageToSdCard(path, tempBitmap);
                    } else {
                        saveImageToData(path, tempBitmap, context);
                    }
                    tempBitmap = null;
                }
            }

        }
    }

    /**
     * 获取图片bitmap
     *
     * @param imageURL
     * @param context
     * @return
     */
    public static Bitmap getImg(String imageURL, Context context) {
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        if (sdCardExist) {
            if (ImageFromSdCardExist(imageURL)) {
                return getImageFromSdCard(imageURL);
            } else
                return getImageFromData(imageURL, context);
        } else {
            return getImageFromData(imageURL, context);
        }
    }

    /**
     * 通过输入url得到图片字节数组
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static byte[] getImage(String path) throws Exception {

        //		URL url = new URL(HttpUtils.replaceUrl(path));
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 6];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    //path为下载路径,saveName是保存名称可以是任何文件
    public static void getImage(String path, String saveName) throws Exception {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000 * 6);
        if (con.getResponseCode() == 200) {
            InputStream inputStream = con.getInputStream();
            byte[] b = getByte(inputStream);
            File file = new File(convertUrlToFileName(saveName));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(b);
            fileOutputStream.close();
        }
    }

    private static byte[] getByte(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 读取SD卡中文本文件
     *
     * @param fileName
     * @return
     */
    public static FileInputStream readSDFile(String fileName) {
        File file = new File(convertUrlToFileName(fileName));
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.close();
            return fis;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fis;
    }

    // 图片按比例大小压缩方法（根据Bitmap图片压缩）：
    public static Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
//            baos.reset();//重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//这里压缩90%，把压缩后的数据存放到baos中
//        }
        if(baos.toByteArray().length / 1024 < 50){
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }else if (baos.toByteArray().length / 1024 < 100) {
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }else if(baos.toByteArray().length / 1024 < 200){
            image.compress(Bitmap.CompressFormat.JPEG, 30, baos);//这里压缩30%，把压缩后的数据存放到baos中
        }else {
            image.compress(Bitmap.CompressFormat.JPEG, 20, baos); ////20 是压缩率，表示压缩80%; 如果不压缩是100
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = 400f;//这里设置高度为800f
//        float ww = 400f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        try {
            bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        } catch (Exception e) {
            // TODO: handle exception
        }
//        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    // 质量压缩方法：
    public static Bitmap compressImage(Bitmap image) {
        Bitmap bitmap = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 200) {    //循环判断如果压缩后图片是否大于200KB,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        try {
            bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bitmap;
    }

    //将压缩的bitmap保存到sdcard卡临时文件夹pic，用于上传
    public static String saveMyBitmap(String filename, Bitmap bit) {
        String imageurl = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/gloryPhoto/" + filename);
            if (!file.exists()) {
                Log.i("FileBoyMap", "---file1---" + file);
                file.getParentFile().mkdirs();
                file.createNewFile();
                Log.i("FileBoyMap", "---file2---" + file);
            }
            OutputStream stream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/gloryPhoto/" + filename);
            bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            imageurl = file.toString();
            Log.i("FileBoyMap", "---imageurl---" + imageurl);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageurl;
    }

    public static void deleteFile() {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/gloryPhoto/");
//			if (file.exists()) { // 判断文件是否存在
//				if (file.isFile()) { // 判断是否是文件
//					file.delete(); // delete()方法 你应该知道 是删除的意思;
//				} else if (file.isDirectory()) { // 否则如果它是一个目录
//					File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
//					for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
//						deleteFile(); // 把每个文件 用这个方法进行迭代
//					}
//				}
//				file.delete();
//			} else {
//				Log.i("FileBoyMap", "----文件不存在!");
//			}
            deleteDir(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
