package com.example.sportsdating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsdating.post.dao.PostLikeDAOImp;
import com.example.sportsdating.post.model.ViewPost;
import com.example.sportsdating.post.service.PostService;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class MomentPostAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;
    //private List<String> list;
    private List<ViewPost> postList;
    private int currentPage;
    private User currentUser;

    public MomentPostAdapter(Context context , OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        postList=new ArrayList<>();
        this.showPage(1);
        currentUser = UserMgr.getInstance().getCurrentUserState().getUser();
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.momenttop_item, parent, false));
    }
    public void showPage(int page){
        this.currentPage=page;
        PostService postService = PostService.getInstance();
        List<ViewPost> pageposts =  postService.getPostByPage(page); //get the post data of page 1
        this.postList.clear();
        this.postList.addAll(pageposts);
        this.notifyDataSetChanged();

    }

    //TODO:
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //System.out.println("### show post at position:"+position);
        LinearViewHolder viewHolder= (LinearViewHolder) holder;
        viewHolder.setData(this.postList.get(position),position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return 0;//偶数
        }else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    //TODO:all items
    class LinearViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_user;
        private TextView tv_userName;
        private TextView tv_content;
        private ImageView iv_post;
        private ImageView iv_like;
        private ImageView iv_coment;
        private TextView tv_totalLike;
        private TextView tv_totalcomment;
        private Button button_follow;
        private Button button_block;

        private int position;

        public LinearViewHolder(View itemView){
            super(itemView);
            iv_user = itemView.findViewById(R.id.iv_user);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_post = itemView.findViewById(R.id.iv_post);
            iv_like = itemView.findViewById(R.id.iv_like);
            iv_coment = itemView.findViewById(R.id.iv_coment);
            tv_totalLike = itemView.findViewById(R.id.tv_totalLike);
            tv_totalcomment = itemView.findViewById(R.id.tv_totalcomment);
            button_follow = itemView.findViewById(R.id.button_follow);
            button_block = itemView.findViewById(R.id.btn_item_block);
            button_follow.setOnClickListener(myListener);
            button_block.setOnClickListener(myListener);
            this.iv_like.setOnClickListener(myListener);
        }

        View.OnClickListener myListener = new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                System.out.println("###  on button clicked " + position);
                //Toast.makeText(mContext,"click..."+position,Toast.LENGTH_SHORT).show();
                switch (view.getId()){
                    case R.id.iv_like: {
                        System.out.println("###  like is clicked");
                        int uid = currentUser.getId();
                        int postId = postList.get(position).getPost().getId();
                        PostLikeDAOImp.getInstance().likePost(uid, postId);
                        int likeCount = PostLikeDAOImp.getInstance().getLikeCountByPost(postId);
                        tv_totalLike.setText(likeCount+"");
                        break;
                    }
                    case R.id.button_follow:{
                        if(currentUser.getFriends().contains(postList.get(position).getUser().getId())) {
                            System.out.println("follow button clicked, f->unf");
                            button_follow.setBackgroundColor(R.color.blue);
                            button_block.setVisibility(View.VISIBLE);
                            button_block.setText("BLOCK");
                            button_block.setBackgroundColor(R.color.blue);
                            UserDAOImp.getInstance().removeFriend(currentUser.getId(), postList.get(position).getUser().getId());
                        }else{
                            System.out.println("follow button clicked, uf->f");
                            button_follow.setBackgroundColor(R.color.purple_200);
                            button_block.setVisibility(View.GONE);
                            UserDAOImp.getInstance().addFriend(currentUser.getId(), postList.get(position).getUser().getId());
                        }
                        break;
                    }
                    case R.id.btn_item_block:{
                        if(currentUser.getBlocks().contains(postList.get(position).getUser().getId())) {
                            button_block.setBackgroundColor(R.color.blue);
                            button_block.setText("BLOCK");
                            button_follow.setVisibility(view.VISIBLE);
                            button_follow.setBackgroundColor(R.color.blue);
                            UserDAOImp.getInstance().removeBlock(currentUser.getId(), postList.get(position).getUser().getId());
                        }else{
                            System.out.println("###  block is clicked");
                            button_block.setBackgroundColor(R.color.purple_200);
                            button_block.setText("UNBLOCK");
                            button_follow.setVisibility(view.GONE);
                            UserDAOImp.getInstance().addBlock(currentUser.getId(), postList.get(position).getUser().getId());

                        }
                        break;
                    }
                }
            }
        };
        @SuppressLint("ResourceAsColor")
        public void setData(ViewPost data, int position) {
            this.position=position;
            this.tv_userName.setText(data.getUser().getName());
            System.out.println(data.getPost().getContent());
            this.tv_content.setText(data.getPost().getContent());
            this.tv_totalLike.setText(data.getLikeCount()+"");
//            this.tv_totalcomment.setText(data.getPost().);
            if(currentUser!=null){
                if(data.getUser().getId() == currentUser.getId()){
                    this.button_follow.setVisibility(View.GONE);
                    this.button_block.setVisibility(View.GONE);
                }else if(currentUser.getFriends().contains(data.getUser().getId()) ){
                    this.button_follow.setVisibility(View.VISIBLE);
                    this.button_follow.setBackgroundColor(R.color.purple_200);
                    this.button_block.setVisibility(View.GONE);
                }else{
                    System.out.println("never like and block");
                    this.button_follow.setVisibility(View.VISIBLE);
                    this.button_block.setVisibility(View.VISIBLE);
                    button_block.setText("BLOCK");
                    this.button_follow.setBackgroundColor(R.color.blue);
                    this.button_block.setBackgroundColor(R.color.blue);
                }
            }
        }
    }


    //接口
    public interface  OnItemClickListener{
        void onClick(int pos);
    }
}



