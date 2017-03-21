package com.regis.regisdebot;

import com.google.common.util.concurrent.FutureCallback;
import com.regis.regisdebot.user.MyUser;
import com.regis.regisdebot.user.RemoveUser;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.*;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;
import de.btobastian.javacord.listener.server.ServerMemberRemoveListener;
import java.util.ArrayList;

public class RegisDeBot
{
    public DiscordAPI api;
    MyUser myUser;
    RemoveUser removeUser;
    public ArrayList<Server> shutups = new ArrayList();
    
    public RegisDeBot()
    {
        boolean testBot = true;
        String token = null;
        
        if(testBot)
            token = "MjkyNDE2MjY1NzExMTI0NDgx.C63tbA.xCb7PZR5LSXMZNtT-mwBMGZr2Gg";
        else
            token = "MjkwMTk1MTA4NjgxMzUxMTc5.C6XZQw.gsYSWnqLjHx3nvrfRjyGOG8n5PM";
        
        api = Javacord.getApi(token, true);
        // connect
        api.connect(new FutureCallback<DiscordAPI>() {
            @Override
            public void onSuccess(DiscordAPI api) {
                
                System.out.println("The Bot has started.");
                
                //start message
                for(Server server : api.getServers())
                    for(Channel channel : server.getChannels())
                        channel.sendMessage("I'm awake!");
                
                //check if any new users joined while offline
                for(Server server : api.getServers())
                    for(User user : server.getMembers())
                        myUser = new MyUser(user, server);
                
                
                /**
                 * Listeners
                 */
                
                //new message listener
                api.registerListener(new MessageCreateListener() {
                    @Override
                    public void onMessageCreate(DiscordAPI api, Message message) {
                        ParseMessage parseMessage = new ParseMessage(message, api);
                        parseMessage = null;
                    }
                });
                
                //new member listener
                api.registerListener(new ServerMemberAddListener() {
                    @Override
                    public void onServerMemberAdd(DiscordAPI api, User user, Server server)
                    {
                        String message = "Welcome " + user.getMentionTag() + " to " + server.getName() + "! Type ~help to view all the commands!";
                        server.getChannels().iterator().next().sendMessage(message);

                        myUser = new MyUser(user, server);
                    }
                });
                
                //member leave listener
                api.registerListener( new ServerMemberRemoveListener() {
                    @Override
                    public void onServerMemberRemove(DiscordAPI api, User user, Server server)
                    {
                        removeUser = new RemoveUser(user, server);
                    }
                });
                
                
                /**
                 * Add to XP reserve
                 */
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}