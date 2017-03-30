package com.regis.regisdebot.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import java.util.concurrent.Future;

public class Echo 
{
    public Echo(Message message)
    {
        String text  = message.getContent().substring(message.getContent().indexOf(" ") + 1);
        
        if(!text.startsWith("~"))
            if(message.getMentions().size() == 0)
                if(!text.contains("@everyone") && !text.contains("@here"))
                    message.reply(text);
    }
}
