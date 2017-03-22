package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.GetName;
import com.regis.regisdebot.server.ServerSettings;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class UnMute 
{
    public UnMute(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        boolean unmute = false;
        if(new Rank(server, message.getAuthor()).get() >= 8)
        {
            new ServerSettings(server).removeMute(message.getMentions().get(0));
            message.reply("Unmuted " + new GetName(message.getMentions().get(0), message.getChannelReceiver().getServer()));
            unmute = true;
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
