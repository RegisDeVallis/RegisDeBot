package com.regis.regisdebot.botutil;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;

public class GetName 
{
    String name;
    
    public GetName(User user, Server server)
    {        
        if(user.getNickname(server) == null)
            name = user.getName();
        else
            name = user.getNickname(server);
            
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
