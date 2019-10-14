package fr.intech.leaguedata.server.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "queueType",
        "summonerName",
        "hotStreak",
        "wins",
        "veteran",
        "losses",
        "rank",
        "tier",
        "inactive",
        "freshBlood",
        "leagueId",
        "summonerId",
        "leaguePoints"
})
public class RankedQueue {

    @JsonProperty("queueType")
    private String queueType;
    @JsonProperty("summonerName")
    private String summonerName;
    @JsonProperty("hotStreak")
    private boolean hotStreak;
    @JsonProperty("wins")
    private int wins;
    @JsonProperty("veteran")
    private boolean veteran;
    @JsonProperty("losses")
    private int losses;
    @JsonProperty("rank")
    private String rank;
    @JsonProperty("tier")
    private String tier;
    @JsonProperty("inactive")
    private boolean inactive;
    @JsonProperty("freshBlood")
    private boolean freshBlood;
    @JsonProperty("leagueId")
    private String leagueId;
    @JsonProperty("summonerId")
    private String summonerId;
    @JsonProperty("leaguePoints")
    private int leaguePoints;

    /**
     * No args constructor for use in serialization
     */
    public RankedQueue() {
    }

    /**
     * @param leaguePoints
     * @param leagueId
     * @param inactive
     * @param rank
     * @param hotStreak
     * @param veteran
     * @param queueType
     * @param freshBlood
     * @param tier
     * @param losses
     * @param summonerName
     * @param summonerId
     * @param wins
     */
    public RankedQueue(String queueType, String summonerName, boolean hotStreak, int wins, boolean veteran, int losses,
                       String rank, String tier, boolean inactive, boolean freshBlood, String leagueId,
                       String summonerId, int leaguePoints) {
        super();
        this.queueType = queueType;
        this.summonerName = summonerName;
        this.hotStreak = hotStreak;
        this.wins = wins;
        this.veteran = veteran;
        this.losses = losses;
        this.rank = rank;
        this.tier = tier;
        this.inactive = inactive;
        this.freshBlood = freshBlood;
        this.leagueId = leagueId;
        this.summonerId = summonerId;
        this.leaguePoints = leaguePoints;
    }

    @JsonProperty("queueType")
    public String getQueueType() {
        return queueType;
    }

    @JsonProperty("queueType")
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    @JsonProperty("summonerName")
    public String getSummonerName() {
        return summonerName;
    }

    @JsonProperty("summonerName")
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    @JsonProperty("hotStreak")
    public boolean isHotStreak() {
        return hotStreak;
    }

    @JsonProperty("hotStreak")
    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    @JsonProperty("wins")
    public int getWins() {
        return wins;
    }

    @JsonProperty("wins")
    public void setWins(int wins) {
        this.wins = wins;
    }

    @JsonProperty("veteran")
    public boolean isVeteran() {
        return veteran;
    }

    @JsonProperty("veteran")
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    @JsonProperty("losses")
    public int getLosses() {
        return losses;
    }

    @JsonProperty("losses")
    public void setLosses(int losses) {
        this.losses = losses;
    }

    @JsonProperty("rank")
    public String getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(String rank) {
        this.rank = rank;
    }

    @JsonProperty("tier")
    public String getTier() {
        return tier;
    }

    @JsonProperty("tier")
    public void setTier(String tier) {
        this.tier = tier;
    }

    @JsonProperty("inactive")
    public boolean isInactive() {
        return inactive;
    }

    @JsonProperty("inactive")
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @JsonProperty("freshBlood")
    public boolean isFreshBlood() {
        return freshBlood;
    }

    @JsonProperty("freshBlood")
    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    @JsonProperty("leagueId")
    public String getLeagueId() {
        return leagueId;
    }

    @JsonProperty("leagueId")
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    @JsonProperty("summonerId")
    public String getSummonerId() {
        return summonerId;
    }

    @JsonProperty("summonerId")
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    @JsonProperty("leaguePoints")
    public int getLeaguePoints() {
        return leaguePoints;
    }

    @JsonProperty("leaguePoints")
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

}