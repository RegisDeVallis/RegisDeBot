package com.regis.regisdebot;

import com.regis.regisdebot.util.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main 
{
    public static RegisDeBot Bot;
    private static ConsoleCommands commands;
    private static Console console;
    
    public static void main(String[] args)
    {
        //build the console
        System.out.println("Starting.");
        console = new Console();
        console.setTitle("RegisDeBot");
        console.setSize(700, 500);
        //console.build();
                
        start();   
    }
    
    private static void start()
    {
       Bot = new RegisDeBot();
       
       //commands = new ConsoleCommands(console, Bot);
    }
    
    public static void restart()
    {
        System.out.println("Restarting");
        Bot.api.disconnect();
        
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Bot.api = null;
        Bot = null;
        start();
    }

}
