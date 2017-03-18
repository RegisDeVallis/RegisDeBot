/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.regis.regisdebot;

import com.regis.regisdebot.command.xp.XP;
import com.regis.regisdebot.command.xp.AddXP;
import com.regis.regisdebot.command.leaderboard.*;
import com.regis.regisdebot.command.link.*;
import com.regis.regisdebot.command.*;
import com.regis.regisdebot.command.guide.*;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;


public class ParseCommand 
{
    public ParseCommand(Message message, DiscordAPI api)
    {
        String messageText = message.getContent().trim().toLowerCase();
        String command;
        
        System.out.println("Logged a command: " + message.getContent());
        
        //if theres no space
        if(messageText.indexOf(" ") == -1)
            command = messageText.substring(1);
        else
            command = messageText.substring(1, messageText.indexOf(" "));
        
        
        //list of commands
        if(command.equals("ping"))
            new PingPong(message);
        else if(command.equals("shutdown"))
            new Shutdown(message);
        else if(command.equals("restart"))
            new Restart(message);
        else if(command.equals("echo"))
            new Echo(message);
        else if(command.equals("test"))
            new Test(message);
        else if(command.equals("help"))
            new Help(message);
        else if(command.equals("link"))
            new Link(message);
        else if(command.equals("addlink"))
            new AddLink(message);
        else if(command.equals("links"))
            new Links(message);
        else if(command.equals("removelink"))
            new RemoveLink(message);
        else if(command.equals("reaction"))
            new Reaction(message);
        else if(command.equals("reactions"))
            new Reactions(message);
        else if(command.equals("addreaction"))
            new AddReaction(message);
        else if(command.equals("removereaction"))
            new RemoveReaction(message);
        else if(command.equals("whoareyou"))
            new WhoAreYou(message);
        else if(command.equals("whoami"))
            new WhoAmI(message);
        else if(command.equals("joined"))
            new Joined(message);
        else if(command.equals("kick"))
            new Kick(message);
        else if(command.equals("xp"))
            new XP(message);
        else if(command.equals("addxp"))
            new AddXP(message);
        else if(command.equals("stats"))
            new Stats(message);
        else if(command.equals("messages"))
            new Messages(message);
        else if(command.equals("leaderboard"))
            new Leaderboard(message);
        else if(command.equals("msgleaderboard"))
            new MsgLeaderboard(message);
        else if(command.equals("randmeme"))
            new RandMeme(message);
        else if(command.equals("guide"))
            new Guide(message);
        else if(command.equals("addguide"))
            new AddGuide(message);
        else if(command.equals("removeguide"))
            new RemoveGuide(message);
        else if(command.equals("guides"))
            new Guides(message);
        else if(command.equals("updateleaderboards"))
            new UpdateLeaderboards(message);
        else if(command.equals("updatemsgleaderboards"))
            new UpdateMsgLeaderboards(message);
        else if(command.equals("updatexpleaderboards"))
            new UpdateXPLeaderboards(message);
        else if(command.equals("servers"))
            new Servers(message);
        else if(command.equals("listservers"))
            new ListServers(message);
        else if(command.equals("invite"))
            new Invite(message);
        else if(command.equals("shutup"))
            new Shutup(message);
        else
            message.reply("I'm sorry but I dont recognize that command.");
            
    }
}
