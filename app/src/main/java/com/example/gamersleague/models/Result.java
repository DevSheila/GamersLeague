
package com.example.gamersleague.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Result {

    @SerializedName("aliases")
    @Expose
    private String aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("description")
    @Expose
    private String description;
//    @SerializedName("expected_release_day")
//    @Expose
//    private Object expectedReleaseDay;
//    @SerializedName("expected_release_month")
//    @Expose
//    private Object expectedReleaseMonth;
//    @SerializedName("expected_release_quarter")
//    @Expose
//    private Object expectedReleaseQuarter;
//    @SerializedName("expected_release_year")
//    @Expose
//    private Object expectedReleaseYear;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("image_tags")
    @Expose
    private List<ImageTag> imageTags = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number_of_user_reviews")
    @Expose
    private Integer numberOfUserReviews;
    @SerializedName("original_game_rating")
    @Expose
    private List<OriginalGameRating> originalGameRating = null;
    @SerializedName("original_release_date")
    @Expose
    private String originalReleaseDate;
    @SerializedName("platforms")
    @Expose
    private List<Platform> platforms = null;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param image
     * @param aliases
     * @param expectedReleaseQuarter
     * @param numberOfUserReviews
     * @param dateLastUpdated
     * @param deck
     * @param expectedReleaseYear
     * @param expectedReleaseMonth
     * @param description
     * @param expectedReleaseDay
     * @param originalReleaseDate
     * @param originalGameRating
     * @param dateAdded
     * @param platforms
     * @param apiDetailUrl
     * @param name
     * @param guid
     * @param id
     * @param imageTags
     * @param siteDetailUrl
     */
    public Result(String aliases, String apiDetailUrl, String dateAdded, String dateLastUpdated, String deck, String description, Object expectedReleaseDay, Object expectedReleaseMonth, Object expectedReleaseQuarter, Object expectedReleaseYear, String guid, Integer id, Image image, List<ImageTag> imageTags, String name, Integer numberOfUserReviews, List<OriginalGameRating> originalGameRating, String originalReleaseDate, List<Platform> platforms, String siteDetailUrl) {
        super();
        this.aliases = aliases;
        this.apiDetailUrl = apiDetailUrl;
        this.dateAdded = dateAdded;
        this.dateLastUpdated = dateLastUpdated;
        this.deck = deck;
        this.description = description;
//        this.expectedReleaseDay = expectedReleaseDay;
//        this.expectedReleaseMonth = expectedReleaseMonth;
//        this.expectedReleaseQuarter = expectedReleaseQuarter;
//        this.expectedReleaseYear = expectedReleaseYear;
        this.guid = guid;
        this.id = id;
        this.image = image;
        this.imageTags = imageTags;
        this.name = name;
        this.numberOfUserReviews = numberOfUserReviews;
        this.originalGameRating = originalGameRating;
        this.originalReleaseDate = originalReleaseDate;
        this.platforms = platforms;
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//
//    public Object getExpectedReleaseDay() {
//        return expectedReleaseDay;
//    }
//
//    public void setExpectedReleaseDay(Object expectedReleaseDay) {
//        this.expectedReleaseDay = expectedReleaseDay;
//    }
//
//    public Object getExpectedReleaseMonth() {
//        return expectedReleaseMonth;
//    }
//
//    public void setExpectedReleaseMonth(Object expectedReleaseMonth) {
//        this.expectedReleaseMonth = expectedReleaseMonth;
//    }

//    public Object getExpectedReleaseQuarter() {
//        return expectedReleaseQuarter;
//    }
//
//    public void setExpectedReleaseQuarter(Object expectedReleaseQuarter) {
//        this.expectedReleaseQuarter = expectedReleaseQuarter;
//    }
//
//    public Object getExpectedReleaseYear() {
//        return expectedReleaseYear;
//    }
//
//    public void setExpectedReleaseYear(Object expectedReleaseYear) {
//        this.expectedReleaseYear = expectedReleaseYear;
//    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfUserReviews() {
        return numberOfUserReviews;
    }

    public void setNumberOfUserReviews(Integer numberOfUserReviews) {
        this.numberOfUserReviews = numberOfUserReviews;
    }

    public List<OriginalGameRating> getOriginalGameRating() {
        return originalGameRating;
    }

    public void setOriginalGameRating(List<OriginalGameRating> originalGameRating) {
        this.originalGameRating = originalGameRating;
    }

    public String getOriginalReleaseDate() {
        return originalReleaseDate;
    }

    public void setOriginalReleaseDate(String originalReleaseDate) {
        this.originalReleaseDate = originalReleaseDate;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

}
