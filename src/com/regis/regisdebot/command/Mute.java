package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.GetName;
import com.regis.regisdebot.server.ServerSettings;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class Mute 
{
    public Mute(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        boolean mute = false;
        
        if(!message.getMentions().get(0).getId().equals("290195108681351179")) //can't mute the bot
            for(Role roles : message.getAuthor().getRoles(server))
                if(roles.getName().equals("Admin"))
                {
                    System.out.println("Muting");
                    new ServerSettings(server).addMute(message.getMentions().get(0));
                    message.reply("Muted " + new GetName(message.getMentions().get(0), message.getChannelReceiver().getServer()));
                    mute = true;
                }
        if(!mute)
            message.reply("Either you failed to mute the user, or you don't have permission to do that.");
    }
}
