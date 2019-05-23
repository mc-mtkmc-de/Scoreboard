package de.scoreboard.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.milkbowl.vault.economy.Economy;

public class Scoreboards extends JavaPlugin implements Listener {
	
	private static Scoreboards plugin;
	
	public static Scoreboards instance;
	
	int sched;
	
	private final long SCOREBOARD_DELAY = 20*20;
	
	public void onEnable() {
		instance = this;
		
		this.getServer().getPluginManager().registerEvents(this, this);
		setupEconomy();
		
	}
	
	public static Economy econ = null;
	
	public static String playerBalance(Player player, Double amount) {
		double balance = econ.getBalance(player);
		String formart = econ.format(balance);
		
		return formart;
	}
	
	private boolean setupEconomy() {
		if(getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
			if(rsp == null) {
				return false;
			}
			
			econ = (Economy)rsp.getProvider();
			return econ != null;
	}
	

	@EventHandler
	public void handlePlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		this.updateScoreboard(player);

	
		if(!Bukkit.getScheduler().isCurrentlyRunning(sched)) {
			sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Scoreboards.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					updateScoreboard(player);
					playerBalance(player, null);
					
				}
				
			}

		}, 0, SCOREBOARD_DELAY);
	}
		
	}

	private void updateScoreboard(Player player) {
			
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objectiv = board.registerNewObjective("abcd", "abcd");
		
		objectiv.setDisplaySlot(DisplaySlot.SIDEBAR);
		objectiv.setDisplayName("§6§lEnderGamesMC.de");
		
		objectiv.getScore("§1§l ").setScore(12);
		objectiv.getScore("§6§lName:").setScore(11);
		objectiv.getScore("§b§l" + player.getName()).setScore(10);
		objectiv.getScore("").setScore(9);
		objectiv.getScore("§6§lRang:").setScore(8);
		if(player.hasPermission("Prefix.Owner")) {
			objectiv.getScore("§4§lOwner").setScore(7);
		} else if(player.hasPermission("Prefix.Admin")) {
			objectiv.getScore("§c§lAdmin").setScore(7);
		} else if(player.hasPermission("Prefix.Developer")) {
			objectiv.getScore("§b§lDeveloper").setScore(7);
		} else if(player.hasPermission("Prefix.DI/TSAdmin")) {
			objectiv.getScore("§6§lDI/TSAdmin").setScore(7);
		} else if(player.hasPermission("Prefix.MasterBuilder")) {
			objectiv.getScore("§1§lMBuilder").setScore(7);
		} else if(player.hasPermission("Prefix.Builder")) {
			objectiv.getScore("§a§lBuilder").setScore(7);
		} else if(player.hasPermission("Prefix.DevAzubi")) {
			objectiv.getScore("§d§lDevAzubi").setScore(7);
		} else if(player.hasPermission("Prefix.BuildAzubi")) {
			objectiv.getScore("§d§lBuildAzubi").setScore(7);
		} else if(player.hasPermission("Prefix.Moderator")) {
			objectiv.getScore("§3§lModerator").setScore(7);
		} else if(player.hasPermission("Prefix.Supporter")) {
			objectiv.getScore("§2§lSupporter").setScore(7);
		} else if(player.hasPermission("Prefix.Freund")) {
			objectiv.getScore("§c§lFreund").setScore(7);
		} else if(player.hasPermission("Prefix.Youtuber+")) {
			objectiv.getScore("§5§lYoutuber§4§l+").setScore(7);
		} else if(player.hasPermission("Prefix.Champ")) {
			objectiv.getScore("§e§lChampion").setScore(7);
		} else if(player.hasPermission("Prefix.Drache")) {
			objectiv.getScore("§4§lDrache").setScore(7);
		} else if(player.hasPermission("Prefix.Titan")) {
			objectiv.getScore("§9§lTitan").setScore(7);
		} else if(player.hasPermission("Prefix.Youtuber")) {
			objectiv.getScore("§5§lYoutuber").setScore(7);
		} else if(player.hasPermission("Prefix.Legende")) {
			objectiv.getScore("§c§lLegende").setScore(7);
		} else if(player.hasPermission("Prefix.Ultra")) {
			objectiv.getScore("§b§lUltra").setScore(7);
		} else if(player.hasPermission("Prefix.Premium")) {
			objectiv.getScore("§6§lPremium").setScore(7);
		} else if(player.hasPermission("Prefix.Spieler")) {
			objectiv.getScore("§7§lSpieler").setScore(7);
		}
		objectiv.getScore("§5§l").setScore(6);
		objectiv.getScore("§6§lKontostand").setScore(5);
		objectiv.getScore("§c§l" + playerBalance(player, null)).setScore(4);
		objectiv.getScore("§1§l").setScore(3);
		objectiv.getScore("§6§lSpieler:").setScore(2);
		objectiv.getScore("§d§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers()).setScore(1);
		
		
		
		player.setScoreboard(board);
	}

	
	public static Scoreboards getPlugin() {
		return plugin;
	}

	public static Scoreboards getInstance() {
		return instance;
	}
	
}
