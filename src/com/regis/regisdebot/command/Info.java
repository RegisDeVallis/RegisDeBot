package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class Info 
{
    public Info(Message message)
    {
        message.reply("This bot was written by Regis_DeVallis.");
    }
}
