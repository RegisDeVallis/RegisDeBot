package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.GetName;
import de.btobastian.javacord.entities.message.Message;

public class GetID 
{
    public GetID(Message message)
    {
        if(message.getMentions().isEmpty())
            message.reply("Your ID is " + message.getAuthor().getId());
        else
            message.reply(new GetName(message.getMentions().get(0), message.getChannelReceiver().getServer()) + "'s ID is " + message.getMentions().get(0).getId());
    }
}
