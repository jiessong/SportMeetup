package com.example.sportsdating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsdating.post.dao.PostDAOImpl;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.model.User;

public class PostActivity extends AppCompatActivity {

    Button btn_post ;
    TextView tv_cancelpost ;
    ImageView post_iv_photo;
    TextView pt_postWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btn_post = findViewById(R.id.button_moment_post);
        btn_post.setOnClickListener(button_post_Listener);

        tv_cancelpost = findViewById(R.id.tv_createTeam);
        tv_cancelpost.setOnClickListener(tv_cancelpost_Listener);
        pt_postWords = (TextView)findViewById(R.id.pt_postWords);

        //TODO:sent the photo to post datafile
        post_iv_photo = findViewById(R.id.post_iv_photo);

        post_iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:在相册中，或本地文件中 添加图片
                Toast.makeText(PostActivity.this, "please add images on your phone ... ", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(),MomentsActivity.class);
//                startActivity(intent);
            }
        });



    }


    private View.OnClickListener button_post_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            User user = UserMgr.getInstance().getCurrentUserState().getUser();
            PostDAOImpl postDAOImp = PostDAOImpl.getInstance();
            String post = pt_postWords.getText().toString();
            if(user!=null && post.length()>0){
                Post daoPost = postDAOImp.createPost(post, user);
                Toast.makeText(PostActivity.this, "Successfully created a post", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    };

    private View.OnClickListener tv_cancelpost_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(PostActivity.this, "btn_post tv_cancelpost clicked", Toast.LENGTH_SHORT).show();

            //TODO:MainActivity --> MomentsFragment
            Intent intent = new Intent(getApplicationContext(),MomentsActivity.class);
            startActivity(intent);
        }
    };
}