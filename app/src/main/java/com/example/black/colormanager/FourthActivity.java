package com.example.black.colormanager;

import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Toast;

import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private de.hdodenhof.circleimageview.CircleImageView QQImage;

    long[] mHits = new long[5];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        QQImage = findViewById(R.id.QQ_image);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };

        QQImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - mHits[0] <= 1000) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(FourthActivity.this);
                    dialog.setTitle("彩蛋！");
                    dialog.setMessage("我们可以交流一下，QQ：1065833328");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("联系一下", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (isQQClientAvailable(FourthActivity.this)) {
                                Intent intent = new Intent();
                                ComponentName componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.HomeActivity");
                                intent.setAction(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setComponent(componentName);
                                startActivity(intent);
                            } else {
                                Toast.makeText(FourthActivity.this, "未安装QQ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.setNegativeButton("不敢不敢", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
            }
        });
        QQImage.setOutlineProvider(viewOutlineProvider);
    }

    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo =packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
}
