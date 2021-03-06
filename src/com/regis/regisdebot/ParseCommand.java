package com.regis.regisdebot;

import com.regis.regisdebot.command.GetRoleID;
import com.regis.regisdebot.server.SetRules;
import com.regis.regisdebot.command.xp.*;
import com.regis.regisdebot.command.leaderboard.*;
import com.regis.regisdebot.command.link.*;
import com.regis.regisdebot.command.*;
import com.regis.regisdebot.command.feedback.*;
import com.regis.regisdebot.command.guide.*;
import com.regis.regisdebot.command.parts.*;
import com.regis.regisdebot.server.rank.*;
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
        else if(command.equals("info"))
            new Info(message);
        else if(command.equals("home"))
            new Home(message);
        else if(command.equals("rules"))
            new Rules(message);
        else if(command.equals("setrules"))
            new SetRules(message);
        else if(command.equals("issue"))
            new Issue(message);
        else if(command.equals("issues"))
            new Issues(message);
        else if(command.equals("idea"))
            new Idea(message);
        else if(command.equals("ideas"))
            new Ideas(message);
        else if(command.equals("github"))
            new Github(message);
        else if(command.equals("invitetest"))
            new InviteTest(message);
        else if(command.equals("mute"))
            new Mute(message);
        else if(command.equals("unmute"))
            new UnMute(message);
        else if(command.equals("getid"))
            new GetID(message);
        else if(command.equals("setrank"))
            new SetRank(message);
        else if(command.equals("removerank"))
            new RemoveRank(message);
        else if(command.equals("updateranks"))
            new UpdateRanks(message);
        else if(command.equals("rankinfo"))
            new RankInfo(message);
        else if(command.equals("lenny"))
            new Lenny(message);
        else if(command.equals("online"))
            new Online(message);
        else if(command.equals("members"))
            new Members(message);
        else if(command.equals("setxp"))
            new SetXP(message);
        else if(command.equals("getroleid"))
            new GetRoleID(message);
        else if(command.equals("globalmsg"))
            new GlobalMsg(message);
        else if(command.equals("servermessages"))
            new ServerMessages(message);
        else if(command.equals("globalmessages"))
            new GlobalMessages(message);
        else if(command.equals("serverid"))
            new ServerID(message);
        else if(command.equals("parts"))
            new Parts(message);
        else if(command.equals("addparts"))
            new AddParts(message);
        else if(command.equals("removeparts"))
            new RemoveParts(message);
        else
            message.reply("I'm sorry but I dont recognize that command.");
            
    }
}
