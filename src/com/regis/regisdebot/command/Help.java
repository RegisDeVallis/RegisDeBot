package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.LongMessage;
import com.regis.regisdebot.command.leaderboard.CheckUserMsgLeaderboard;
import de.btobastian.javacord.entities.message.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Help 
{
    public Help(Message message)
    {
        String text = "***Help*** \n\n"
                
                + "**Daddy's Commands** ```"
                + "~shutdown - Shutdown the bot. \n"
                + "~listservers - List the servers and ids. \n"
                + "~shutup - Shut the bot up on the server. \n"
                + "~unshutup - Un-shutup the bot on the server. \n"
                + "~updateleaderboards - Update all the leaderboards. \n"
                + "~updatemsgleaderboards - Update all the message leaderboards. \n"
                + "~updatexpleaderboards - Update all the XP leaderboards. \n"
                + "~issues - View the issues. \n"
                + "~ideas - View the ideas. \n"
                + "~globalmsg - Send a global message \n"
                + "``` \n"
                
                + "**Admin Commands** ```"
                + "~setrank - Add a role to the rank list. Type ~rankinfo for more information. Usage: ~setrank RANKNUM ROLENAME  \n"
                + "~removerank - Remove a role from the rank list. Usage: ~removerank ROLENAME \n"
                + "``` \n"
                
                + "**Mod Commands** ```"
                + "~addxp - Add XP to a user. Works the same as ~xp, but no reserve is taken away. \n"
                + "~setxp - Set a users XP. Works the same as ~addxp \n"
                + "~kick - Kick the user. Usage ~kick @USER \n"
                + "~setrules - Set the server rules. Usage ~setrules RULES \n"
                + "~mute - Mute a user. Usage ~mute @USER \n"
                + "~unmute - Unmute a user. Usage ~unmute @USER \n"
                + "``` \n"
                
                + "**Trusted Commands** ```"
                + "~addlink - Add a link. Usage: ~addlink \"NAME\" LINK \n"
                + "~removelink ` Remove a link. Usage: ~removelink NAME \n"
                + "~addreaction - Add a reaction. Usage: ~addlink \"NAME\" LINK \n"
                + "~removereaction - Remove a reaction. Usage: ~removelink NAME \n"
                + "~addguide - Add a guide. Usage: ~addguide NAME GUIDE \n"
                + "~removeguide - Remove a guide. Usage: ~removeguide NAME \n"
                + "``` \n";
                
        String text2 = "**Common Commands ** ```"
                        
                + "~ping - Pong! \n"
                + "~echo - Echoes whatever is put after it. \n"
                + "~link - Link a link. Usage: ~link \"NAME\" \n"
                + "~links - View all the links on the server. \n"
                + "~reaction - Link a reaction. Usage: ~reaction \"NAME\" \n"
                + "~reactions - View all the reactions on the server. \n"
                + "~guide - View a guide. Usage: ~guide NAME \n"
                + "~guides - View all the guides. \n"
                + "~whoareyou - Find out who the bot is. \n"
                + "~whoami - Find out who you are. \n"
                + "~joined - Find out when you joined. \n"
                + "~xp - Give XP to people. Usage: ~xp @NAME # \n"
                + "~leaderboard - View the top XP holders on the server. Add a number after it up to 100 to view the top for that number. \n"
                + "~msgleaderboard - Vew the top message senders on the server. Works the same as ~leaderboard \n"
                + "~randmeme - View a random dank meme. \n"
                + "~servers - View the server count. \n"
                + "~invitetest - Get invited to the test server. \n"
                + "~issue - Add an issue with the bot. Usage: ~issue ISSUE \n"
                + "~idea - Add an idea. Usage: ~idea IDEA \n"
                + "~github - Link to the git."
                + "~getid - Get yours or somebody elses ID. \n"
                + "~getroleid - Get the ID of a rank. \n"
                + "~lenny - Lenny face. \n"
                + "~online - See how many members are online. \n"
                + "~members - View the member count. \n"
                + "~servermessages - View the message server count. \n"
                + "~globalmessages - View the total message count. \n"
                + "~serverid - Get the server ID. \n"
                + "~parts - View yours or someone elses part list. \n"
                + "~parts NAME - View the titled part list of you or somebody elses. \n"
                + "~addparts - Add a part list. Usage: ~addparts \"NAME\" LIST \n"
                + "~removeparts - Remove a part list. Usage: ~removeparts NAME \n"
                + "```";
                
        message.reply(text);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
        }
        message.reply(text2);
    }
}
