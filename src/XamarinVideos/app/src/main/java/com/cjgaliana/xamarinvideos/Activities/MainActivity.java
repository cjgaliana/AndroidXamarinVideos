package com.cjgaliana.xamarinvideos.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cjgaliana.xamarinvideos.Fragments.PlaylistsFragment;
import com.cjgaliana.xamarinvideos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);

        setupFragments();
    }

    private void setupFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        PlaylistsFragment fragment = new PlaylistsFragment();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }
}
