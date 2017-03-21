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

public class Idea 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> ideas = new ArrayList();
    ArrayList<String> servers = new ArrayList();
    File file;
    
    public Idea(Message message)
    {
        file = new File("data/bot/ideas.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
            load();
        
        if(message.getContent().trim().indexOf(" ") == -1)
            message.reply("Usage: `~idea IDEA`");
        else
        {
            String idea = message.getContent().substring(message.getContent().indexOf(" ") + 1);
            names.add(message.getAuthor().getName());
            servers.add(message.getChannelReceiver().getServer().getName());
            ideas.add(idea);
            
            save();
            message.reply("Idea added.");
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
    
    private void save()
    {
        Element xml = new Element("ideas");
        
        for(int i = 0; i < ideas.size(); i++)
        {
            Element idea = new Element("idea");
            idea.appendChild(ideas.get(i));
            idea.addAttribute(new Attribute("name", names.get(i)));
            idea.addAttribute(new Attribute("server", servers.get(i)));
            xml.appendChild(idea);
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
}
