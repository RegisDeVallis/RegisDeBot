/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.regis.regisdebot.command;

import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class Messages 
{
    public Messages(Message message)
    {
        
        if(message.getContent().trim().indexOf(" ") == -1)
        {
            MyUser user = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
            message.reply("You have sent " + user.getMessages() + " messages.");
        }
        else
        {
            MyUser user = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
            message.reply(message.getMentions().get(0).getMentionTag() + " has sent " + user.getMessages() + " messages");
        }
    }
}
