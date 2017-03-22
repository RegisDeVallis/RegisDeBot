package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.LongMessage;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;

public class Test 
{
    public Test(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11) //make sure its me
        {
            
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
