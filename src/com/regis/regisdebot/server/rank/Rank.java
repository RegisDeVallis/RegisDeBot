package com.regis.regisdebot.server.rank;

import com.regis.regisdebot.server.ServerSettings;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;

public class Rank 
{
    int rank = 0;
    
    public Rank(Server server, User user)
    {
        if(user.getId().equals("111992351378984960")) //I automatically get a top rank
            rank = 11;
        else
        {
            ServerSettings settings = new ServerSettings(server);
            
            rank = new MyUser(user, server).getRank();
        }
    }
    
    public Rank(MyUser myUser)
    {
        this(myUser.getServer(), myUser.getUser());
    }
    
    public int get()
    {
        return rank;
    }
}
