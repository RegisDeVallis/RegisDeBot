package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Permissions;
import de.btobastian.javacord.entities.permissions.Role;

public class Kick 
{
    Server server;
    
    public Kick(Message message)
    {
        server = message.getChannelReceiver().getServer();
        
        boolean success = false;
        
        for(Role roles : message.getAuthor().getRoles(server))
            if(roles.getName().equals("Admin"))
            {
                server.kickUser(message.getMentions().get(0));
                success = true;
            }
        
        if(!success)
            message.reply("Looks like you dont have the right permissions.");
    }
}
