package nurulaiman.sony.myapplication.sample;

import android.app.Activity;
import android.os.Bundle;

import nurulaiman.sony.myapplication.R;

import fr.bmartel.youtubetv.YoutubeTvView;

public class FullscreenActivity extends Activity {
    private YoutubeTvView mYoutubeView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        mYoutubeView = (YoutubeTvView) findViewById(R.id.youtube_video);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mYoutubeView.closePlayer();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
