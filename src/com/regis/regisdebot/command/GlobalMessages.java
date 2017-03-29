package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;

public class GlobalMessages 
{
    public GlobalMessages(Message message)
    {
        long msg = 0;
        for(Server server : Main.Bot.api.getServers())
            for(User user : server.getMembers())
                msg += new MyUser(user, server).getMessages();
        message.reply("I have counted a total " + msg + " messages.");
    }
}
