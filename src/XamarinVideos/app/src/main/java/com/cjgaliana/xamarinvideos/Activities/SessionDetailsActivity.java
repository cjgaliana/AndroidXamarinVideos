package com.cjgaliana.xamarinvideos.Activities;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cjgaliana.xamarinvideos.Fragments.SessionDetailsFragment;
import com.cjgaliana.xamarinvideos.Models.EvolveSession;
import com.cjgaliana.xamarinvideos.R;
import com.cjgaliana.xamarinvideos.Services.NavigationHelper;

public class SessionDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_details);
        this.initializeActivity(savedInstanceState);
    }


    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            EvolveSession session = getIntent().getParcelableExtra(NavigationHelper.SESSION_NAV_PARAMETERS_KEY);
            this.setupFragments(session);

        } else {
            // Show error, can't navigate to this section without a playlist data
        }
    }


    private void setupFragments(EvolveSession session) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        SessionDetailsFragment fragment = new SessionDetailsFragment();
        fragment.setSession(session);
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }

}
