package com.cjgaliana.xamarinvideos.Services;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by camilo on 17/06/2016.
 */
public class YoutubeHelper {


    public static void openYoutubeVideoOnExternal(Context context, String videoID) {
        try {
            // Try to open the Youtube app
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoID));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Fail? Then open it on the browser
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getVideoUrl(videoID)));
            context.startActivity(intent);
        }
    }

    public static String getVideoUrl(String youtubeID) {
        return "http://www.youtube.com/watch?v=" + youtubeID;
    }
}
