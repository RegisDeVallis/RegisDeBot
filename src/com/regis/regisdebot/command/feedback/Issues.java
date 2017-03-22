package com.regis.regisdebot.command.feedback;

import com.regis.regisdebot.command.guide.AddGuide;
import com.regis.regisdebot.server.rank.Rank;
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

public class Issues 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> issues = new ArrayList();
    ArrayList<String> servers = new ArrayList();
    File file;
    
    public Issues(Message message)
    {
        if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() == 11)
        {
            file = new File("data/bot/issues.xml");
            
            if(file.exists())
            {
                load();
                
                String text = "**Issues** ```";
                for(int i = 0; i < issues.size(); i++)
                    text += "  - " + names.get(i) + " / " + servers.get(i) + ": " + issues.get(i);
                text += "```";
                
                message.reply(text);
            }
            else
                message.reply("Looks like there haven't been any issues added yet!");
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
        
        XML issueXML = new XML(fileInput, "issues");
        
        for(XML issue : issueXML.children("issue"))
        {
            names.add(issue.string("name"));
            servers.add(issue.string("server"));
            issues.add(issue.content());
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(AddGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
