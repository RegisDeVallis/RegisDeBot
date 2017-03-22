package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.UserStatus;
import de.btobastian.javacord.entities.message.Message;

public class Online 
{
    public Online(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        int total = server.getMemberCount();
        int online = 0;
        for(User user : server.getMembers())
            if(user.getStatus().equals(UserStatus.ONLINE))
                online++;
        
        message.reply("There are " + online + " online out of " + total + " total members.");
    }
}
