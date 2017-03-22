package com.regis.regisdebot.command;

import com.regis.regisdebot.*;
import com.regis.regisdebot.server.rank.Rank;
import de.btobastian.javacord.entities.message.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shutdown 
{
    public Shutdown(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11) //if its me
        {
            message.delete();
        
            message.reply("Shutting down...");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shutdown.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            Main.Bot.api.setAutoReconnect(false);
            Main.Bot.api.disconnect();
            
            System.exit(0);
        }
        else
        {
            message.reply("You don't have permission to do that. ");
        }
        
        
    }
}
