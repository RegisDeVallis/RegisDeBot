package com.regis.regisdebot.command.settings;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class ServerSettings 
{
    Server server;
    File file;
    
    //settings
    String rules = "";
    
    public ServerSettings(Server server)
    {
        this.server = server;
        file = new File("data/" + server.getId() + "/settings.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
            load();
        else
            save();
    }
    
    private void save()
    {
        Element xml = new Element("settings");
        
        for(int i = 0; i < 3; i++)
        {
            Element data = new Element("setting");
            Attribute name = new Attribute("name", "filler");
            
            switch(i)
            {
                case 0:
                    name.setValue("name");
                    data.appendChild(server.getName());
                    break;
                
                case 1:
                    name.setValue("id");
                    data.appendChild(server.getId());
                    break;
                
                case 2:
                    name.setValue("rules");
                    data.appendChild(rules);
                    break;
            }
            
            data.addAttribute(name);
            xml.appendChild(data);
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
            Logger.getLogger(ServerSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XML settingsXML = new XML(fileInput, "settings");
        
        for(XML child : settingsXML.children("setting"))
        {
            if(child.string("name").equals("rules"))
                rules = child.content();
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRules()
    {
        return rules;
    }
    
    public void setRules(String r)
    {
        rules = r;
        save();
    }
}
