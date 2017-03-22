package com.regis.regisdebot.server.rank;

import com.regis.regisdebot.server.ServerSettings;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class SetRank 
{
    public SetRank(Message message)
    {
        Server server = message.getChannelReceiver().getServer();
        ServerSettings settings = new ServerSettings(server);
        String text = message.getContent();
        
        if(!text.trim().contains(" "))
            message.reply("Usage: `~setrank RANKNUM ROLENAME`");
        else if(new Rank(server, message.getAuthor()).get() == 10)
        {
            String r = text.substring(text.indexOf(" ") + 1);
            r = r.substring(0, r.indexOf(" "));
            int rank = Integer.parseInt(r);
            //System.out.println("Parsed Rank: " + rank);
            
            String roleName = text.substring(text.indexOf(" ") + 1);
            roleName = roleName.substring(roleName.indexOf(" ") + 1);
            //System.out.println("Parsed RoleName: " + roleName);
            
            boolean exists = false;
            for(Role role : server.getRoles())
                if(role.getName().toLowerCase().equals(roleName.toLowerCase()))
                    exists = true;
            
            if(exists)
            {
                if(rank < 1 || rank > 10)
                    message.reply("Rank must be between 1 and 10.");
                else
                {
                    String rankID = null;
                    for(Role role : server.getRoles())
                        if(role.getName().toLowerCase().equals(roleName.toLowerCase()))
                            rankID = role.getId();
                    
                    //remove the rank if it exists
                    if(settings.rankIDs.contains(rankID))
                    {
                        int index = settings.rankIDs.indexOf(rankID);
                        settings.rankIDs.remove(index);
                        settings.rankNums.remove(index);
                    }
                    
                    settings.addRank(rank, rankID);
                    message.reply("Added rank to " + roleName + ".");
                    new CheckRanks(server);
                }
            }
            else
                message.reply("Looks like that role doesn't exist");
        }
        else
            message.reply("You don't have permission to do that.");
    }
}
