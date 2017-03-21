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

public class Link 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> links = new ArrayList();
    File file;
    
    public Link(Message message) 
    {
        file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/links.xml");
        
        if(file.exists())
        {
           if(message.getContent().trim().indexOf(" ") == -1)
               message.reply("Usage: `~link \"NAME\"`");
           else
           {
                load();

                String name = message.getContent().substring(message.getContent().indexOf(" ") + 1).toLowerCase();
                if(names.contains(name))
                {
                    message.reply("*" + name + ":* \n" + links.get(names.indexOf(name)));
                }
                else
                    message.reply("I cant seem to find the link " + name + ". Are you sure you typed it correctly?");
           }
        }
        else
        {
            message.reply("Looks like there hasn't been any links added yet!");
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
        
        XML linkXML = new XML(fileInput, "links");
        
        for(XML link : linkXML.children("link"))
        {
            names.add(link.content());
            links.add(link.string("http"));
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(Link.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
