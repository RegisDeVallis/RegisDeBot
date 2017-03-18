package com.regis.regisdebot.command;

import com.regis.regisdebot.Main;
import de.btobastian.javacord.entities.message.Message;

public class Servers 
{
    public Servers(Message message)
    {
        message.reply("I am a part of " + Main.Bot.api.getServers().size() + " servers!");
    }
}
