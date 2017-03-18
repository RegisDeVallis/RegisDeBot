package com.regis.regisdebot.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import java.util.concurrent.Future;

public class Echo 
{
    public Echo(Message message)
    {
        String text  = message.getContent().substring(message.getContent().indexOf(" "));
        message.reply(text);
    }
}
