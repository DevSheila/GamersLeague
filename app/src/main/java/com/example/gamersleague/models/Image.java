
package com.example.gamersleague.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Image {

    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("medium_url")
    @Expose
    private String mediumUrl;
    @SerializedName("screen_url")
    @Expose
    private String screenUrl;
    @SerializedName("screen_large_url")
    @Expose
    private String screenLargeUrl;
    @SerializedName("small_url")
    @Expose
    private String smallUrl;
    @SerializedName("super_url")
    @Expose
    private String superUrl;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("tiny_url")
    @Expose
    private String tinyUrl;
    @SerializedName("original_url")
    @Expose
    private String originalUrl;
    @SerializedName("image_tags")
    @Expose
    private String imageTags;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Image() {
    }

    /**
     * 
     * @param smallUrl
     * @param mediumUrl
     * @param screenUrl
     * @param superUrl
     * @param tinyUrl
     * @param iconUrl
     * @param originalUrl
     * @param thumbUrl
     * @param imageTags
     * @param screenLargeUrl
     */
    public Image(String iconUrl, String mediumUrl, String screenUrl, String screenLargeUrl, String smallUrl, String superUrl, String thumbUrl, String tinyUrl, String originalUrl, String imageTags) {
        super();
        this.iconUrl = iconUrl;
        this.mediumUrl = mediumUrl;
        this.screenUrl = screenUrl;
        this.screenLargeUrl = screenLargeUrl;
        this.smallUrl = smallUrl;
        this.superUrl = superUrl;
        this.thumbUrl = thumbUrl;
        this.tinyUrl = tinyUrl;
        this.originalUrl = originalUrl;
        this.imageTags = imageTags;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getScreenUrl() {
        return screenUrl;
    }

    public void setScreenUrl(String screenUrl) {
        this.screenUrl = screenUrl;
    }

    public String getScreenLargeUrl() {
        return screenLargeUrl;
    }

    public void setScreenLargeUrl(String screenLargeUrl) {
        this.screenLargeUrl = screenLargeUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getSuperUrl() {
        return superUrl;
    }

    public void setSuperUrl(String superUrl) {
        this.superUrl = superUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getImageTags() {
        return imageTags;
    }

    public void setImageTags(String imageTags) {
        this.imageTags = imageTags;
    }

}
