package com.regis.regisdebot.botutil;

import de.btobastian.javacord.entities.message.Message;
import java.util.ArrayList;

public class LongMessage 
{
    public LongMessage(Message message, String text)
    {
        ArrayList<String> toSend = new ArrayList();
        
        //split the message up
        int length = (text.length() / 2000) + 1;
        for(int i = 0; i < length; i++)
        {
            if(text.length() < 2000)
                toSend.add(text);
            else
            {
                toSend.add(text.substring(0, 2000)); 
                text = text.substring(2000);
            }
        }
        
        //send the message
        for(int i = 0; i < toSend.size(); i++)
            message.reply(toSend.get(i));
        
    }
}
