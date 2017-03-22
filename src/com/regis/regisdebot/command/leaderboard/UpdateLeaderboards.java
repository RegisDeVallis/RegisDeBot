package com.regis.regisdebot.command.leaderboard;

import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;

public class UpdateLeaderboards 
{
    public UpdateLeaderboards(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11) //make sure its me
        {
            new UpdateMsgLeaderboards(message);
            new UpdateXPLeaderboards(message);
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
