package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class PingPong 
{
    public PingPong(Message message) 
    {
       message.reply("Pong!");
    }
   
}
