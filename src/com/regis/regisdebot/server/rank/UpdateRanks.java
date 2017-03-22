package com.regis.regisdebot.server.rank;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;

public class UpdateRanks 
{
    public UpdateRanks(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
        {
            new CheckRanks(message.getChannelReceiver().getServer());
            message.reply("Updated all the ranks.");
        }
        else
            message.reply("Looks like you don't have access to this command.");
    }
}
