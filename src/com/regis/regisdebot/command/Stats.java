package com.regis.regisdebot.command;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class Stats 
{
    public Stats(Message message)
    {
        if(message.getContent().trim().indexOf(" ") == -1)
        {
            MyUser user = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
            message.reply("You have " + user.getXP() + " XP and " + user.getXPReserve() + " XP reserve.");
        }
        else
        {
            MyUser user = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
            message.reply(message.getMentions().get(0).getMentionTag() + " has " + user.getXP() + " XP and " + user.getXPReserve() + " XP reserve.");
        }
        
    }
    
}
