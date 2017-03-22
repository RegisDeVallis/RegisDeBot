package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.GetName;
import de.btobastian.javacord.entities.message.Message;

public class WhoAmI 
{
    public WhoAmI(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
            message.reply("You're my Dad!");
        else
        {
            message.reply("You are " + new GetName(message.getAuthor(), message.getChannelReceiver().getServer()));
        }
        
    }
}
