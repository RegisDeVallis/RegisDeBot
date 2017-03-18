package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.LongMessage;
import de.btobastian.javacord.entities.message.Message;

public class Test 
{
    public Test(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960")) //make sure its me
        {
            
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
