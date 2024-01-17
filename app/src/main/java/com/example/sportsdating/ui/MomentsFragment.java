package com.example.sportsdating.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsdating.AddGroupActivity;
import com.example.sportsdating.GroupUpAdapter;
import com.example.sportsdating.MomentPostAdapter;
import com.example.sportsdating.PostActivity;
import com.example.sportsdating.R;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.post.model.ViewPost;
import com.example.sportsdating.post.service.PostService;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MomentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MomentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MomentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MomentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MomentsFragment newInstance(String param1, String param2) {
        MomentsFragment fragment = new MomentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //TODO: Button post1;
    private RecyclerView recyclerView_post;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //capure the Button post
        Button button_post = (Button)getActivity().findViewById(R.id.button_moment_post);
        button_post.setOnClickListener(button_postListener);

        recyclerView_post = view.findViewById(R.id.rv_moment);
        //TODO:make sure the getContext() is right
        recyclerView_post.setLayoutManager(new LinearLayoutManager(MomentsFragment.this.getContext()));

        recyclerView_post.setAdapter(new MomentPostAdapter(MomentsFragment.this.getContext(), new MomentPostAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(getContext(),"cliked " + pos,Toast.LENGTH_LONG).show();
            }
        }));
        List<ViewPost> pageposts = new ArrayList<>();
        PostService postService = PostService.getInstance();
        for(ViewPost viewPost:pageposts){
            Post post = viewPost.getPost();
            int likeCount = viewPost.getLikeCount();
            User author = viewPost.getUser();//Kobe
            boolean userLikedPost = viewPost.UserLikedPost();
            // TODO:
            String postim = post.getPostImg();

            //TODO2:add friends  and blocked
            int currentUid = UserMgr.getInstance().getCurrentUserState().getId();
            UserDAOImp.getInstance().addFriend(currentUid, post.getId());

            UserDAOImp.getInstance().addBlock(currentUid, post.getId());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moments, container, false);

    }

    private View.OnClickListener button_postListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //MomentsFragment --> PostActivity
            switch (view.getId()){
                case R.id.button_moment_post:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),PostActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


}