package nurulaiman.sony.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import nurulaiman.sony.myapplication.R;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_browse);
    }
}
