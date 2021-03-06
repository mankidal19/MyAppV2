/*
 * Copyright (c) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nurulaiman.sony.myapplication.data;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Resources;
import android.media.Rating;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nurulaiman.sony.myapplication.R;
import nurulaiman.sony.myapplication.Utils;
import nurulaiman.sony.myapplication.model.Card;
import nurulaiman.sony.myapplication.model.CardRow;

/** Mock data to provide content for search results. */
public class MockDatabase {

    // The columns we'll include in the video database table
    public static final String KEY_NAME = SearchManager.SUGGEST_COLUMN_TEXT_1;
    public static final String KEY_DESCRIPTION = SearchManager.SUGGEST_COLUMN_TEXT_2;
    public static final String KEY_ICON = SearchManager.SUGGEST_COLUMN_RESULT_CARD_IMAGE;
    public static final String KEY_DATA_TYPE = SearchManager.SUGGEST_COLUMN_CONTENT_TYPE;
    public static final String KEY_IS_LIVE = SearchManager.SUGGEST_COLUMN_IS_LIVE;
    public static final String KEY_VIDEO_WIDTH = SearchManager.SUGGEST_COLUMN_VIDEO_WIDTH;
    public static final String KEY_VIDEO_HEIGHT = SearchManager.SUGGEST_COLUMN_VIDEO_HEIGHT;
    public static final String KEY_AUDIO_CHANNEL_CONFIG =
            SearchManager.SUGGEST_COLUMN_AUDIO_CHANNEL_CONFIG;
    public static final String KEY_PURCHASE_PRICE = SearchManager.SUGGEST_COLUMN_PURCHASE_PRICE;
    public static final String KEY_RENTAL_PRICE = SearchManager.SUGGEST_COLUMN_RENTAL_PRICE;
    public static final String KEY_RATING_STYLE = SearchManager.SUGGEST_COLUMN_RATING_STYLE;
    public static final String KEY_RATING_SCORE = SearchManager.SUGGEST_COLUMN_RATING_SCORE;
    public static final String KEY_PRODUCTION_YEAR = SearchManager.SUGGEST_COLUMN_PRODUCTION_YEAR;
    public static final String KEY_COLUMN_DURATION = SearchManager.SUGGEST_COLUMN_DURATION;
    public static final String KEY_ACTION = SearchManager.SUGGEST_COLUMN_INTENT_ACTION;

    private Context context;

    private List<Card> cards;

    /**
     * Returns a list of all of the movies in the database.
     *
     * @return All of the movies.
     */
    public List<Card> getAllCard() {
        if (cards == null) {
            String json = Utils.inputStreamToString(context.getResources()
                    .openRawResource(R.raw.grid_example));
            CardRow row = new Gson().fromJson(json, CardRow.class);

            this.cards = row.getCards();
            //Log.i("CARD:",cards.get(0).getTitle());

        }
        return cards;


    }

    public MockDatabase(Context context){
        this.context = context;
    }

    /**
     * Searches for a movie whose title or description can match against the query parameter.
     *
     * @param query Search string.
     * @return A list of movies that match the query string.
     */
    public List<Card> search(String query) {
        query = query.toLowerCase();
        List<Card> results = new ArrayList<>();
        for (Card c : getAllCard()) {
            if (c.getTitle().toLowerCase().contains(query)
                    || c.getDescription().toLowerCase().contains(query)) {
                results.add(c);
            }
        }
        return results;
    }

    /**
     * Finds a particular movie with the given id.
     *
     * @param id movie's id.
     * @return A movie with the id.
     */
    public Card findMovieWithId(int id) {
        for (Card c : getAllCard()) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new IllegalArgumentException("Cannot find movie with id: " + id);
    }




}
