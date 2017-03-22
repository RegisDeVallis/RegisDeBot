package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class Members 
{
    public Members(Message message)
    {
        message.reply("There are " + message.getChannelReceiver().getServer().getMemberCount() + " members on this server.");
    }
}
