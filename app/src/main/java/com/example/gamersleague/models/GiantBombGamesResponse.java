
package com.example.gamersleague.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class GiantBombGamesResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GiantBombGamesResponse() {
    }

    /**
     * 
     * @param results
     * @param version
     */
    public GiantBombGamesResponse(List<Result> results, String version) {
        super();
        this.results = results;
        this.version = version;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
