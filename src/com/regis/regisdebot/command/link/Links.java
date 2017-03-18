package com.regis.regisdebot.command.link;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Links 
{
    File file;
    ArrayList<String> names = new ArrayList();
    ArrayList<String> links = new ArrayList();
    
    public Links(Message message)
    {
        file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/links.xml");
        
        if(file.exists())
        {
            load();
            
            String text = "``` Links \n";
            
            for(String name : names)
                text += " - " + name + "\n";
            text = "```";
            
            message.reply(text);
        }
        else
            message.reply("Looks like you havent added any links yet!");
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
    }
}
