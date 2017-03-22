package com.regis.regisdebot.server.rank;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;

public class CheckRanks 
{
    public CheckRanks(Server server)
    {
        for(User user : server.getMembers())
            new UpdateRank(user, server);
        
    }
}
