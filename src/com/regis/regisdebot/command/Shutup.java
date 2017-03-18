package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import de.btobastian.javacord.entities.message.Message;

public class Shutup 
{
    public Shutup(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
        {
           Main.Bot.shutups.add(message.getChannelReceiver().getServer());
            message.reply("Shutting up..."); 
        }
        else
            message.reply("You dont have permission to do that.");
        
    }
}
