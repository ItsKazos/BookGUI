package me.itskazos.bookgui.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.itskazos.bookgui.Main;

public class ReloadConfig implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	Player p = (Player) sender;
		Main.plugin.reloadConfig();
		p.sendMessage(ChatColor.GREEN + "Reloaded the configuration!");
		return false;
	}
}
