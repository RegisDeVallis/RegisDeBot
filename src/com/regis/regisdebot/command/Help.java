package com.regis.regisdebot.command;

import com.regis.regisdebot.botutil.LongMessage;
import com.regis.regisdebot.command.leaderboard.CheckUserMsgLeaderboard;
import de.btobastian.javacord.entities.message.Message;

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
                + "``` \n"
                
                + "**Admin Commands** ```"
                + "~addxp - Add XP to a user. Works the same as ~xp, but no reserve is taken away. \n"
                + "~kick - Kick the user. Usage ~kick @USER \n"
                + "~setrules - Set the server rules. Usage ~setrules RULES \n"
                + "~mute - Mute a user. Usage ~mute @USER \n"
                + "~unmute - Unmute a user. Usage ~unmute @USER \n"
                + "``` \n"
                
                + "**Common Commands ** ```"
                + "~ping - Pong! \n"
                + "~echo - Echoes whatever is put after it. \n"
                + "~link - Link a link. - Usage: ~link \"NAME\" \n"
                + "~links - View all the links on the server. \n"
                + "~addlink - Add a link. - Usage: ~addlink \"NAME\" LINK \n"
                + "~removelink ` Remove a link. - Usage: ~removelink NAME \n"
                + "~reaction - Link a reaction. - Usage: ~reaction \"NAME\" \n"
                + "~reactions - View all the reactions on the server. \n"
                + "~addreaction - Add a reaction. - Usage: ~addlink \"NAME\" LINK \n"
                + "~removereaction - Remove a reaction. - Usage: ~removelink NAME \n"
                + "~whoareyou - Find out who the bot is. \n"
                + "~whoami - Find out who you are. \n"
                + "~joined - Find out when you joined. \n"
                + "~xp - Give XP to people.  - Usage: ~xp @NAME # \n"
                + "~leaderboard - View the top XP holders on the server. Add a number after it up to 100 to view the top for that number. \n"
                + "~msgleaderboard - Vew the top message senders on the server. Works the same as ~leaderboard \n"
                + "~randmeme - View a random dank meme. \n"
                + "~servers - View the server count. \n"
                + "~invitetest - Get invited to the test server. \n"
                + "~issue - Add an issue with the bot. Usage: ~issue ISSUE \n"
                + "~idea - Add an idea. Usage: ~idea IDEA \n"
                + "~github - Link to the git."
                + "```";
        
        new LongMessage(message, text);
        
        
    }
}
