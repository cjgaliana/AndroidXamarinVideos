package com.cjgaliana.xamarinvideos.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjgaliana.xamarinvideos.Models.VideoCollection;
import com.cjgaliana.xamarinvideos.R;

import java.util.List;

/**
 * Created by camilo on 16/06/2016.
 */
public class RVSessionAdapter extends RecyclerView.Adapter<RVSessionAdapter.SessionViewHolder> {


    private final List<VideoCollection> data;

    public RVSessionAdapter(List<VideoCollection> data) {
        this.data = data;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_template, parent, false);
        SessionViewHolder pvh = new SessionViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).Name);
    }


    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class SessionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        ImageView image;

        SessionViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.session_card);
            title = (TextView) itemView.findViewById(R.id.session_card_title);
            image = (ImageView) itemView.findViewById(R.id.session_card_image);
        }
    }

}
