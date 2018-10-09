package nurulaiman.sony.myapplication.fragment;

import android.app.Fragment;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlight;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;

import com.google.gson.Gson;

import nurulaiman.sony.myapplication.CardPresenterSelector;
import nurulaiman.sony.myapplication.R;
import nurulaiman.sony.myapplication.Utils;
import nurulaiman.sony.myapplication.model.CardRow;
import nurulaiman.sony.myapplication.activity.LiveActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainBrowseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainBrowseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainBrowseFragment extends VerticalGridFragment {

    private static final int COLUMNS = 3;
    private static final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_MEDIUM;

    private static String TAG = "MainBrowseFragment";

    private ArrayObjectAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.grid_title));
        setupRowAdapter();




        // VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt("Test");
    }

    //implement voice iteraction
    @Override
    public void onResume(){
        super.onResume();
        if(getActivity().isVoiceInteraction() || getActivity().isVoiceInteractionRoot()){
            startVoiceTrigger();
            Log.i(TAG,"voiceIteraction triggered");

        }

        else{
            Log.i(TAG,"voiceIteraction NOT triggered");
        }

        //getActivity().finish();
    }



    private void startVoiceTrigger() {Log.d(TAG, "startVoiceTrigger: ");
        VoiceInteractor.PickOptionRequest.Option option = new VoiceInteractor.PickOptionRequest.Option("play TV",0);
        option.addSynonym("TV");
        option.addSynonym("broadcast");
        option.addSynonym("live");

        getActivity().getVoiceInteractor()
                .submitRequest(new VoiceInteractor.PickOptionRequest(new VoiceInteractor.Prompt("Welcome to Operator App! How can I help you?"), new VoiceInteractor.PickOptionRequest.Option[]{option}, null) {
                    @Override
                    public void onPickOptionResult(boolean finished, Option[] selections, Bundle result) {
                        if (finished && selections.length == 1) {
                            Message message = Message.obtain();
                            message.obj = result;

                            Intent intent = new Intent(getContext(), LiveActivity.class);
                            getContext().startActivity(intent);
                        } else {
                            getActivity().finish();
                        }
                    }
                    @Override
                    public void onCancel() {
                        getActivity().finish();
                    }
                });

    }

    private void setupRowAdapter() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter(ZOOM_FACTOR);
        gridPresenter.setNumberOfColumns(COLUMNS);
        setGridPresenter(gridPresenter);

        PresenterSelector cardPresenterSelector = new CardPresenterSelector(getActivity());
        mAdapter = new ArrayObjectAdapter(cardPresenterSelector);
        setAdapter(mAdapter);

        prepareEntranceTransition();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createRows();
                startEntranceTransition();
            }
        }, 1000);
    }

    private void createRows() {
        String json = Utils.inputStreamToString(getResources()
                .openRawResource(R.raw.grid_example));
        CardRow row = new Gson().fromJson(json, CardRow.class);
        mAdapter.addAll(0, row.getCards());
    }


}
