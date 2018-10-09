package nurulaiman.sony.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nurulaiman.sony.myapplication.R;

import fr.bmartel.youtubetv.YoutubeTvView;
import fr.bmartel.youtubetv.listener.IPlayerListener;
import fr.bmartel.youtubetv.model.VideoInfo;
import fr.bmartel.youtubetv.model.VideoState;

public class ApiActivity extends Activity {

    private final static String TAG = ApiActivity.class.getSimpleName();

    private YoutubeTvView mYoutubeView1;

    private final static int POSITION_OFFSET = 5;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        mYoutubeView1 = (YoutubeTvView) findViewById(R.id.video1);

        Button playBtn = (Button) findViewById(R.id.play_button);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.start();
            }
        });
        Button pauseBtn = (Button) findViewById(R.id.pause_button);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.pause();
            }
        });
        Button nextBtn = (Button) findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.nextVideo();
            }
        });
        Button previousBtn = (Button) findViewById(R.id.previous_button);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.previousVideo();
            }
        });

        Button backwardBtn = (Button) findViewById(R.id.backward_button);
        backwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.moveBackward(POSITION_OFFSET);
            }
        });
        Button forwardBtn = (Button) findViewById(R.id.forward_button);
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeView1.moveForward(POSITION_OFFSET);
            }
        });

        mYoutubeView1.addPlayerListener(new IPlayerListener() {
            @Override
            public void onPlayerReady(final VideoInfo videoInfo) {
                Log.i(TAG, "onPlayerReady");
            }

            @Override
            public void onPlayerStateChange(final VideoState state,
                                            final long position,
                                            final float speed,
                                            final float duration,
                                            final VideoInfo videoInfo) {
                Log.i(TAG, "onPlayerStateChange : " + state.toString() + " | position : " + position + " | speed : " + speed);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mYoutubeView1.closePlayer();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
