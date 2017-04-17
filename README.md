# Project Photo and Video Capture

## This is a project for video play and record

* To focus on what we want. You can extends VideoRecordBaseActivity
* This class handle trivial things then you can pass videoview for setup and on RecordFinish event as image capture finish
You can focus on only below function only

	```
    public abstract void onRecordFinish(Uri uri);
    public abstract void setupVideoView();
	```    


* How to enable the activity to enable video capture   

	```  
    public void takeVideoByIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
	```   
    
# Reference   
1. https://developer.android.com/training/camera/videobasics.html  
1. http://stackoverflow.com/questions/6568054/how-to-write-a-simple-video-player-on-android
1. https://github.com/Atlas0904/Project_Android_VideoPlayer