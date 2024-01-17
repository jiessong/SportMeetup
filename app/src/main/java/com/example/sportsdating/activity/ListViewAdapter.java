package com.example.sportsdating.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.Base64;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<String> inputsURL;
    private Context context;
    //connect with mainActivity 传递list
    LayoutInflater inflater;

    public ListViewAdapter(List<String> inputsURL, Context context) {
        this.inputsURL = inputsURL;
        this.context = context;
    }

    @Override
    public int getCount() {
        return inputsURL.size();
    }

    @Override
    public Object getItem(int i) {
        return inputsURL.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //show ListView of each item
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        View view = inflater.inflate(R.layout.activity_main,null);
//        EditText editText = view.findViewById(R.id.inputText);
//        editText.setText(inputsURL.get(i).toString());
        return null;
    }
}
