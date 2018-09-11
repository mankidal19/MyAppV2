package nurulaiman.sony.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.net.URI;
import java.net.URISyntaxException;

public class Card {

    @SerializedName("title")
    private String mTitle = "";
    @SerializedName("description")
    private String mDescription = "";
    @SerializedName("extraText")
    private String mExtraText = "";
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("footerColor")
    private String mFooterColor = null;
    @SerializedName("selectedColor")
    private String mSelectedColor = null;
    @SerializedName("localImageResource")
    private String mLocalImageResource = null;
    @SerializedName("footerIconLocalImageResource")
    private String mFooterResource = null;
    @SerializedName("type")
    private Card.Type mType;
    @SerializedName("id")
    private int mId;
    @SerializedName("width")
    private int mWidth;
    @SerializedName("height")
    private int mHeight;

    //adding for video id
    @SerializedName("videoId")
    private String mVideoId;

    public String getTitle() {
        return mTitle;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getId() {
        return mId;
    }

    public Type getType() {
        return mType;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getExtraText() {
        return mExtraText;
    }

    public int getFooterColor() {
        if (mFooterColor == null) return -1;
        return Color.parseColor(mFooterColor);
    }

    public int getSelectedColor() {
        if (mSelectedColor == null) return -1;
        return Color.parseColor(mSelectedColor);
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public URI getImageURI() {
        if (getImageUrl() == null) return null;
        try {
            return new URI(getImageUrl());
        } catch (URISyntaxException e) {
            Log.d("URI exception: ", getImageUrl());
            return null;
        }
    }

    public int getLocalImageResourceId(Context context) {
        return context.getResources().getIdentifier(getLocalImageResourceName(), "drawable",
                context.getPackageName());
    }

    public String getLocalImageResourceName() {
        return mLocalImageResource;
    }

    public String getFooterLocalImageResourceName() {
        return mFooterResource;
    }

    public String getVideoId() {
        return mVideoId;
    }



    public enum Type {
        YOUTUBETV_FRAGMENT,
        YOUTUBETV_FULLSCREEN,
        YOUTUBETV_API,
        YOUTUBETV_SPLITTED,
        YOUTUBETV_DEBUG,
        YOUTUBE_LICENSE,
        YOUTUBETV_LIVE
    }

}

