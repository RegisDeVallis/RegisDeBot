package com.regis.regisdebot.command.xp;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class AddXP 
{
    MyUser myUser;
    
    public AddXP(Message message)
    {
        if(message.getContent().indexOf(" ") == -1)
            message.reply("Usage: `~addxp @NAME #`");
        else
        {
            if(message.getContent().length() > 64)
                message.reply("You can't give that much XP!");
            else
            {
                myUser = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
            
                boolean success = false;
                for(Role roles : message.getAuthor().getRoles(message.getChannelReceiver().getServer()))
                    if(roles.getName().equals("Admin"))
                    {
                        success = true;
                    }

                if(success) //if admin
                {
                    String text = message.getContent().trim();
                    text = text.substring(text.indexOf(" ") + 1);
                    text = text.substring(text.indexOf(" ") + 1);

                    if(text.startsWith("-"))
                    {
                        long amount = Long.parseLong(text.substring(1));
                        myUser.removeXP(amount);
                    }
                    else
                    {
                        long amount = Long.parseLong(text);
                        myUser.addXP(amount);
                    }
                    message.reply("You added " + text + " to " + myUser.getUser().getMentionTag() + "'s XP.");
                }
                else
                {
                    message.reply("You must be an admin to use this command.");
                }
            }
        }
    }
}
