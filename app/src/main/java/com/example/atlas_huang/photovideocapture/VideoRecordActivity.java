package com.example.atlas_huang.photovideocapture;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by atlas_huang on 2017/4/17.
 */

public class VideoRecordActivity extends VideoRecordBaseActivity {
    private static final String TAG = VideoRecordActivity.class.getSimpleName();

    public VideoRecordActivity() {
        super();
        Log.d(TAG, "VideoRcordActivity()");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);

        setupVideoView();
        takeVideoByIntent();
    }

    @Override
    public void onRecordFinish(Uri uri) {
        Log.d(TAG, "onRecordFinish uri" + uri);
        playVideo(uri);
    }

    @Override
    public void setupVideoView() {
        this.mVideoView = (VideoView) findViewById(R.id.vvVideoShow);
    }

}
