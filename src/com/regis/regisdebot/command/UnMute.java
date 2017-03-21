package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.GetName;
import com.regis.regisdebot.command.settings.ServerSettings;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class UnMute 
{
    public UnMute(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        boolean unmute = false;
        for(Role roles : message.getAuthor().getRoles(server))
            if(roles.getName().equals("Admin"))
            {
                new ServerSettings(server).removeMute(message.getMentions().get(0));
                message.reply("Unmuted " + new GetName(message.getMentions().get(0), message.getChannelReceiver().getServer()));
                unmute = true;
            }
        if(!unmute)
            message.reply("Either you failed to unmute the user, or you don't have permission to do that.");
    }
}
