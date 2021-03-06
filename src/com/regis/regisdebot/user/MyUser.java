package com.regis.regisdebot.user;

import com.regis.regisdebot.Main;
import com.regis.regisdebot.command.leaderboard.CheckUserLeaderboard;
import com.regis.regisdebot.command.leaderboard.CheckUserMsgLeaderboard;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class MyUser 
{
    //util stuff
    DiscordAPI api;
    private User user;
    private File file;
    private Date date;
    private Server server;
    private CheckUserLeaderboard checkUserLeaderboard;
    private CheckUserMsgLeaderboard checkUserMsgLeaderboard;
    
    //user stuff
    private long xp = 0;
    private long xpReserve = 0;
    private String dateJoined;
    private long messages = 0;
    private int rank = 0;
    
    private ArrayList<String> partNames = new ArrayList();
    private ArrayList<String> partLists = new ArrayList();
    
    public MyUser(User user, Server server)
    {
        api = Main.Bot.api;
        this.user = user;
        this.server = server;
        
        file = new File("data/" + server.getId() + "/users/" + user.getId() + ".xml");
        file.getParentFile().mkdirs();
        date = new Date();
        
        if(file.exists())
            load();
        else //new user
        {
            dateJoined = date.toString();
            addXP(0);
            save();
            System.out.println("Added " + user.getName() + " with ID " + user.getId() + " in server " + server.getName());
        }
            
    }
    
    public void save()
    {
        //have fun reading this
        Element xml = new Element("user");
        
        for(int i = 0; i < 8; i++)
        {
            Element data = new Element("data");
            Attribute name = new Attribute("name", "filler");
            
            switch(i)
            {
                case 0:
                    name.setValue("name");
                    data.appendChild(user.getName());
                    break;
                    
                case 1:
                    name.setValue("id");
                    data.appendChild(user.getId());
                    break;
                    
                case 2:
                    name.setValue("joined");
                    data.appendChild(date.toString());
                    break;
                    
                case 3:
                     name.setValue("xp");
                    data.appendChild(Long.toString(xp));
                    break;
                    
                case 4:
                    name.setValue("xpreserve");
                    data.appendChild(Long.toString(xpReserve));
                    break;
                    
                case 5:
                    name.setValue("messages");
                    data.appendChild(Long.toString(messages));
                    break;
                    
                case 6:
                    name.setValue("rank");
                    data.appendChild(Integer.toString(rank));
                    break;
                    
                case 7:
                    name.setValue("parts");
                    for(int x = 0; x < partNames.size(); x++)
                    {
                        Element elem = new Element("partList");
                        elem.addAttribute(new Attribute("name", partNames.get(x)));
                        elem.appendChild(partLists.get(x));
                        data.appendChild(elem);
                    }
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
    
    public void load()
    {
        FileInputStream fileInput = null;
        
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        XML userXML = new XML(fileInput, "user");
        
        for(XML child : userXML.children("data"))
        {
            if(child.string("name").equals("joined"))
                dateJoined = child.content();
            if(child.string("name").equals("xp"))
                xp = Long.parseLong(child.content());
            if(child.string("name").equals("xpreserve"))
                xpReserve = Long.parseLong(child.content());
            if(child.string("name").equals("messages"))
                messages = Long.parseLong(child.content());
            if(child.string("name").equals("rank"))
                rank = Integer.parseInt(child.content());
            if(child.string("name").equals("parts"))
                for(XML partList : child.children("partList"))
                {
                    partLists.add(partList.content());
                    partNames.add(partList.string("name"));
                }
        }
        
        try {
            fileInput.close();
        } catch (IOException ex) {
            Logger.getLogger(MyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getXP()
    {
        return xp;
    }
    
    public void addXP(long add)
    {
        xp += add;
        checkUserLeaderboard = new CheckUserLeaderboard(this);
        save();
    }
    
    public void removeXP(long remove)
    {
        xp -= remove;
        if(xp < 0)
            xp = 0;
        checkUserLeaderboard = new CheckUserLeaderboard(this);
        save();
    }
    
    public long getXPReserve()
    {
        return xpReserve;
    }
    
    public void addXPReserve(long add)
    {
        xpReserve += add;
        save();
    }
    
    public void removeXPReserve(long remove)
    {
        xpReserve -= remove;
        save();
    }
    
    public void addMessage(int add)
    {
        messages += add;
        checkUserMsgLeaderboard = new CheckUserMsgLeaderboard(this);
        save();
    }
    
    public long getMessages() 
    {
        return messages;
    } 
    
    
    public String getDateJoined() 
    {
        return dateJoined;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public Server getServer()
    {
        return server;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public void setRank(int r)
    {
        rank = r;
        save();
    }
    
    public void addParts(String name, String parts)
    {
        partNames.add(name.toLowerCase());
        partLists.add(parts);
        save();
    }
    
    public void removeParts(String name)
    {
        name = name.toLowerCase();
        if(partNames.contains(name))
        {
            int index = partNames.indexOf(name);
            partNames.remove(index);
            partLists.remove(index);
        }
    }
    
    public ArrayList<String> getPartNames()
    {
        return partNames;
    }
    
    public ArrayList<String> getPartLists()
    {
        return partLists;
    }
}
