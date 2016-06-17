package com.cjgaliana.xamarinvideos.Fragments;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
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
import com.cjgaliana.xamarinvideos.Services.NavigationHelper;
import com.cjgaliana.xamarinvideos.Services.NetworkHelper;
import com.cjgaliana.xamarinvideos.Services.YoutubeHelper;
import com.squareup.picasso.Picasso;


public class SessionDetailsFragment extends Fragment {

    public int downloadProgressPercentage;
    ImageView imageView;
    TextView titleView;
    TextView speakerView;
    TextView trackView;
    TextView descriptionView;
    Button playButton;
    Button downloadButton;
    //    ProgressBar progressBarView;
    Button deleteButton;
    private EvolveSession session;
    private long downloadReference;
    private DownloadManager downloadManager;
    private BroadcastReceiver receiverDownloadCompleted;
    private BroadcastReceiver receiverNotificationClicked;

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
        this.playButton = (Button) getView().findViewById(R.id.session_details_playButton);
        this.downloadButton = (Button) getView().findViewById(R.id.session_details_downloadButton);
        this.deleteButton = (Button) getView().findViewById(R.id.session_details_deleteButton);
//        this.progressBarView = (ProgressBar) getView().findViewById(R.id.session_details_progressBar);
    }

    private void setButtonsListeners() {


        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayButtonClick();
            }
        });

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

    private void onPlayButtonClick() {
        // Navigate to play activity
        NavigationHelper.NavigateToPlayerView(getActivity(), this.session);
    }


    void onDownloadButtonClick() {
        if (!NetworkHelper.IsOnline(getActivity())) {
            showNetworkError();
        } else {

            // Start download
            startDownload();

            Toast.makeText(getActivity(), R.string.download_queued, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        this.downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        setClickedReceiver();
        setDownloadCompletedReceiver();

    }

    private void setDownloadCompletedReceiver() {

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        this.receiverDownloadCompleted = new BroadcastReceiver() {


            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (reference == downloadReference) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(reference);
                    Cursor cursor = downloadManager.query(query);

                    cursor.moveToFirst();
                    // Get the status of the download
                    int statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(statusIndex);

                    int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                    String savedFilePath = cursor.getString(filenameIndex);


                    // Progress
                    int bytesDownloadedIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                    long bytesDownloaded = cursor.getLong(bytesDownloadedIndex);

                    int totalBytesIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                    long totalBytes = cursor.getLong(totalBytesIndex);

                    // Get the reason - more details on the satus
                    int reasonIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(reasonIndex);

                    switch (status) {
                        case DownloadManager.STATUS_SUCCESSFUL:
                            Toast.makeText(getActivity(), "COMPLETED: " + savedFilePath, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_FAILED:
                            Toast.makeText(getActivity(), "FAILED: " + reason, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(getActivity(), "PAUSED: " + reason, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(getActivity(), "PENDING: " + reason, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(getActivity(), "RUNNING: " + reason, Toast.LENGTH_SHORT).show();
                            break;

                        default:
                            Toast.makeText(getActivity(), "DEFAULT:  " + reason, Toast.LENGTH_SHORT).show();
                            break;
                    }


                    // Download progress
                    downloadProgressPercentage = Math.round(bytesDownloaded * 100 / totalBytes);

                    cursor.close();

                }
            }
        };

        getActivity().registerReceiver(receiverDownloadCompleted, filter);
    }

    private void setClickedReceiver() {

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);

        this.receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;

                long[] references = intent.getLongArrayExtra(extraId);
                for (long reference : references) {
                    if (reference == downloadReference) {
                        // Do something with the downloaded file

                    }
                }

            }
        };

        getActivity().registerReceiver(receiverNotificationClicked, filter);
    }

    private void startDownload() {

        // Following the tutorial on http://101apps.co.za/articles/using-the-downloadmanager-to-manage-your-downloads.html
        Uri videoUri = Uri.parse(YoutubeHelper.getVideoUrl(this.session.YoutubeID));
        DownloadManager.Request request = new DownloadManager.Request(videoUri);
        request.setVisibleInDownloadsUi(true);

        // Only wifi
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //TODO: Add setting to allow mobile network too
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

        // Data in notification hubs
        request
                .setTitle(session.Title)
                .setDescription(getResources().getString(R.string.app_name));


        this.downloadReference = downloadManager.enqueue(request);
        // TODO: Save this reference for future management
    }

    private void showNetworkError() {
        Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_SHORT).show();
    }

    void onDeleteDownloadButton() {

        downloadManager.remove(this.downloadReference);
        Toast.makeText(getActivity(), "Download deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_open_youtube:
                YoutubeHelper.openYoutubeVideoOnExternal(getActivity(), this.session.YoutubeID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().unregisterReceiver(receiverDownloadCompleted);
        getActivity().unregisterReceiver(receiverNotificationClicked);

    }


}
