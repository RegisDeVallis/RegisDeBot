package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import de.btobastian.javacord.entities.message.Message;

public class UnShutup 
{
    public UnShutup(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
        {
            if(Main.Bot.shutups.contains(message.getChannelReceiver().getServer()))
            Main.Bot.shutups.remove(message.getChannelReceiver().getServer());
            message.reply("I'm no longer shutting up.");
        }
        else
            message.reply("You don't have permission to do that.");
        
    }
}
