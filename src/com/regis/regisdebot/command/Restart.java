package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restart 
{
    public Restart(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11) //if its me
        {
            message.delete();
            message.reply("This action is currently disabled because api.disconnect() is broken.");
            //message.reply("Restarting...");

            //Main.restart();
        }
        else
        {
            message.reply("You don't have permission to do that.");
        }
    }
}
