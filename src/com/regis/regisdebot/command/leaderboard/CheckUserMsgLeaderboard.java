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

public class CheckUserMsgLeaderboard 
{
    ArrayList<String> ids = new ArrayList();
    ArrayList<Long> msgs = new ArrayList();
    File file;
    int limit = 100;
    
    public CheckUserMsgLeaderboard(MyUser myUser)
    {
        file = new File("data/" + myUser.getServer().getId() + "/msgLeaderboard.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
            load();
        
        String ID = myUser.getUser().getId();
        Long msg = myUser.getMessages();
        
        if(ids.contains(ID)) //if user is already on the leaderboard, remove them
        {
            int index = ids.indexOf(ID);
            ids.remove(index);
            msgs.remove(index);
        }
        
        for(int i = 0; i < ids.size(); i++)
        {
            if(msgs.get(i) < msg)
            {
                ids.add(i, ID);
                msgs.add(i, msg);
                break;
            }
        }
        
        //if the user isn't on the leaderboard add them
        if(!ids.contains(ID))
        {
            ids.add(ID);
            msgs.add(msg);
        }
        
        //if its a new file
        if(ids.size() == 0)
        {
            ids.add(0, ID);
            msgs.add(0, msg);
        }
        
        if(ids.size() == limit + 1)
        {
            ids.remove(limit);
            msgs.remove(limit);
        }
        
        save();
        System.out.println("Updated message leaderboard for user " + myUser.getUser().getName());
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
            msgs.add(Long.parseLong(child.content()));
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(CheckUserMsgLeaderboard.class.getName()).log(Level.SEVERE, null, ex);
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
            elemUser.appendChild(msgs.get(i).toString());
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
