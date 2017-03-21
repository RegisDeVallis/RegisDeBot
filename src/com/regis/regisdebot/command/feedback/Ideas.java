package com.regis.regisdebot.command.feedback;

import com.regis.regisdebot.command.guide.AddGuide;
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

public class Ideas 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> ideas = new ArrayList();
    ArrayList<String> servers = new ArrayList();
    File file;
    
    public Ideas(Message message)
    {
        if(message.getAuthor().getId().equals("111992351378984960"))
        {
            file = new File("data/bot/ideas.xml");
            
            if(file.exists())
            {
                load();
                
                String text = "**Ideas** ```";
                for(int i = 0; i < ideas.size(); i++)
                    text += "  - " + names.get(i) + " / " + servers.get(i) + ": " + ideas.get(i);
                text += "```";
                
                message.reply(text);
            }
            else
                message.reply("Looks like there haven't been any ideas added yet!");
        }
        else
            message.reply("You don't have permission to see this.");
    }
    
    private void load()
    {
        FileInputStream fileInput = null;
        
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XML ideaXML = new XML(fileInput, "ideas");
        
        for(XML idea : ideaXML.children("idea"))
        {
            names.add(idea.string("name"));
            servers.add(idea.string("server"));
            ideas.add(idea.content());
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(AddGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
