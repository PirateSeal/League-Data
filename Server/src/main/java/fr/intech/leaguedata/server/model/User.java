
package fr.intech.leaguedata.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountId",
        "puuid",
        "name",
        "profileIconId",
        "revisionDate",
        "summonerLevel"
})
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("puuid")
    private String puuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profileIconId")
    private int profileIconId;

    @JsonProperty("revisionDate")
    private long revisionDate;

    @JsonProperty("summonerLevel")
    private int summonerLevel;

    /**
     * @param name
     */

    public User(String name) {
        this.name = name;
    }

    /**
     * @param id
     * @param accountId
     * @param name
     * @param profileIconId
     * @param revisionDate
     * @param summonerLevel
     * @param puuid
     */
    public User(String id, String accountId, String puuid, String name, int profileIconId, long revisionDate,
                int summonerLevel) {
        super();
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("puuid")
    public String getPuuid() {
        return puuid;
    }

    @JsonProperty("puuid")
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("profileIconId")
    public int getProfileIconId() {
        return profileIconId;
    }

    @JsonProperty("profileIconId")
    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    @JsonProperty("revisionDate")
    public long getRevisionDate() {
        return revisionDate;
    }

    @JsonProperty("revisionDate")
    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    @JsonProperty("summonerLevel")
    public int getSummonerLevel() {
        return summonerLevel;
    }

    @JsonProperty("summonerLevel")
    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }


}