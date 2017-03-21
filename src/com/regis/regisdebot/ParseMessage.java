package com.regis.regisdebot;

import static com.regis.regisdebot.Main.Bot;
import com.regis.regisdebot.botutil.Subreddit;
import com.regis.regisdebot.command.UnShutup;
import com.regis.regisdebot.command.settings.ServerSettings;
import com.regis.regisdebot.user.MyUser;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;

public class ParseMessage 
{
    Message message;
    DiscordAPI api;
    Server server;
    User user;
    ParseCommand parseCommand;
    
    public ParseMessage(Message message, DiscordAPI api)
    {
        this.message = message;
        this.api = api;
        this.server = message.getChannelReceiver().getServer();
        this.user = message.getAuthor();
        MyUser myUser = new MyUser(user, server);
        
        //make sure the api is synced
        Main.Bot.api = this.api;
        
        //first add xp reserve
        myUser = new MyUser(message.getAuthor(), message.getChannelReceiver().getServer());
        myUser.addXPReserve(1);
        myUser.addMessage(1);
        
        //check if the bot us shutup'd
        if(!Main.Bot.shutups.contains(message.getChannelReceiver().getServer()))
        {
            //check if the user is muted //I can't spell
            ServerSettings settings = new ServerSettings(message.getChannelReceiver().getServer());

            for(String user : settings.mutes)
                if(user.equals(message.getAuthor().getId()))
                    message.delete();
                
            if(!message.isDeleted()) //if it isnt deleted
            {
                //if the message is text
                if(message.getAttachments().isEmpty())
                {
                   //if it starts with the command symbol
                    if(message.getContent().trim().substring(0, 1).equals("~"))
                        parseCommand = new ParseCommand(message, api);
                    else if(message.getMentions().contains(message.getChannelReceiver().getServer().getMemberById("290195108681351179"))) //if it mentions the bot
                        message.reply("Wat");
                    if(message.getContent().contains("r/"))
                        new Subreddit(message);
                }
            }
            
        }
        else
            if(message.getAttachments().isEmpty())
                if(message.getContent().toLowerCase().equals("~unshutup"))
                    new UnShutup(message);
        
        

        myUser = null;
    }
}
