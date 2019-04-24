package com.ssproduction.shashank.ucn;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter
{
    private Context context;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_resource_file, parent, false);
        return new ProfileViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ProfileViewHolder)holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView coverImage;
        private CircleImageView profileImage;
        private ImageView coverEditImage;
        private TextView userFirstName;
        private TextView userLastName;
        private TextView userStatus;
        private TextView postsText;
        private TextView followersText;
        private TextView followingTexts;
        private TextView postsQuantity;
        private TextView followersQuantity;
        private TextView followingQuantity;
        private Button editProfileBtn;
        private TextView editDetailsText;
        Context context;

        public ProfileViewHolder(View itemView, Context context) {

            super(itemView);
            this.context = context;
            coverImage = (ImageView) itemView.findViewById(R.id.cover_image);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            coverEditImage = (ImageView) itemView.findViewById(R.id.cover_edit_image);
            userFirstName = (TextView) itemView.findViewById(R.id.profile_user_first_name);
            userLastName = (TextView) itemView.findViewById(R.id.profile_user_last_name);
            userStatus = (TextView) itemView.findViewById(R.id.profile_status);
            postsText = (TextView) itemView.findViewById(R.id.posts_text);
            followersText = (TextView) itemView.findViewById(R.id.followers_text);
            followingTexts = (TextView) itemView.findViewById(R.id.following_text);
            postsQuantity = (TextView) itemView.findViewById(R.id.posts_quantity);
            followersQuantity = (TextView) itemView.findViewById(R.id.followers_quantity);
            followingQuantity = (TextView) itemView.findViewById(R.id.following_quantity);
            editProfileBtn = (Button) itemView.findViewById(R.id.edit_profile_button);
            editDetailsText = (TextView) itemView.findViewById(R.id.edit_details_text);
        }

        public void bindView(int position){
            coverImage.setImageResource(profileData.profileImages[0]);
            profileImage.setImageResource(profileData.profileImages[1]);
            coverEditImage.setImageResource(profileData.profileImages[2]);
            userFirstName.setText(profileData.something[0]);
            userLastName.setText(profileData.something[1]);
            userStatus.setText(profileData.something[2]);
            postsText.setText(profileData.something[3]);
            followersText.setText(profileData.something[4]);
            followingTexts.setText(profileData.something[5]);
            postsQuantity.setText(profileData.something[6]);
            followersQuantity.setText(profileData.something[7]);
            followingQuantity.setText(profileData.something[8]);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();

        }
    }
}
