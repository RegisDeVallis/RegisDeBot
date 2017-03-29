package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;

public class UnShutup 
{
    public UnShutup(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        if(new Rank(server, message.getAuthor()).get() == 11)
        {
            if(Main.Bot.shutups.contains(server))
                Main.Bot.shutups.remove(server);
            Main.Bot.api.setGame("");
            message.reply("I'm no longer shutting up.");
        }
        else
            message.reply("You don't have permission to do that.");
        
    }
}
