package com.regis.regisdebot.server.rank;

import com.regis.regisdebot.server.ServerSettings;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class RemoveRank 
{
    public RemoveRank(Message message)
    {
        if(!message.getContent().trim().contains(" "))
            message.reply("Usage: `~removerank ROLENAME`");
        else
        {
            Server server = message.getChannelReceiver().getServer();
            if(new Rank(server, message.getAuthor()).rank == 10)
            {
                ServerSettings settings = new ServerSettings(server);
                
                String roleName = message.getContent().substring(message.getContent().indexOf(" ") + 1);
                String rankID = null;
                for(Role role : server.getRoles())
                    if(role.getName().toLowerCase().equals(roleName.toLowerCase()))
                        rankID = role.getId();
                
                if(settings.rankIDs.contains(rankID))
                {
                    int index = settings.rankIDs.indexOf(rankID);
                    settings.rankIDs.remove(index);
                    settings.rankNums.remove(index);
                    settings.save();
                    message.reply("Removed rank.");
                    new CheckRanks(server);
                }
                else
                    message.reply("Either you typed it in incorrectly, or it wasnt on the rank list.");
            }
            else
                message.reply("You don't have permission to do this.");
        }
    }
}
