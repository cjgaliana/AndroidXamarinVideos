package com.cjgaliana.xamarinvideos.Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cjgaliana.xamarinvideos.Activities.PlayerActivity;
import com.cjgaliana.xamarinvideos.Activities.PlaylistDetailsActivity;
import com.cjgaliana.xamarinvideos.Activities.SessionDetailsActivity;
import com.cjgaliana.xamarinvideos.Models.EvolveSession;
import com.cjgaliana.xamarinvideos.Models.VideoCollection;

/**
 * Created by camilo on 16/06/2016.
 */
public class NavigationHelper {

    public static final String PLAYLIST_NAV_PARAMETERS_KEY = "playlist_intent_param";
    public static final String SESSION_NAV_PARAMETERS_KEY = "session_intent_param";
    public static final String PLAYER_NAV_PARAMETERS_KEY = "player_intent_param";

    public static void NavigateToPlaylistDetails(Context originActivity, VideoCollection playlist) {
        try {
            Intent intent = new Intent(originActivity, PlaylistDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // This line is necessary because where are launching the activity from a Context instead an Activity
            intent.putExtra(NavigationHelper.PLAYLIST_NAV_PARAMETERS_KEY, playlist);
            originActivity.startActivity(intent);
        } catch (Exception ex) {
            Log.e(NavigationHelper.class.getSimpleName(), ex.getMessage(), ex);
        }

    }

    public static void NavigateToSessionDetails(Context originActivity, EvolveSession session) {

        Intent intent = new Intent(originActivity, SessionDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // This line is necessary because where are launching the activity from a Context instead an Activity
        intent.putExtra(NavigationHelper.SESSION_NAV_PARAMETERS_KEY, session);
        originActivity.startActivity(intent);
    }

    public static void NavigateToPlayerView(Context context, EvolveSession session) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // This line is necessary because where are launching the activity from a Context instead an Activity
        intent.putExtra(NavigationHelper.PLAYER_NAV_PARAMETERS_KEY, session);
        context.startActivity(intent);
    }
}
