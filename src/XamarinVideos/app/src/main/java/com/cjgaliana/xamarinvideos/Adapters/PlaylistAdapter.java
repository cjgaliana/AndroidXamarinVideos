package com.cjgaliana.xamarinvideos.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjgaliana.xamarinvideos.Models.VideoCollection;
import com.cjgaliana.xamarinvideos.R;
import com.cjgaliana.xamarinvideos.Services.NavigationHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camilo on 15/06/2016.
 */
public class PlaylistAdapter extends BaseAdapter {

    private List<VideoCollection> mArrayList;
    private Context mContext;
    private LayoutInflater mInflater;


    public PlaylistAdapter(Context context, LayoutInflater inflater) {
        super();

        mContext = context;
        mInflater = inflater;

        mArrayList = new ArrayList<VideoCollection>();
    }


    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mArrayList.get(position).Id.hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Reuse an old view if we can, otherwise create a new one.
        if (view == null) {
            view = mInflater.inflate(R.layout.playlist_template, null);
        }

        CardView cardView = (CardView) view.findViewById(R.id.playlist_card);
        TextView titleView = (TextView) view.findViewById(R.id.playlist_card_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.playlist_card_image);

        final VideoCollection collection = mArrayList.get(position);

        String title = collection.Name;

        titleView.setText(title);

        Picasso.with(mContext).load(R.drawable.default_image).into(imageView);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationHelper.NavigateToPlaylistDetails(v.getContext(), collection);
            }
        });


        return view;
    }

    public void setData(List<VideoCollection> data) {
        this.mArrayList = data;
    }
}
