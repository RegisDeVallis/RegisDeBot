package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class Invite 
{
    public Invite(Message message)
    {
        message.reply("Invite me to your server with this link: \n https://discordapp.com/api/oauth2/authorize?client_id=290195108681351179&scope=bot&permissions=0");
    }
}
