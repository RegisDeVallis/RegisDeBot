package com.regis.regisdebot.server;

import com.regis.regisdebot.command.Rules;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class SetRules 
{
    public SetRules(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() >= 8)
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
