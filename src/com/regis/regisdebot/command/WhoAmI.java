package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class WhoAmI 
{
    public WhoAmI(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
            message.reply("You're my Dad!");
        else
        {
            if(message.getAuthor().getNickname(message.getChannelReceiver().getServer()) == null)
                message.reply("You are " + message.getAuthor().getName() + ".");
            else
                message.reply("You are " + message.getAuthor().getNickname(message.getChannelReceiver().getServer()) + ".");
        }
        
    }
}
