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
 * Created by camilo on 15/06/2016.
 */
public class RVPlaylistAdapter extends RecyclerView.Adapter<RVPlaylistAdapter.PlaylistViewHolder> {


    private final List<VideoCollection> data;

    public RVPlaylistAdapter(List<VideoCollection> data) {
        this.data = data;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_template, parent, false);
        PlaylistViewHolder pvh = new PlaylistViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).Name);
    }


    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        ImageView image;

        PlaylistViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.playlist_card);
            title = (TextView) itemView.findViewById(R.id.playlist_card_title);
            image = (ImageView) itemView.findViewById(R.id.playlist_card_image);
        }
    }

}
