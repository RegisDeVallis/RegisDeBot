package com.regis.regisdebot.command.parts;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class RemoveParts 
{
    public RemoveParts(Message message)
    {
        MyUser myUser = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
        
        String listName = message.getContent().trim().substring(message.getContent().indexOf(" ") + 1);
        
        if(myUser.getPartNames().contains(listName))
        {
            myUser.removeParts(listName);
            message.reply("Parts list removed!");
        }
        else
            message.reply("Looks like that parts list doesn't exist!");
    }
}
