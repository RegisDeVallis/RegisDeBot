package com.regis.regisdebot.command.settings;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.*;

public class ServerSettings 
{
    Server server;
    File file;
    
    //settings
    String rules = "";
    
    //other stuff
    public ArrayList<String> mutes = new ArrayList();
    
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
        
        for(int i = 0; i < 4; i++)
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
                    
                case 3:
                    name.setValue("mutes");
                    for(String mute : mutes)
                    {
                        Element elem = new Element("mute");
                        elem.appendChild(mute);
                        data.appendChild(elem);
                    }
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
            if(child.string("name").equals("mutes"))
                for(XML mute : child.children("mute"))
                    mutes.add(mute.content());
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
    
    public void addMute(User user)
    {
        mutes.add(user.getId());
        save();
    }
    
    public void removeMute(User user)
    {
        if(mutes.contains(user.getId()))
        {
            mutes.remove(user.getId());
            save();
        }
    }
}
