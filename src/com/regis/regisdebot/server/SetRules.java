package com.regis.regisdebot.server;

import com.regis.regisdebot.command.Rules;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class SetRules 
{
    public SetRules(Message message)
    {
        for(Role roles : message.getAuthor().getRoles(message.getChannelReceiver().getServer()))
            if(roles.getName().equals("Admin"))
             {
                if(message.getContent().trim().indexOf(" ") == -1)
                    message.reply("Usage: `~setrules RULES`");
                else
                {
                    String rules = message.getContent().substring(message.getContent().indexOf(" "));
                    new ServerSettings(message.getChannelReceiver().getServer()).setRules(rules);
                    message.reply("Rules set!");

                    new Rules(message);
                }
            }
            else
                message.reply("You don't have permission to do this.");
    }
}
