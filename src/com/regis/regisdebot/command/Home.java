package com.regis.regisdebot.command;

import com.sun.management.OperatingSystemMXBean;
import de.btobastian.javacord.entities.message.Message;

public class Home 
{
    //private long totalMem = OperatingSystemMXBean.getTotalPhysicalMemorySize();
    
    public Home(Message message)
    {
        String text = "*My Home* ```";
        
        String OS = System.getProperty("os.name");
        //long totalMem = OperatingSystemMXBean.getTotalPhysicalMemorySize();
        //double cpuLoad = OperatingSystemMXBean.getSystemLoadAverage();
        
        text += "OS: " + OS + "\n";
        
        text += "```";
        
        message.reply(text);
    }
}
