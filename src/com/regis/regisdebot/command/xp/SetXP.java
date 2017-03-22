package com.regis.regisdebot.command.xp;

import com.regis.regisdebot.botutil.GetName;
import com.regis.regisdebot.server.rank.Rank;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class SetXP 
{
    public SetXP(Message message)
    {
        if(message.getContent().indexOf(" ") == -1)
            message.reply("Usage: `~setxp @NAME #`");
        else if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() >= 8)
        {
            if(message.getContent().length() > 64)
                message.reply("That's above the XP limit.");
            else
            {
                MyUser myUser = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
                
                String text = message.getContent().trim();
                text = text.substring(text.indexOf(" ") + 1);
                text = text.substring(text.indexOf(" ") + 1);

                if(text.startsWith("-"))
                    message.reply("You can't set negative XP!");
                
                else
                {
                    long amount = Long.parseLong(text);
                    myUser.addXP(amount);
                }
                message.reply("You set " + new GetName(myUser.getUser(), myUser.getServer()) + "'s XP to " + text + ".");
            }
        }
    }
}
