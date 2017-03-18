package com.regis.regisdebot.command.xp;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class XP 
{
    MyUser userGet;
    MyUser userSend;
    
    public XP(Message message)
    {
        if(message.getContent().indexOf(" ") == -1)
            message.reply("Usage: `~xp @NAME #`");
        else
        {
            if(message.getContent().length() > 64)
                message.reply("You can't give that much XP!");
            else
            {
                userGet = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
                userSend = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());

                if(userGet.getUser().getId().equals(userSend.getUser().getId()))
                {
                    message.reply("You cant give yourself XP!");
                }
                else
                {
                    String text = message.getContent().trim();
                    text = text.substring(text.indexOf(" ") + 1);
                    text = text.substring(text.indexOf(" ") + 1);

                    if(text.startsWith("-"))
                    {
                        message.reply("You can't give negative XP!");
                    }
                    else 
                    {
                        long amount = Integer.parseInt(text);
                        
                        if(amount == 0)
                            message.reply("You can't give 0 xp!");
                        else
                        {
                            if(userSend.getXPReserve() < amount)
                                message.reply("Looks like you dont have enough XP reserve!");
                            else
                            {
                                userGet.addXP(amount);
                                userSend.removeXPReserve(amount);
                                message.reply(message.getAuthor().getMentionTag() + " gave " + message.getMentions().get(0).getMentionTag() + " " + amount + " XP!");
                            } 
                        }
                        

                        
                    }
                }
            }
            
        }
        
    }
}
