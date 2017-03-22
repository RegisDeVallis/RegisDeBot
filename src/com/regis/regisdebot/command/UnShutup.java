package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;

public class UnShutup 
{
    public UnShutup(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11)
        {
            if(Main.Bot.shutups.contains(message.getChannelReceiver().getServer()))
            Main.Bot.shutups.remove(message.getChannelReceiver().getServer());
            message.reply("I'm no longer shutting up.");
        }
        else
            message.reply("You don't have permission to do that.");
        
    }
}
