package de.scoreboard.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.scoreboard.listener.ScoreboardListener;

public class Scoreboards extends JavaPlugin {
	
	private static Scoreboards plugin;
	
	public void onEnable() {
		plugin = this;
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new ScoreboardListener(), this);
		
	}
	
	public static Scoreboards getPlugin() {
		return plugin;
	}
	
	
	
}
