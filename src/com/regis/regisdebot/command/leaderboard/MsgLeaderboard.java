package com.regis.regisdebot.command.leaderboard;

import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.util.XML;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MsgLeaderboard 
{
    ArrayList<String> ids = new ArrayList();
    ArrayList<Long> msgs = new ArrayList();
    Message message;
    Server server;
    File file;
    
    public MsgLeaderboard(Message message)
    {
        this.server = message.getChannelReceiver().getServer();
        this.message = message;
        
        file = new File("data/" + server.getId() + "/msgLeaderboard.xml");
        file.getParentFile().mkdirs();
        
        if(file.exists())
        {
            load();
            
            if(message.getContent().trim().indexOf(" ") == -1)
                getLeaderboard(10);
            else
            {
                String text = message.getContent().trim();
                text = text.substring(text.indexOf(" ") + 1);
                text = text.substring(text.indexOf(" ") + 1);

                int amount = Integer.parseInt(text);

                if(amount > 0 && amount <= 100)
                    getLeaderboard(amount);
                else
                    message.reply("That amount isn't supported.");
            }
        }
    }
    
    private void getLeaderboard(int amount)
    {
        if(amount > ids.size())
            amount = ids.size();
        
        String text = "``` Messages Leaderboard - Top " + amount + "\n \n";
        
        for(int i = 0; i < amount; i++)
            text += "   " + (i + 1) + ". " + server.getMemberById(ids.get(i)).getName() + " with " + msgs.get(i) + " messages. \n";
        
        text += "```";
        
        message.reply(text);
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
            Logger.getLogger(MsgLeaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
