package com.regis.regisdebot.command;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;
import java.util.Date;

public class Joined 
{
    public Joined(Message message)
    {
        //if they're asking for themselves
        if(message.getContent().trim().equals("~joined"))
        {
            MyUser theUser = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
            message.reply("You joined " + theUser.getDateJoined());
        }
        else //asking for someone else
        {
            MyUser theUser = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
            message.reply(message.getMentions().get(0).getMentionTag() + " joined " + theUser.getDateJoined());
        }
        
    }
}
