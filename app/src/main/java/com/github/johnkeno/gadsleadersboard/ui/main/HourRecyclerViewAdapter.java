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
import com.github.johnkeno.gadsleadersboard.network.main.HourResponse;

import java.util.List;

public class HourRecyclerViewAdapter extends RecyclerView.Adapter<HourRecyclerViewAdapter.viewHolder> {

    private Context mContext;
    private final List<HourResponse> mBody;

    public HourRecyclerViewAdapter(Context context, List<HourResponse> body) {
        this.mContext = context;
        this.mBody = body;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hour,parent,false);

        return new viewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mBody.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.mLearnTitle.setText(mBody.get(position).getName());
        holder.mLearText.setText(mBody.get(position).toString());
        Glide.with(mContext).load(mBody.get(position).getBadgeUrl()).into(holder.mLearnImage);


    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private final TextView mLearnTitle;
        private final TextView mLearText;
        private final ImageView mLearnImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mLearnTitle = itemView.findViewById(R.id.hour_title);
            mLearText = itemView.findViewById(R.id.hour_text);
            mLearnImage = itemView.findViewById(R.id.hour_image);
        }
    }
}
