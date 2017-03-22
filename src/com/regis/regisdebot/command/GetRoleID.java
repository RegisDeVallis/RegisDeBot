package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;

public class GetRoleID 
{
    public GetRoleID(Message message)
    {
        String text = message.getContent();
        
        if(!text.trim().contains(" "))
            message.reply("Usage: `~getroleid ROLENAME`");
        else
        {
            String roleName = text.substring(text.indexOf(" ") + 1);
            
            boolean exists = false;
            for(Role role : message.getChannelReceiver().getServer().getRoles())
                if(role.getName().toLowerCase().equals(roleName.toLowerCase()))
                    exists = true;
            
            if(exists)
            {
                String rankID = null;
                for(Role role : message.getChannelReceiver().getServer().getRoles())
                    if(role.getName().toLowerCase().equals(roleName.toLowerCase()))
                        rankID = role.getId();
                
                message.reply("The ID for " + roleName + " is " + rankID + ".");
            }
            else
                message.reply("Looks like that role doesn't exist.");
        }
    }
}
