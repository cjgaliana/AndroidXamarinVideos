package com.cjgaliana.xamarinvideos.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjgaliana.xamarinvideos.Models.EvolveSession;
import com.cjgaliana.xamarinvideos.R;
import com.cjgaliana.xamarinvideos.Services.NavigationHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camilo on 16/06/2016.
 */
public class SessionAdapter extends BaseAdapter {
    private List<EvolveSession> mArrayList;
    private Context mContext;
    private LayoutInflater mInflater;


    public SessionAdapter(Context context, LayoutInflater inflater) {
        super();

        mContext = context;
        mInflater = inflater;

        mArrayList = new ArrayList<EvolveSession>();
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
            view = mInflater.inflate(R.layout.session_template, null);
        }

        CardView cardView = (CardView) view.findViewById(R.id.session_card);
        TextView titleView = (TextView) view.findViewById(R.id.session_card_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.session_card_image);

        final EvolveSession session = mArrayList.get(position);

        String title = session.Title;

        titleView.setText(title);

        Picasso
                .with(mContext)
                .load(session.Thumbnail)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(imageView);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationHelper.NavigateToSessionDetails(v.getContext(), session);
            }
        });


        return view;
    }

    public void setData(List<EvolveSession> data) {
        this.mArrayList = data;
    }
}
