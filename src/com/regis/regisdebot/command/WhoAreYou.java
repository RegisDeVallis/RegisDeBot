/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;

public class WhoAreYou 
{
    public WhoAreYou(Message message)
    {
        message.reply("I'm RegisDeBot, a bot created by Regis_DeVallis.");
    }
}
