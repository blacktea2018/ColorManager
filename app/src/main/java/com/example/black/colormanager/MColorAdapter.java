package com.example.black.colormanager;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MColorAdapter extends ArrayAdapter<MColor> {

    private int resourceId;
    public MColorAdapter(Context context, int textViewResourceId, List<MColor> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MColor mColor = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        final Button colorName = view.findViewById(R.id.color_name);
        colorName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(colorName.getText().toString().split(":")[1]);
                Toast.makeText(getContext(), "已复制至剪切板", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        colorName.setText(mColor.getName());
        colorName.setBackgroundColor(Color.parseColor(mColor.getName().split(":")[1]));
        return view;
    }
}
