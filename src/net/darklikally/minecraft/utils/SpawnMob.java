// $Id$
/*
 * LyTreeHelper
 * Copyright (C) 2011 DarkLiKally <http://darklikally.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.darklikally.minecraft.utils;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

import net.minecraft.server.WorldServer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;

/**
 *
 * @author DarkLiKally
 */
public class SpawnMob {
    @SuppressWarnings("unused")
    public SpawnMob(Server server, Player player, Location loc, String type) {

    	if (type.equalsIgnoreCase("PigZombie")) {
    		type = "PigZombie";
    	}else {
    		type = this.capitalCase(type);
    	}
    	CreatureType ct = CreatureType.fromName(type);
        if (ct == null) {
            player.sendMessage(ChatColor.DARK_RED + "Invalid mob type. Possible mob types are:");
            player.sendMessage(ChatColor.DARK_RED + "Squid, Creeper, Zombie, Skeleton, Ghast, Slime, Pig, PigZombie, Spider, Sheep, Chicken, Wolf, Cow"); 
            return;
        }
        WorldServer worldServer = ((org.bukkit.craftbukkit.CraftWorld)player.getWorld()).getHandle();
        CraftEntity spawnedMob = null;
        
        int blockTypeId = player.getWorld().getBlockTypeIdAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());

        while (!(blockTypeId == 0 || blockTypeId == 8 || blockTypeId == 9)) {
            loc.setY(loc.getY() + 1);
            blockTypeId = player.getWorld().getBlockTypeIdAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        }
        
        player.getWorld().spawnCreature(loc, ct);
    }
    
    public SpawnMob(Server server, Block spawnblock, String type) {
    	
    	Location loc = spawnblock.getLocation();
    	if (type.equalsIgnoreCase("PigZombie")) {
    		type = "PigZombie";
    	}else {
    		type = this.capitalCase(type);
    	}
    	CreatureType ct = CreatureType.fromName(type);
    	
    	if (ct == null) {
        	System.out.println("Couldn't find the mob type!");
            return;
        }
    	spawnblock.getWorld().spawnCreature(loc, ct);
        
    }

    private String capitalCase(String s)
    {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}