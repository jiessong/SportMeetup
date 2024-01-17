package com.example.sportsdating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MineActivity extends AppCompatActivity {
    ImageButton imageButton_setting;
    ListView minelistView;
    EditText inputText;
    ArrayList<String> myArray = new ArrayList<>();
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine);
        // 1.capture button,Create onClick listener
        imageButton_setting = findViewById(R.id.imageButton_setting);
        imageButton_setting.setOnClickListener(myimageButtonListener);
//        //2 Add iterms to the listview Arraylist
        myArray.add("Personal Infomation");
        myArray.add("My Group");
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myArray);
        //TODO:
        minelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String curURL = myArray.get(i);
                if (curURL.isEmpty()){
                    Toast.makeText(MineActivity.this, "URL is invalid!", Toast.LENGTH_SHORT).show();
                }else {
                    //MainActivity --> ActivityWeb
                    Intent intentToWeb = new Intent(getApplicationContext(),MineActivity.class);
                    intentToWeb.putExtra("URL",curURL);
                    startActivity(intentToWeb);
                }
            }
        });
        minelistView.setAdapter((ListAdapter) myAdapter);
        Toast.makeText(this, "myURL_List listener", Toast.LENGTH_SHORT).show();

        //Capture the EditText
        inputText = (EditText) findViewById(R.id.searchText);
    }


    // 3.Set an OnClickListener to button
    // This listener should get the content written on the EditText view and insert into the ListView
    private View.OnClickListener myimageButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //This listener should get the content written on the EditText view and insert into the ListView.
            Toast.makeText(MineActivity.this, "add_URL_bn is clicked", Toast.LENGTH_SHORT).show();

            // add the content of the EditText view to an array
            myArray.add(inputText.getText().toString());
            // clear the EditText
            inputText.setText("");
            //notify the ArrayAdapter that the array was updated.
            myAdapter.notifyDataSetChanged();
        }
    };
}