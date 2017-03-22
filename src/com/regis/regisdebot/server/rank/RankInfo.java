package com.regis.regisdebot.server.rank;

import de.btobastian.javacord.entities.message.Message;

public class RankInfo 
{
    public RankInfo(Message message)
    {
        message.reply("The rank system is a system to determine what commands a role has. "
                + "When creating a new role, make sure to set the ranks. "
                + "1 is the lowest rank and 10 being the highest. "
                + "I created the rank system with this in mind. "
                + "10 being admin level, 9 being bot level, 8 being mod level, and 3 being trusted level. "
                + "PM me if you need more help.");
    }
}
