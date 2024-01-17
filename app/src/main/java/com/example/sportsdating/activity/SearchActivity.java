package com.example.sportsdating.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sportsdating.R;
import com.example.sportsdating.activities.model.SportActivity;
import com.example.sportsdating.activities.service.SportActivityService;
import com.example.sportsdating.tokenizer.Parser;
import com.example.sportsdating.tokenizer.Tokenizer;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
/**
 * @Author Ke Yan
 */
public class SearchActivity extends AppCompatActivity {

    private EditText inputText;
    private List<SportActivity> activitiesList = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private Button addTeamBtn;
    private ImageButton imageButton_search;
    private RecyclerView recyclerView_groupup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(this, "activity_main", Toast.LENGTH_SHORT).show();

        recyclerView_groupup = findViewById(R.id.rv_groupup1);
        //TODO:make sure the getContext() is right
        //利用Adapter显示item
        recyclerView_groupup.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        //设置Adapter
        activitiesList = SportActivityService.findAllActivity();
        searchAdapter = new SearchAdapter(activitiesList);
        recyclerView_groupup.setAdapter(searchAdapter);

        //Capture the EditText
        inputText = (EditText) findViewById(R.id.searchText);

        Button searchBut = findViewById(R.id.search_bn);

        searchBut.setOnClickListener(myButtonListener);
    }

    // 3.Set an OnClickListener to button
    private final View.OnClickListener myButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(SearchActivity.this, "searching", Toast.LENGTH_SHORT).show();
            // clear previous search result
            activitiesList.clear();
            // parser for search item

                Tokenizer tokenizer= new Tokenizer(inputText.getText().toString());
                Parser parser = new Parser(tokenizer);
                parser.parseExp();
                SportActivityService sportActivityService = SportActivityService.getInstance();
                // search list
                List<SportActivity> allActivitiesList = SportActivityService.findAllActivity();
                System.out.println(parser.output());

                for (SportActivity activity: allActivitiesList) {
                    System.out.println(activity.getTitle());
                    System.out.println(activity.getTitle());
//                    System.out.println(parser.output().get("title").get(0));
//                    System.out.println(activity.getTitle().equals(parser.output().get("title").get(0)));
                    if (parser.output().get("title").size() > 0) {
                        if (activity.getTitle().toLowerCase(Locale.ROOT).equals(parser.output().get("title").get(0))) {
                            if (parser.output().get("location").size() > 0) {
                                if (activity.getLocation().toLowerCase(Locale.ROOT).equals(parser.output().get("location").get(0))) {
                                    if (parser.output().get("time").size() > 0) {
                                        if (activity.getTime().toLowerCase(Locale.ROOT).equals(parser.output().get("time").get(0)))
                                            activitiesList.add(activity);
                                    } else activitiesList.add(activity);
                                }
                            } else activitiesList.add(activity);
                        }

                    } else if (parser.output().get("location").size() > 0) {
                        if (activity.getLocation().toLowerCase(Locale.ROOT).equals(parser.output().get("location").get(0))) {
                            if (parser.output().get("time").size() > 0) {
                                if (activity.getTime().toLowerCase(Locale.ROOT).equals(parser.output().get("time").get(0)))
                                    activitiesList.add(activity);
                            } else activitiesList.add(activity);
                        }
                    } else if (parser.output().get("time").size() > 0) {
                        if (activity.getLocation().toLowerCase(Locale.ROOT).equals(parser.output().get("time").get(0))) {
                            activitiesList.add(activity);
                        }
                    }
                }
                System.out.println(activitiesList);
                // clear the EditText
                // inputText.setText("");
                //notify the ArrayAdapter that the array was updated.
                searchAdapter.notifyDataSetChanged();

            }


//            if (parser.output().get("title").size() != 0) {
//                for (String activity: Objects.requireNonNull(parser.output().get("title"))) {
//                    sportActivities.addAll(sportActivityService.findActivityBySportType(activity));
//                    System.out.println(sportActivities);
//                }
//            } else {
//                sportActivities = new HashSet<>(sportActivities);
//                System.out.println(sportActivities);
//            }
//            if (parser.output().get("time").size() != 0) {
//                System.out.println(allActivitiesList);
//                for (SportActivity activity: sportActivities) {
//                    // search time item
//                    if (parser.output().get("time").contains(activity.getTime())) {
//                        activitiesList.add(activity);
//                    }
//                }
//            }
//            for (SportActivity activity: activitiesList) {
//                if (parser.output().get("location").size() == 0) {
//                    activitiesList.addAll(sportActivities);
//                }
//                if (!parser.output().get("location").contains(activity.getLocation())) {
//                    activitiesList.remove(activity);
//                }
//            }


    };
}
