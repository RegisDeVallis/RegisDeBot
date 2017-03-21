package com.regis.regisdebot.command.guide;

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

public class RemoveGuide 
{
    ArrayList<String> names = new ArrayList();
    ArrayList<String> guides = new ArrayList();
    File file;
    
    public RemoveGuide(Message message)
    {
        file = new File("data/" + message.getChannelReceiver().getServer().getId() + "/guides.xml");

        if(message.getContent().trim().indexOf(" ") == -1)
            message.reply("Usage `~removeguide NAME`");
        else
        {
            if(file.exists())
            {
                load();
                
                String name = message.getContent().substring(message.getContent().indexOf(" ") + 1);
                if(names.contains(name))
                {
                    guides.remove(names.indexOf(name));
                    names.remove(name);
                    save();
                }
                message.reply("Link " + name + " removed!");
            }
            else
                message.reply("Looks like there are no guides to remove!");
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
        
        XML guideXML = new XML(fileInput, "guides");
        
        for(XML guide : guideXML.children("guide"))
        {
            names.add(guide.string("name"));
            guides.add(guide.content());
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(RemoveGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save()
    {
        Element xml = new Element("guides");
        
        for(int i = 0; i < names.size(); i++)
        {
            Element guide = new Element("guide");
            guide.appendChild(guides.get(i));
            Attribute name = new Attribute("name", names.get(i));
            guide.addAttribute(name);
            xml.appendChild(guide);
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
