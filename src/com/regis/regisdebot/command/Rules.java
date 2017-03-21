package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.LongMessage;
import com.regis.regisdebot.command.settings.ServerSettings;
import de.btobastian.javacord.entities.message.Message;

public class Rules 
{
    public Rules(Message message)
    {
        String rules = new ServerSettings(message.getChannelReceiver().getServer()).getRules();
        if(rules.equals(""))
            message.reply("Looks like the rules haven't been added yet!");
        else
        {
            rules = "***" + message.getChannelReceiver().getServer().getName() + " Rule's:*** \n" + rules;
            new LongMessage(message, rules);
        }
        
        
    }
}
