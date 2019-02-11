package com.example.black.colormanager;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 10;

    private Button image_button;

    private ImageView image_view;


    private Bitmap bitmap, bitmap2;

    private Button button_color2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image_view = findViewById(R.id.image_view);
        image_button = findViewById(R.id.image_button);
        button_color2 = findViewById(R.id.button_color2);

        button_color2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(button_color2.getText().toString());
                if (!button_color2.getText().equals("长按复制至剪切板"))
                    Toast.makeText(SecondActivity.this, "已复制至剪切板", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "未选取图片", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.
                        WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum ();
                }
            }
        });

        image_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap2 = ((BitmapDrawable)image_view.getDrawable()).getBitmap();

                    int x = (int)event.getX();
                    int y = (int)event.getY();
                    int color = bitmap2.getPixel(x, y);

                    int a, r, g, b;
                    a = Color.alpha(color);
                    r = Color.red(color);
                    g = Color.green(color);
                    b = Color.blue(color);

                    String color_A = Integer.toHexString(a);
                    String color_R = Integer.toHexString(r);
                    String color_G = Integer.toHexString(g);
                    String color_B = Integer.toHexString(b);

                    String color_number ="#" + color_A + color_R + color_G + color_B;
                    button_color2.setText(color_number);
                    button_color2.setBackgroundColor(Color.parseColor(color_number));
                }

                return true;
            }
        });
    }

    private void openAlbum () {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent
                ,PackageManager.MATCH_DEFAULT_ONLY);
        if(resolveInfos.size() == 0){
            Toast.makeText(this, "无法打开你的相册？？？", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "您没有给予相关权限", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19)
                        handleImageOnKitKat (data);
                    else
                        handleImageBeforeKitKat (data);
                }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat (Intent data) {

        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath (contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath (uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage (imagePath);
    }

    private void handleImageBeforeKitKat (Intent data) {

        Uri uri = data.getData();
        String imagePath = getImagePath (uri, null);
        displayImage (imagePath);
    }

    private String getImagePath (Uri uri, String selection) {

        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage (String imagePath) {

        if (imagePath != null) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);

            options.inSampleSize = calculateInSampleSize(options, 500, 800);

            options.inJustDecodeBounds = false;

            image_view.setImageBitmap(BitmapFactory.decodeFile(imagePath, options));
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
