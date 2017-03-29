package com.regis.regisdebot.command.parts;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class AddParts 
{
    public AddParts(Message message)
    {
        MyUser myUser = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
        
        if(!message.getContent().trim().contains(" "))
            message.reply("Usage: ~addparts \"NAME\" PARTS");
        else 
        {
            String name = message.getContent().substring(message.getContent().indexOf("\"") + 1);
            name = name.substring(0, name.indexOf("\"")).toLowerCase();
            
            String parts = message.getContent().substring(message.getContent().indexOf("\"") + 1);
            parts = parts.substring(parts.indexOf("\""));
            parts = parts.substring(parts.indexOf(" ") + 1);
            
            myUser.addParts(name, parts);
            message.reply("Added parts");
        }
    }
}
