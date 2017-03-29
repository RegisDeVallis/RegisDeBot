package com.regis.regisdebot.command.parts;

import com.regis.regisdebot.botutil.GetName;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.entities.message.Message;

public class Parts 
{
    public Parts(Message message)
    {
        MyUser myUser;
        
        if(message.getMentions().size() == 0)
            myUser = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
        else
            myUser = new MyUser(message.getMentions().get(0), message.getChannelReceiver().getServer());
        
        //if nothing after command
        if(!message.getContent().trim().contains(" "))
        {
            System.out.println("parts with no mention");
            if(myUser.getPartNames().size() != 0)
            {
                String partsList = "**" + new GetName(myUser.getUser(), myUser.getServer()).toString() + "** Parts Lists ```";

                for(String name : myUser.getPartNames())
                    partsList += "  - " + name + "\n";

                partsList += "```";
                message.reply(partsList);
            } else
                message.reply("Looks like there haven't been any parts added yet!");
            
        }
        //if user is mentioned but no part list name
        else if(message.getMentions().size() == 1 && !message.getContent().substring(message.getContent().indexOf(">")).contains(" "))
        {
            if(myUser.getPartNames().size() != 0)
            {
                String partsList = "**" + new GetName(myUser.getUser(), myUser.getServer()).toString() + "** Parts Lists ```";

                for(String name : myUser.getPartNames())
                    partsList += "  - " + name + "\n";

                partsList += "```";
                message.reply(partsList);
            } else
                message.reply("Looks like there haven't been any parts added yet!");
        }
        
        //if its just a name of a parts list
        else if(message.getContent().trim().contains(" ") && message.getMentions().size() == 0)
        {
            String listName = message.getContent().trim().substring(message.getContent().indexOf(" ") + 1);
            System.out.println("ListName:" + listName);
            
            if(myUser.getPartNames().contains(listName))
                message.reply("**" + listName + "**\n" + myUser.getPartLists().get(myUser.getPartNames().indexOf(listName)));
            else
                message.reply("Looks like there isn't a parts list with that name!");
        }
        
        //if its a mention and it requestis a part name
        else if(message.getContent().contains(" ") && message.getMentions().size() == 1)
        {
            String listName = message.getContent().trim().substring(message.getContent().indexOf(">") + 2);
            
            if(myUser.getPartNames().contains(listName))
                message.reply("**" + listName + "**\n" + myUser.getPartLists().get(myUser.getPartNames().indexOf(listName)));
            else
                message.reply("Looks like there isn't a parts list with that name!");
        }
    }
}
