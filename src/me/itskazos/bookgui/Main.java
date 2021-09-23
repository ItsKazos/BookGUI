package me.itskazos.bookgui;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.itskazos.bookgui.commands.CommandsBookGui;
import me.itskazos.bookgui.commands.ReloadConfig;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	@Override
	public void onEnable() {
		this.getCommand("book").setExecutor(new CommandsBookGui());
		this.getCommand("reloadconfig").setExecutor(new ReloadConfig());
		createFiles();
		plugin = this;
	}
	public void onDisable() {
		
	}
	
	private File configf;
	private FileConfiguration config;
	
	private void createFiles() {
		configf = new File(getDataFolder(), "config.yml");
		
		if (!configf.exists()) {
			configf.getParentFile().mkdirs();
			saveResource("config.yml", false);
		}
		config = new YamlConfiguration();
		
		try {
			config.load(configf);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
}
