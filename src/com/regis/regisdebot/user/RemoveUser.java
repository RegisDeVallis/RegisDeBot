package com.regis.regisdebot.user;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import java.io.File;

public class RemoveUser 
{
    public RemoveUser(User user, Server server)
    {
       server.getChannels().iterator().next().sendMessage("Goodbye " + user.getMentionTag() + ", we will miss you!");
        
        File file = new File("data/" + server.getId() + "/users/" + user.getId() + ".xml");
        file.delete();
    }
}
