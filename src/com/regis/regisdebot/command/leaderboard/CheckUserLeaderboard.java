package com.regis.regisdebot.command.leaderboard;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
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

public class CheckUserLeaderboard 
{
    ArrayList<String> ids = new ArrayList();
    ArrayList<Long> xps = new ArrayList();
    File file;
    int limit = 100;
    
    public CheckUserLeaderboard(MyUser myUser)
    {
        file = new File("data/" + myUser.getServer().getId() + "/xpLeaderboard.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
            load();
        
        String ID = myUser.getUser().getId();
        Long xp = myUser.getXP();
        
        if(ids.contains(ID)) //if user is already on the leaderboard, remove them
        {
            int index = ids.indexOf(ID);
            ids.remove(index);
            xps.remove(index);
        }
        
        for(int i = 0; i < ids.size(); i++)
        {
            if(xps.get(i) < xp)
            {
                ids.add(i, ID);
                xps.add(i, xp);
                break;
            }
        }
        
        //if its a new file
        if(ids.size() == 0)
        {
            ids.add(0, ID);
            xps.add(0, xp);
        }
        
        //if the user isn't on the leaderboard, add them
        if(!ids.contains(ID))
        {
            ids.add(ID);
            xps.add(xp);
        }
        
        if(ids.size() == limit + 1)
        {
            ids.remove(limit);
            xps.remove(limit);
        }
        
        save();
        System.out.println("Updated xp leaderboard for user " + myUser.getUser().getName());
    }
    
    private void load()
    {
        FileInputStream fileInput = null;
        
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XML leaderboardXML = new XML(fileInput, "leaderboard");
        
        for(XML child : leaderboardXML.children("user"))
        {
            ids.add(child.string("id"));
            xps.add(Long.parseLong(child.content()));
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(CheckUserLeaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save()
    {
        Element xml = new Element("leaderboard");
        
        for(int i = 0; i < ids.size(); i++)
        {
            Element elemUser = new Element("user");
            Attribute attID = new Attribute("id", ids.get(i));
            elemUser.addAttribute(attID);
            elemUser.appendChild(xps.get(i).toString());
            xml.appendChild(elemUser);
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
                serializer.setMaxLength(1024);
                 
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
