package com.regis.regisdebot.botutil;

import de.btobastian.javacord.entities.message.Message;

public class Subreddit 
{
    public Subreddit(Message message)
    {
        String msg = message.getContent();
        String link = msg.substring(msg.indexOf("r/"));
        
        if(link.contains(" "))
            link = link.substring(0, link.indexOf(" "));
        
        System.out.println("Link: " + link);
        
        if(link.startsWith("r/"))
            link = link.substring(2);
        else if(link.startsWith("/r/"))
            link = link.substring(3);
        
        if(!link.contains("/") && !message.getAuthor().isBot()) //make sure its just the subreddit and not a link to reddit
            message.reply("https://reddit.com/r/" + link); 
        
            
        
    }
}
