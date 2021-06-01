
package com.example.gamersleague.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Platform {

    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Platform() {
    }

    /**
     * 
     * @param apiDetailUrl
     * @param name
     * @param id
     * @param abbreviation
     * @param siteDetailUrl
     */
    public Platform(String apiDetailUrl, Integer id, String name, String siteDetailUrl, String abbreviation) {
        super();
        this.apiDetailUrl = apiDetailUrl;
        this.id = id;
        this.name = name;
        this.siteDetailUrl = siteDetailUrl;
        this.abbreviation = abbreviation;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
