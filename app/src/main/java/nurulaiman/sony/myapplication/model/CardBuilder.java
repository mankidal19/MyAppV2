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

package nurulaiman.sony.myapplication.model;

/** Builds a {@link Card} instance. */
public class CardBuilder {
    private int id;
    private String title;
    private String description;
    private String cardImage;
    private String backgroundImage;
    private String contentType;
    private boolean live = false;
    private int width;
    private int height;
    private String audioChannelConfig;
    private String purchasePrice;
    private String rentalPrice;
    private int ratingStyle;
    private double ratingScore;
    private int productionYear;
    private int duration;
    private String videoUrl;

    public CardBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CardBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CardBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CardBuilder setCardImage(String cardImage) {
        this.cardImage = cardImage;
        return this;
    }

    public CardBuilder setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public CardBuilder setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public CardBuilder setLive(boolean live) {
        this.live = live;
        return this;
    }

    public CardBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public CardBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public CardBuilder setAudioChannelConfig(String audioChannelConfig) {
        this.audioChannelConfig = audioChannelConfig;
        return this;
    }

    public CardBuilder setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public CardBuilder setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
        return this;
    }

    public CardBuilder setRatingStyle(int ratingStyle) {
        this.ratingStyle = ratingStyle;
        return this;
    }

    public CardBuilder setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
        return this;
    }

    public CardBuilder setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public CardBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public CardBuilder setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Card createCard() {
        return new Card(
                id,
                title,
                description,
                cardImage,
                backgroundImage,
                videoUrl,
                contentType,
                live,
                width,
                height,
                audioChannelConfig,
                purchasePrice,
                rentalPrice,
                ratingStyle,
                ratingScore,
                productionYear,
                duration);
    }
}
