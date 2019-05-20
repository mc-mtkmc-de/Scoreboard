package de.scoreboard.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.scoreboard.main.Scoreboards;

public class ScoreboardListener implements Listener{
	
	int sched;
	
	private final long SCOREBOARD_DELAY = 20*20;
	
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
		

		objectiv.getScore("§c§lName:").setScore(8);
		objectiv.getScore("§b§l" + player.getName()).setScore(7);
		objectiv.getScore("§c§l").setScore(6);
		objectiv.getScore("§c§lSpieler:").setScore(5);
		objectiv.getScore("§c§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers()).setScore(4);
		objectiv.getScore("").setScore(3);
		objectiv.getScore("§c§lRang:").setScore(2);
		if(player.hasPermission("Prefix.Owner")) {
			objectiv.getScore("§4§lOwner").setScore(1);
		} else if(player.hasPermission("Prefix.Admin")) {
			objectiv.getScore("§c§lAdmin").setScore(1);
		} else if(player.hasPermission("Prefix.Developer")) {
			objectiv.getScore("§b§lDeveloper").setScore(1);
		} else if(player.hasPermission("Prefix.DI/TSAdmin")) {
			objectiv.getScore("§6§lDI/TSAdmin").setScore(1);
		} else if(player.hasPermission("Prefix.MasterBuilder")) {
			objectiv.getScore("§1§lMBuilder").setScore(1);
		} else if(player.hasPermission("Prefix.Builder")) {
			objectiv.getScore("§a§lBuilder").setScore(1);
		} else if(player.hasPermission("Prefix.DevAzubi")) {
			objectiv.getScore("§d§lDevAzubi").setScore(1);
		} else if(player.hasPermission("Prefix.BuildAzubi")) {
			objectiv.getScore("§d§lBuildAzubi").setScore(1);
		} else if(player.hasPermission("Prefix.Moderator")) {
			objectiv.getScore("§3§lModerator").setScore(1);
		} else if(player.hasPermission("Prefix.Supporter")) {
			objectiv.getScore("§2§lSupporter").setScore(1);
		} else if(player.hasPermission("Prefix.Freund")) {
			objectiv.getScore("§c§lFreund").setScore(1);
		} else if(player.hasPermission("Prefix.Youtuber+")) {
			objectiv.getScore("§5§lYoutuber§4§l+").setScore(1);
		} else if(player.hasPermission("Prefix.Champ")) {
			objectiv.getScore("§e§lChampion").setScore(1);
		} else if(player.hasPermission("Prefix.Drache")) {
			objectiv.getScore("§4§lDrache").setScore(1);
		} else if(player.hasPermission("Prefix.Titan")) {
			objectiv.getScore("§9§lTitan").setScore(1);
		} else if(player.hasPermission("Prefix.Youtuber")) {
			objectiv.getScore("§5§lYoutuber").setScore(1);
		} else if(player.hasPermission("Prefix.Legende")) {
			objectiv.getScore("§c§lLegende").setScore(1);
		} else if(player.hasPermission("Prefix.Ultra")) {
			objectiv.getScore("§b§lUltra").setScore(1);
		} else if(player.hasPermission("Prefix.Premium")) {
			objectiv.getScore("§6§lPremium").setScore(1);
		} else if(player.hasPermission("Prefix.Spieler")) {
			objectiv.getScore("§7§lSpieler").setScore(1);
		}
		
		player.setScoreboard(board);
	}

}
