package fr.intech.leaguedata.model;

import java.util.ArrayList;

public class User {
    private String id;

    private String accountId;

    private String puuid;

    private String name;

    private int profileIconId;

    private long revisionDate;

    private int summonerLevel;

    private RankedQueue[] rankedQueues;

    public User() {

    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public RankedQueue[] getRankedQueues() {
        return rankedQueues;
    }

    public void setRankedQueues(RankedQueue[] rankedQueues) {
        this.rankedQueues = rankedQueues;
    }
}
