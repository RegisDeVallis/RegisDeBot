package com.regis.regisdebot.command.link;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reaction 
{
    Server server;
    ArrayList<String> names = new ArrayList();
    ArrayList<String> links = new ArrayList();
    File file;
    
    public Reaction(Message message) 
    {
        server = message.getChannelReceiver().getServer();
        file = new File("data/" + server.getId() + "/reactions.xml");
        
        if(file.exists())
        {
           if(message.getContent().trim().indexOf(" ") == -1)
               message.reply("Usage: `~reaction \"NAME\"`");
           else
           {
               load();
               
               String link = message.getContent().substring(message.getContent().indexOf(" ") + 1).toLowerCase();
               if(names.contains(link))
               {
                   message.reply(links.get(names.indexOf(link)));
               }
               else
                   message.reply("I cant seem to find that reaction " + link + ". Are you sure you typed it correctly?");
           }
        }
        else
        {
            message.reply("Looks like there hasn't been any reactions added yet!");
        }
    }
    private void load() 
    {
        FileInputStream fileInput = null;
        
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XML linkXML = new XML(fileInput, "reactions");
        
        for(XML link : linkXML.children("link"))
        {
            names.add(link.content());
            links.add(link.string("http"));
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(Reaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
