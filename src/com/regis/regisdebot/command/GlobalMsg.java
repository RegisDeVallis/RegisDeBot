package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;

public class GlobalMsg 
{
    public GlobalMsg(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11)
            for(Server server : Main.Bot.api.getServers())
                for(Channel channel : server.getChannels())
                    channel.sendMessage(message.getContent().substring(message.getContent().indexOf(" ")));
        else
            message.reply("You definently don't have permission for this.");
    }
}
