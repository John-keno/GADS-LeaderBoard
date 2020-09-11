package com.github.johnkeno.gadsleadersboard.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.johnkeno.gadsleadersboard.R;
import com.github.johnkeno.gadsleadersboard.network.main.IqResponse;

import java.util.List;

public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillRecyclerViewAdapter.viewHolder> {
    private Context mContext;
    private final List<IqResponse> mData;

    public SkillRecyclerViewAdapter(Context context, List<IqResponse> valData) {
        this.mContext = context;
        this.mData = valData;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_iq, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.mSkillIqTitle.setText(mData.get(position).getName());
        holder.mSkillIqText.setText(mData.get(position).toString());
        Glide.with(mContext).load(mData.get(position).getBadgeUrl()).into(holder.mSkillIqImage);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private final TextView mSkillIqTitle;
        private final TextView mSkillIqText;
        private final ImageView mSkillIqImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillIqTitle = itemView.findViewById(R.id.skill_iq_title);
            mSkillIqText = itemView.findViewById(R.id.skill_iq_text);
            mSkillIqImage = itemView.findViewById(R.id.skill_iq_image);
        }
    }
}
