package com.regis.regisdebot.command;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;

public class ServerMessages 
{
    public ServerMessages(Message message)
    {
        long msg = 0;
        for(User user : message.getChannelReceiver().getServer().getMembers())
            msg += new MyUser(user, message.getChannelReceiver().getServer()).getMessages();
        
        message.reply("I have seen " + msg + " messages sent in this server.");
    }
}
