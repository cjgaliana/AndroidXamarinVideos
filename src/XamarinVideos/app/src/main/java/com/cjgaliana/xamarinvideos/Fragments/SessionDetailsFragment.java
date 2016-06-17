package com.cjgaliana.xamarinvideos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjgaliana.xamarinvideos.Models.EvolveSession;
import com.cjgaliana.xamarinvideos.R;
import com.cjgaliana.xamarinvideos.Services.YoutubeHelper;
import com.squareup.picasso.Picasso;


public class SessionDetailsFragment extends Fragment {

    ImageView imageView;
    TextView titleView;
    TextView speakerView;
    TextView trackView;
    TextView descriptionView;
    Button downloadButton;
    Button deleteButton;
//    ProgressBar progressBarView;

    private EvolveSession session;

    public SessionDetailsFragment() {
        // Required empty public constructor
    }

    public void setSession(EvolveSession session) {
        this.session = session;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_session_details, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_session_details, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load local downloaded data here?
        bindViews();
        setButtonsListeners();
        bindDataToViews(view);
    }

    private void bindDataToViews(View view) {
        // Load image
        Picasso.
                with(view.getContext())
                .load(this.session.Thumbnail)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(this.imageView);

        this.titleView.setText(session.Title);
        this.speakerView.setText(session.Author);
        this.trackView.setText(session.Track);
        this.descriptionView.setText(session.Description);
    }

    private void bindViews() {
        this.imageView = (ImageView) getView().findViewById(R.id.session_details_image);
        this.titleView = (TextView) getView().findViewById(R.id.session_details_title);
        this.speakerView = (TextView) getView().findViewById(R.id.session_details_speaker);
        this.trackView = (TextView) getView().findViewById(R.id.session_details_track);
        this.descriptionView = (TextView) getView().findViewById(R.id.session_details_description);
        this.downloadButton = (Button) getView().findViewById(R.id.session_details_downloadButton);
        this.deleteButton = (Button) getView().findViewById(R.id.session_details_deleteButton);
//        this.progressBarView = (ProgressBar) getView().findViewById(R.id.session_details_progressBar);
    }

    private void setButtonsListeners() {
        this.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDownloadButtonClick();
            }
        });

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteDownloadButton();
            }
        });
    }


    void onDownloadButtonClick() {
//        Toast.makeText(getActivity(), "Download queued", Toast.LENGTH_SHORT).show();
        YoutubeHelper.OpenYoutubeVideoOnExternal(this.getActivity(), this.session.YoutubeID);
    }

    void onDeleteDownloadButton() {
        Toast.makeText(getActivity(), "Download deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_open_youtube:
                YoutubeHelper.OpenYoutubeVideoOnExternal(getActivity(), this.session.YoutubeID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
