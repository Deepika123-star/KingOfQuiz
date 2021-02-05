package com.smartwebart.kingofquiz.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class UsefullMethods {

    public static final String FOR = "for";
    public static final int FOR_SERVICE_REQUEST = 1;
    public static final int FOR_SCHEDULED_SERVICE = 2;
    public static final int FOR_SERVICE_HISTORY = 3;

    public static final String[] permissionsForTakingImage = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };


    public static void startActivity(Activity from, Class to, int requestcode) {
        Intent intent = new Intent(from, to);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        from.startActivityForResult(intent, 107);
    }

    public static void startActivityWithFinish(Activity from, Activity to) {
        Intent intent = new Intent(from, to.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        from.startActivity(intent);
        from.finish();
    }

    public static void startActivityWithFinishAffinity(Activity from, Class to) {
        Intent intent = new Intent(from, to);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        from.startActivity(intent);
        from.finishAffinity();
    }

    public static void showMessage(Activity from, int type, String title, String content, String confirmationtext, MyClick callBackResponse) {
        new SweetAlertDialog(from, type)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirmationtext)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        if (callBackResponse !=null)
                        callBackResponse.onClick();
                    }
                });
                //.show();
    }



    public static void show_questionAnswerAlert(Activity from, int type, String title, String content, String confirmationtext, MyClick callBackResponse) {
        new SweetAlertDialog(from, type)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirmationtext)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        if (callBackResponse !=null)
                            callBackResponse.onClick();
                    }
                })
                .show();
    }







    public static boolean checkPermissions(Activity context)
    {

        int result = ContextCompat.checkSelfPermission(context,READ_EXTERNAL_STORAGE );
        int result0 = ContextCompat.checkSelfPermission(context,WRITE_EXTERNAL_STORAGE );
        int result1 = ContextCompat.checkSelfPermission(context, CAMERA);
        return result == PackageManager.PERMISSION_GRANTED && result0 == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    public static void requestPermission(Activity context, int requestCode)
    {
            ActivityCompat
                    .requestPermissions(
                            context,
                            permissionsForTakingImage,
                            requestCode);

    }
    public static String imagePath = "";
    public static File imageFile = null;
    public static void openCamera(Context mContext, int requestCode) {

        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            Log.e("Exception", "" + e);
            e.printStackTrace();
        }
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        ((Activity) mContext).startActivityForResult(cameraIntent, requestCode);
    }
    @SuppressLint("SimpleDateFormat")
    public static File createImageFile() throws IOException {

        String getRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(getRoot + "/temp/");
        myDir.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm")
                .format(new Date());
        String imageFileName = "FX" + timeStamp;
        File image = File.createTempFile(imageFileName, ".jpg", myDir);

        return image;
    }



    public static boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }



    public static int Orientation(File f) {
        int angle = 0;
        try {
            ExifInterface exif = new ExifInterface(f.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);



            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            }
            else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            }
            else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return angle;
    }

    public static String getCurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
//        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String localTime = date.format(currentTime);
        return localTime;
    }

    public static String getDiffrenceOfTime(String start_time, String end_time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");

        Date date1 = null;
        Date date2 = null;
        Log.e("Time====",start_time+"::::"+end_time);
        try {
//          date1 = simpleDateFormat.parse("08:00 AM");
            date1 = simpleDateFormat.parse(start_time);
//          date2 = simpleDateFormat.parse("04:00 PM");
            date2 = simpleDateFormat.parse(end_time);
            long difference = date2.getTime() - date1.getTime();
            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            hours = (hours < 0 ? -hours : hours);
            Log.e("======= Hours"," :: "+hours+"::"+ min);
           String h=String.valueOf(hours);
           String m=String.valueOf(min);
          return h+"hours "+m+"minute";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }


}
