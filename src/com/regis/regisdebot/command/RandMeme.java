package com.regis.regisdebot.command;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import java.io.File;
import java.util.ArrayList;

public class RandMeme 
{
    public RandMeme(Message message)
    {
        Channel channel = message.getChannelReceiver();
        File folder = new File("data/meme");
        
        ArrayList<File> memes = new ArrayList();
        
        //create the array list of files
        for(File file : folder.listFiles())
            memes.add(file);
        
        //get the random file
        int random = (int) (Math.random() * memes.size());
        
        File randFile = memes.get(random);
        
        channel.sendFile(randFile);
    }
}
