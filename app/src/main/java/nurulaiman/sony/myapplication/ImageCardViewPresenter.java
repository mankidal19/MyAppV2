package nurulaiman.sony.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v17.leanback.widget.ImageCardView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;

import nurulaiman.sony.myapplication.model.Card;
import nurulaiman.sony.myapplication.activity.ApiActivity;
import nurulaiman.sony.myapplication.activity.FullscreenActivity;
import nurulaiman.sony.myapplication.activity.LiveActivity;
import com.squareup.picasso.Picasso;

public class ImageCardViewPresenter extends AbstractCardPresenter<ImageCardView> {

    private Card.Type mType;

    private String mVideoId;

    public ImageCardViewPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public ImageCardViewPresenter(Context context, Card.Type type) {
        this(context, R.style.DefaultCardTheme);
        mType = type;
    }

    public ImageCardViewPresenter(Context context, Card.Type type, String videoId) {
        this(context, R.style.DefaultCardTheme);
        mType = type;
        mVideoId = videoId;
        Log.i("video ID loaded:",videoId);
    }

    public ImageCardViewPresenter(Context context) {
        this(context, R.style.DefaultCardTheme);
    }

    @Override
    protected ImageCardView onCreateView() {
        ImageCardView imageCardView = new ImageCardView(getContext());
        imageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (mType) {
                    case YOUTUBETV_FULLSCREEN:
                        intent = new Intent(getContext(), FullscreenActivity.class);
                        getContext().startActivity(intent);
                        break;
                    case YOUTUBETV_API:
                        intent = new Intent(getContext(), ApiActivity.class);
                        getContext().startActivity(intent);
                        break;
                    case YOUTUBETV_LIVE:
                        intent = new Intent(getContext(), LiveActivity.class);
                        intent.putExtra("videoId",mVideoId);
                        getContext().startActivity(intent);
                        break;

                    default:
                        break;
                }
            }
        });
        return imageCardView;
    }

    @Override
    public void onBindViewHolder(Card card, final ImageCardView cardView) {
        cardView.setTag(card);
        cardView.setTitleText(card.getTitle());
        cardView.setContentText(card.getDescription());
        if (card.getLocalImageResourceName() != null) {
            int resourceId = getContext().getResources()
                    .getIdentifier(card.getLocalImageResourceName(),
                            "drawable", getContext().getPackageName());
            Picasso.with(getContext()).load(resourceId).into(cardView.getMainImageView());
        }
    }

}

