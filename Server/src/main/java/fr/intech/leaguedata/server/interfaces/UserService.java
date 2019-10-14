package fr.intech.leaguedata.server.interfaces;

import fr.intech.leaguedata.server.model.RankedQueue;
import fr.intech.leaguedata.server.model.User;


public interface UserService {


    /**
     * @param name Summoner Name in LoL, cannnot be null
     * @return User pojo, null if not found
     */
    public User getUserByName(String name);

    /**
     * @param name  Summoner Name in LoL, cannnot be null
     * @param queue Desired queue
     * @return
     */
    public RankedQueue getUserQueueInfo(String name, String queue);

    /**
     *
     * @param name
     * @return
     */
    public RankedQueue[] getUserQueuesInfo(String name);
}
