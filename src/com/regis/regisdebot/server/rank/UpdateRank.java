package com.regis.regisdebot.server.rank;

import com.regis.regisdebot.server.ServerSettings;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.permissions.Role;

public class UpdateRank 
{
    public UpdateRank(User user, Server server)
    {
        MyUser myUser = new MyUser(user, server);
        ServerSettings settings = new ServerSettings(server);
        int rank = 1;
        
        for(Role role : user.getRoles(server))
            for(String id : settings.rankIDs)
                if(id.equals(role.getId()))
                {
                    int index = settings.rankIDs.indexOf(role.getId());
                    if(settings.rankNums.get(index) > rank)
                        rank = settings.rankNums.get(index);
                }
        myUser.setRank(rank);
        
    }
}
