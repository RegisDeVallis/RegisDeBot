package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;

public class Shutup 
{
    public Shutup(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11)
        {
           Main.Bot.shutups.add(message.getChannelReceiver().getServer());
            message.reply("Shutting up..."); 
        }
        else
            message.reply("You dont have permission to do that.");
        
    }
}
