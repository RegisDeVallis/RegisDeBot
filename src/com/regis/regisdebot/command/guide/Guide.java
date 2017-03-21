package com.regis.regisdebot.command.guide;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Guide 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> guides = new ArrayList();
    File file;

    public Guide(Message message)
    {
        file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/guides.xml");
        
        if(file.exists())
        {
            if(message.getContent().trim().indexOf(" ") == -1)
                message.reply("Usage: `~guide \"NAME\"`");
            else
            {
                load();
                
                String name = message.getContent().substring(message.getContent().indexOf(" ") + 1).toLowerCase();
                if(names.contains(name))
                    message.reply("**" + name + ":** \n" + guides.get(names.indexOf(name)));
                else
                    message.reply("I can't seem to find a guide named " + name + ". Are you sure you typed it in correctly?");
            }
        }
        else
            message.reply("Looks there hasn't been any guides added yet!");
    }
    
    private void load()
    {
        FileInputStream fileInput = null;
        
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XML guideXML = new XML(fileInput, "guides");
        
        for(XML guide : guideXML.children("guide"))
        {
            names.add(guide.string("name"));
            guides.add(guide.content());
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(Guide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
