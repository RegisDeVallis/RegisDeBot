package com.regis.regisdebot.command.link;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.*;

public class AddLink 
{
    String link;
    String name;
    File file;
    ArrayList<String> names = new ArrayList();
    ArrayList<String> links = new ArrayList();
    
    public AddLink(Message message)
    {        
        if(message.getContent().trim().indexOf(" ") == -1)
            message.reply("Usage: `~addlink \"NAME\" LINK`");
        else
        {
            //get the name
            name = message.getContent().substring(message.getContent().indexOf("\"") + 1);
            name = name.substring(0, name.indexOf("\"")).toLowerCase();
            System.out.println("Link name: " + name);
            
            //get the link
            link = message.getContent().substring(message.getContent().indexOf("\"") + 1);
            link = link.substring(link.indexOf("\""));
            link = link.substring(link.indexOf(" ") + 1);
            System.out.println("Link link: " + link);
            
            file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/links.xml");
            file.getParentFile().mkdirs();
            
            if(file.exists())
                load();
            
            names.add(name);
            links.add(link);
            save();
            
            System.out.println("Added link " + name + " which links to " + link);
            message.reply("Added link " + name + " which links to " + link);
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
            Logger.getLogger(AddLink.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void save() 
    {
        Element xml = new Element("links");
        
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
