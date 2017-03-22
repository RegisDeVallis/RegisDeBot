package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.botutil.LongMessage;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;

public class ListServers 
{
    public ListServers(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11)
        {
            String text = "``` List of servers \n\n";
            
            int i = 1;
            for(Server server : Main.Bot.api.getServers())
            {
               text += i + ". " + server.getName() + " - " + server.getId() + "\n";
               i++;
            }
            
            text += "```";
            new LongMessage(message, text);
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
