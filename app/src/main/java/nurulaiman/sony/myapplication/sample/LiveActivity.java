package nurulaiman.sony.myapplication.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

import nurulaiman.sony.myapplication.R;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;


public class LiveActivity extends FragmentActivity {

    //private final String liveVideoId = "zEeSa0KpyOY";
    private String liveVideoId = null;
    private YouTubePlayerView youTubePlayerView = null;

    //for channel up/down button
    private ArrayList<String> channelArrayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        initYouTubePlayerView();
        initChannelList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //if(youTubePlayerView !=null){
            //youTubePlayerView.release();
        //}


        finish();

        Log.i("in Live Activity","onBackPressed() is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //if(youTubePlayerView !=null){
            //youTubePlayerView.release();
        //}

        Log.i("in Live Activity","onPause() is called");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        youTubePlayerView.release();
        Log.i("in Live Activity","onDestroy() is called");
    }


    //to handle channel up & down button
    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event){

        boolean handled = false;
        KeyCode = event.getKeyCode();
        Intent intent = new Intent(this, LiveActivity.class);

        Log.i("KeyEvent",KeyCode + " button pressed");

        if(event.getKeyCode() == KeyEvent.KEYCODE_CHANNEL_DOWN){
            Log.i("KeyEvent","Channel down button pressed");
            handled=true;
            //onDestroy();
            //youTubePlayerView.release();
            intent.putExtra("videoId",getPrevChannel());
        }

        else if(event.getKeyCode() == KeyEvent.KEYCODE_CHANNEL_UP){
            Log.i("KeyEvent","Channel up button pressed");
            handled=true;
            intent.putExtra("videoId",getNextChannel());
            //onDestroy();
            //youTubePlayerView.release();
        }


        if(handled){
            onDestroy();
            startActivity(intent);

            return handled;
        }

        else{
            return super.onKeyDown(KeyCode, event);
        }
        //return handled;
    }





    private void initYouTubePlayerView() {
        //YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.getPlayerUIController().showFullscreenButton(false);
        youTubePlayerView.getPlayerUIController().enableLiveVideoUI(true);

        liveVideoId = getIntent().getExtras().getString("videoId");
        Log.i("in Live Activity","current videoID: "+ liveVideoId);

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.initialize(youTubePlayer -> {

            youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    youTubePlayer.loadVideo(liveVideoId,0f);

                }


            });

        }, true);
    }

    //initialize channel list ArrayList
    private void initChannelList(){
        channelArrayList.add("lpBAE0Z0INI");
        channelArrayList.add("2LWt-dxd0v4");
        channelArrayList.add("zEeSa0KpyOY");
        channelArrayList.add("yS2V6jQBXwk");
        channelArrayList.add("KkXq2sv6Tos");
        channelArrayList.add("Lvp-lSqHVKc");
    }

    private String getNextChannel(){
        int idx = channelArrayList.indexOf(liveVideoId);

        //if idx is not the last element
        if (idx!=channelArrayList.size()-1){
            idx++;

            return channelArrayList.get(idx);
        }

        else{
            return channelArrayList.get(0);
        }


    }

    private String getPrevChannel(){
        int idx = channelArrayList.indexOf(liveVideoId);

        //if idx is not the first element
        if (idx!=0){
            idx--;

            return channelArrayList.get(idx);
        }

        else{
            return channelArrayList.get(channelArrayList.size()-1);
        }


    }

}