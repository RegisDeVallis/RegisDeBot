package com.regis.regisdebot.command.link;

import com.regis.regisdebot.server.rank.Rank;
import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
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

public class RemoveReaction 
{
    Server server;
    String name;
    File file;
    ArrayList<String> names = new ArrayList();
    ArrayList<String> links = new ArrayList();
    
    public RemoveReaction(Message message)
    {
        server = message.getChannelReceiver().getServer();
        file = new File("data/" + server.getId() + "/reactions.xml");

        if(message.getContent().trim().indexOf(" ") == -1)
            message.reply("Usage: `~removereaction NAME`");
        else if(new Rank(message.getChannelReceiver().getServer(), message.getAuthor()).get() >= 3)
        {
            if(file.exists())
            {
                load();
                
                String text = message.getContent().substring(message.getContent().indexOf(" ") + 1);
                if(names.contains(text))
                {
                    links.remove(names.indexOf(text));
                    names.remove(text);
                    save();
                }
                message.reply("Reaction " + text + " removed!");
            }
            else
                message.reply("Looks like there are no reactions to remove!");
        }
        else
            message.reply("Looks like you don't have permission to do that.");
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
            Logger.getLogger(RemoveReaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void save() 
    {
        Element xml = new Element("reactions");
        
        for(int i = 0; i < names.size(); i++)
        {
            Element link = new Element("link");
            link.appendChild(names.get(i));
            Attribute http = new Attribute("http", links.get(i));
            link.addAttribute(http);
            xml.appendChild(link);
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
