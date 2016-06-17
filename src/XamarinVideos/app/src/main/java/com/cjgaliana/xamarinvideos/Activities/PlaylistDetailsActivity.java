package com.cjgaliana.xamarinvideos.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cjgaliana.xamarinvideos.Fragments.PlaylistDetailsFragment;
import com.cjgaliana.xamarinvideos.Models.VideoCollection;
import com.cjgaliana.xamarinvideos.R;
import com.cjgaliana.xamarinvideos.Services.NavigationHelper;

public class PlaylistDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_details);
        this.initializeActivity(savedInstanceState);
    }


    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            VideoCollection playlist = getIntent().getParcelableExtra(NavigationHelper.PLAYLIST_NAV_PARAMETERS_KEY);
            this.setTitle(playlist.Name);
            this.setupFragments(playlist);

        } else {
            // Show error, can't navigate to this section without a playlist data
        }
    }


    private void setupFragments(VideoCollection playlist) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        PlaylistDetailsFragment fragment = new PlaylistDetailsFragment();
        fragment.setPlaylist(playlist);
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }


}
