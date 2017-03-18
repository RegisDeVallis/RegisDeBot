/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.regis.regisdebot;

import com.regis.regisdebot.util.Console;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConsoleCommands 
{
    private Console console;
    private RegisDeBot Bot;
    
    public ConsoleCommands(Console console, RegisDeBot Bot)
    {
        this.console = console;
        this.Bot = Bot;
        
        while(true)
        {
            //Thread.sleep(100);
            
            
            String messageText = "";
            try {
                messageText = console.answer(); //remember answer() waits for an answer from the user
            } catch (InterruptedException ex) {
                Logger.getLogger(ConsoleCommands.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ConsoleCommands.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConsoleCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
            String command;
            
            if(messageText.indexOf(" ") == -1)
                command = messageText.substring(0);
            else
                command = messageText.substring(0, messageText.indexOf(" "));
            
            if(command.equals("shutdown"))
                Shutdown();
            else if(command.equals("globalMsg"))
                globalMessage(messageText);
        }
    }
    
    private void globalMessage(String message)
    {
        //cut the message down
        message = message.substring(message.indexOf(" "), message.length());
        
        for(Server server : Bot.api.getServers())
            for(Channel channel : server.getChannels())
                channel.sendMessage(message);
        
        
        
    }
    
    private void Shutdown()
    {
       Bot.api.disconnect();
       System.exit(0);
    }
}
