package com.example.black.colormanager;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button_color;

    private TextView color_Text;

    private SeekBar seekBar_A;

    private SeekBar seekBar_R;

    private SeekBar seekBar_G;

    private SeekBar seekBar_B;

    private String colorvalue_A = "0";

    private String colorvalue_R = "0";

    private String colorvalue_G = "0";

    private String colorvalue_B = "0";

    private String text_colorvalue_A = "255";

    private String text_colorvalue_R = "255";

    private String text_colorvalue_G = "255";

    private String text_colorvalue_B = "255";

    private EditText edit_A;

    private EditText edit_R;

    private EditText edit_G;

    private EditText edit_B;

    private ImageButton imageButton;

    private DrawerLayout mDrawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        View actionbarLayout = LayoutInflater.from(this).inflate(
                R.layout.actionbar_layout, null);

        button_color = findViewById(R.id.button_color);
        color_Text = findViewById(R.id.text_color);
        seekBar_A = findViewById(R.id.seekbar_A);
        seekBar_R = findViewById(R.id.seekbar_R);
        seekBar_G = findViewById(R.id.seekbar_G);
        seekBar_B = findViewById(R.id.seekbar_B);

        OnseekBarChangeListener onseekBarChangeListener = new OnseekBarChangeListener();
        seekBar_A.setOnSeekBarChangeListener(onseekBarChangeListener);
        seekBar_R.setOnSeekBarChangeListener(onseekBarChangeListener);
        seekBar_G.setOnSeekBarChangeListener(onseekBarChangeListener);
        seekBar_B.setOnSeekBarChangeListener(onseekBarChangeListener);

        edit_A = findViewById(R.id.edit_A);
        edit_R = findViewById(R.id.edit_R);
        edit_G = findViewById(R.id.edit_G);
        edit_B = findViewById(R.id.edit_B);

        edit_A.setSelection(edit_A.getText().length());
        edit_R.setSelection(edit_R.getText().length());
        edit_G.setSelection(edit_G.getText().length());
        edit_B.setSelection(edit_B.getText().length());

        imageButton = actionbarLayout.findViewById(R.id.drawer_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START))
                    mDrawerLayout.closeDrawer(Gravity.START);
                else
                    mDrawerLayout.openDrawer(Gravity.START);
            }
        });

        button_color.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(color_Text.getText().toString());
                Toast.makeText(MainActivity.this, "已复制至剪切板", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.color_chose);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.color_chose:
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.photo:
                        Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.color_select:
                        Intent intent3 = new Intent(getApplicationContext(), ThirdActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.QQ:
                        Intent intent4 = new Intent(getApplicationContext(), FourthActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        edit_A.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolEdit();
                setSeekBar(edit_A);
                edit_A.setSelection(edit_A.getText().length());
            }
        });

        edit_R.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolEdit();
                setSeekBar(edit_R);
                edit_R.setSelection(edit_R.getText().length());
            }
        });

        edit_G.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolEdit();
                setSeekBar(edit_G);
                edit_G.setSelection(edit_G.getText().length());
            }
        });

        edit_B.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolEdit();
                setSeekBar(edit_B);
                edit_B.setSelection(edit_B.getText().length());
            }
        });

        mDrawerLayout = findViewById(R.id.drawer_layout);

        actionBar.setCustomView(actionbarLayout, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    private class OnseekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            switch (seekBar.getId()) {
                case R.id.seekbar_A:
                    colorvalue_A = Integer.toHexString(seekBar_A.getProgress());
                    break;
                case R.id.seekbar_R:
                    colorvalue_R = Integer.toHexString(seekBar_R.getProgress());
                    break;
                case R.id.seekbar_G:
                    colorvalue_G = Integer.toHexString(seekBar_G.getProgress());
                    break;
                case R.id.seekbar_B:
                    colorvalue_B = Integer.toHexString(seekBar_B.getProgress());
                    break;
            }
            if (colorvalue_A.length() == 1)
                colorvalue_A = "0" + colorvalue_A;
            if (colorvalue_R.length() == 1)
                colorvalue_R = "0" + colorvalue_R;
            if (colorvalue_G.length() == 1)
                colorvalue_G = "0" + colorvalue_G;
            if (colorvalue_B.length() == 1)
                colorvalue_B = "0" + colorvalue_B;

            text_colorvalue_A = "ff";
            text_colorvalue_R = Integer.toHexString(255 - seekBar_R.getProgress());
            text_colorvalue_G = Integer.toHexString(255 - seekBar_G.getProgress());
            text_colorvalue_B = Integer.toHexString(255 - seekBar_B.getProgress());

            if (text_colorvalue_R.length() == 1)
                text_colorvalue_R = "0" + text_colorvalue_R;
            if (text_colorvalue_G.length() == 1)
                text_colorvalue_G = "0" + text_colorvalue_G;
            if (text_colorvalue_B.length() == 1)
                text_colorvalue_B = "0" + text_colorvalue_B;

            String color_number = colorvalue_A + colorvalue_R + colorvalue_G + colorvalue_B;
            String text_color_number = text_colorvalue_A + text_colorvalue_R + text_colorvalue_G +
                    text_colorvalue_B;
            color_Text.setText("#" + color_number);
            button_color.setTextColor(Color.parseColor("#" + text_color_number));
            button_color.setBackgroundColor(Color.parseColor("#" + color_number));

            edit_A.setText(seekBar_A.getProgress() + "");
            edit_R.setText(seekBar_R.getProgress() + "");
            edit_G.setText(seekBar_G.getProgress() + "");
            edit_B.setText(seekBar_B.getProgress() + "");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private void boolEdit () {

        int a, r, g, b;

        if (edit_A.getText().toString().equals(""))
            a = 0;
        else
            a = Integer.parseInt(edit_A.getText().toString());
        if (edit_R.getText().toString().equals(""))
            r = 0;
        else
            r = Integer.parseInt(edit_R.getText().toString());
        if (edit_G.getText().toString().equals(""))
            g = 0;
        else
            g = Integer.parseInt(edit_G.getText().toString());
        if (edit_B.getText().toString().equals(""))
            b = 0;
        else
            b = Integer.parseInt(edit_B.getText().toString());

        if (a > 255)
            edit_A.setText("255");
        if (r > 255)
            edit_R.setText("255");
        if (g > 255)
            edit_G.setText("255");
        if (b > 255)
            edit_B.setText("255");
    }

    private void setSeekBar (EditText editText) {

        int a, r, g, b;
        if (edit_A.getText().toString().equals(""))
            a = 0;
        else
            a = Integer.parseInt(edit_A.getText().toString());
        if (edit_R.getText().toString().equals(""))
            r = 0;
        else
            r = Integer.parseInt(edit_R.getText().toString());
        if (edit_G.getText().toString().equals(""))
            g = 0;
        else
            g = Integer.parseInt(edit_G.getText().toString());
        if (edit_B.getText().toString().equals(""))
            b = 0;
        else
            b = Integer.parseInt(edit_B.getText().toString());
        switch (editText.getId()) {
            case R.id.edit_A:
                seekBar_A.setProgress(a);
                break;
            case R.id.edit_R:
                seekBar_R.setProgress(r);
                break;
            case R.id.edit_G:
                seekBar_G.setProgress(g);
                break;
            case R.id.edit_B:
                seekBar_B.setProgress(b);
                break;
        }
    }
}