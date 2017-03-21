package com.regis.regisdebot.command.feedback;

import com.regis.regisdebot.command.guide.AddGuide;
import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class Issue 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> issues = new ArrayList();
    ArrayList<String> servers = new ArrayList();
    File file;
    
    public Issue(Message message)
    {
        file = new File("data/bot/issues.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
            load();
        
        if(message.getContent().trim().indexOf(" ") == -1)
            message.reply("Usage: `~issue ISSUE`");
        else
        {
            String issue = message.getContent().substring(message.getContent().indexOf(" ") + 1);
            names.add(message.getAuthor().getName());
            servers.add(message.getChannelReceiver().getServer().getName());
            issues.add(issue);
            
            save();
            message.reply("Issue added.");
        }
    }
    
    private void save()
    {
        Element xml = new Element("issues");
        
        for(int i = 0; i < issues.size(); i++)
        {
            Element issue = new Element("issue");
            issue.appendChild(issues.get(i));
            issue.addAttribute(new Attribute("name", names.get(i)));
            issue.addAttribute(new Attribute("server", servers.get(i)));
            xml.appendChild(issue);
        }
        
        /**
         * Create the document
         */
        Document doc = new Document(xml);
        OutputStream output;
        
        try {
            output = new FileOutputStream(file);
            
            Serializer serializer;
            try {
                serializer = new Serializer(output, "ISO-8859-1");
                serializer.setIndent(4);
                serializer.setMaxLength(2048);
                 
                try {
                    serializer.write(doc);
                } catch (IOException ex) {
                    Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
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
