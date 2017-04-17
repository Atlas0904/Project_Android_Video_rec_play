package com.example.atlas_huang.photovideocapture;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Created by atlas_huang on 2017/4/17.
 */

public abstract class VideoRecordBaseActivity extends Activity {

    public static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final String TAG = VideoRecordBaseActivity.class.getSimpleName();

    protected Uri mUri;
    protected VideoView mVideoView;

    public VideoRecordBaseActivity() {
        super();
    }

    public void takeVideoByIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    protected void playVideo(Uri uri) {
        if (isStoragePermissionGranted()) {
            if (mVideoView != null && uri != null) {
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(mVideoView);

                Log.d(TAG, "playVideo: uri=" + uri);

                mVideoView.setMediaController(mediaController);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();

                mVideoView.start();
            }
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG,"Permission is granted");
                return true;
            } else {

                Log.d(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.d(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);

            // TODO MUST: Handle the action after user granted permission
            playVideo(mUri);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(TAG, "onActivityResult resultCode=" + requestCode +
                " resultCode=" + resultCode +
                " intent=" + intent.getAction());

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Log.d(TAG, "videoUri=" + intent.getData());
            mUri = intent.getData();
            onRecordFinish(mUri);
        }
    }

    public abstract void onRecordFinish(Uri uri);
    public abstract void setupVideoView();

}
