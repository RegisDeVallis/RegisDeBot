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

public class Guides 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> guides = new ArrayList();
    File file;
    
    public Guides(Message message)
    {
        file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/guides.xml");
        
        if(file.exists())
        {
            load();
            
            String text = "``` Guides \n";
            
            for(String name : names)
                text += " - " + name + "\n";
            text += "```";
            
            message.reply(text);
        }
        else
            message.reply("Looks like there haven't been any guides added yet!");
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
            Logger.getLogger(Guides.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
