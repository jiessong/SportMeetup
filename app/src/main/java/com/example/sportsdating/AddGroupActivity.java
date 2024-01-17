package com.example.sportsdating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sportsdating.activities.model.SportActivity;
import com.example.sportsdating.activities.service.SportActivityService;
import com.example.sportsdating.user.UserMgr;

public class AddGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        EditText title = (EditText) findViewById(R.id.et_input_activity);
        EditText time = (EditText) findViewById(R.id.et_input_date);
        EditText location = (EditText) findViewById(R.id.et_input_location);
        Button button = (Button) findViewById(R.id.button_moment_post);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer userId = UserMgr.getInstance().getCurrentUserState().getUser().getId();
                String tl = title.getText().toString();
                String tm = time.getText().toString();
                String loc = location.getText().toString();
                SportActivity sportActivity = new SportActivity();
                sportActivity.setCreatorId(userId);
                if (tl.length() == 0 || tm.length() == 0 || loc.length() == 0) {
                    Toast toast = Toast.makeText(AddGroupActivity.this, "something is empty",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 50, 50);
                    toast.show();
                } else {
                    sportActivity.setTime(tm);
                    sportActivity.setTitle(tl);
                    sportActivity.setLocation(loc);
                    SportActivityService service = SportActivityService.getInstance();
                    service.saveActivity(sportActivity);
                    Toast toast = Toast.makeText(AddGroupActivity.this, "create successfully",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 50, 50);
                    toast.show();
                }

            }
        });
    }
}