package com.regis.regisdebot.command.leaderboard;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.server.rank.Rank;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;

public class UpdateMsgLeaderboards 
{
    public UpdateMsgLeaderboards(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11) //make sure its me
        {
            message.reply("Updating message leaderboards...");
            
            for(Server server : Main.Bot.api.getServers())
            {
                File file = new File("data/" + server.getId() + "/msgLeaderboard.xml");
                if(file.exists())
                    file.delete();
                            
                for(User user : server.getMembers())
                    new CheckUserMsgLeaderboard(new MyUser(user, server));
            }
            
            message.reply("Updated message leaderboards.");
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
