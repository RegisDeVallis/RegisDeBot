package com.regis.regisdebot.command.leaderboard;

import de.btobastian.javacord.entities.message.Message;

public class UpdateLeaderboards 
{
    public UpdateLeaderboards(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960")) //make sure its me
        {
            new UpdateMsgLeaderboards(message);
            new UpdateXPLeaderboards(message);
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
