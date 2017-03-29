package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class ServerID 
{
    public ServerID(Message message)
    {
        message.reply("The ID for this server is " + message.getChannelReceiver().getServer().getId() + ".");
    }
}
