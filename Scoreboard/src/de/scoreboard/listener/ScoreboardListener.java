package de.scoreboard.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardListener implements Listener{
	
	@EventHandler
	public void handlePlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("");
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objectiv = board.registerNewObjective("abcd", "abcd");
		objectiv.setDisplaySlot(DisplaySlot.SIDEBAR);
		objectiv.setDisplayName("ß6ßlEnderGamesMC.de");
		objectiv.getScore("ßcßlRang:").setScore(9);
		if(player.hasPermission("Prefix.Owner")) {
			objectiv.getScore("ß4ßlOwner").setScore(8);
		} else if(player.hasPermission("Prefix.Spieler")) {
			objectiv.getScore("ßcßlFreund").setScore(8);
		} else if(player.hasPermission("Prefix.Freund")) {
			objectiv.getScore("ßcßlSpieler").setScore(8);
		} else if(player.hasPermission("Prefix.Youtuber")) {
			objectiv.getScore("ß5ßlYoutuber").setScore(8);
		} else if(player.hasPermission("Prefix.MasterBuilder")) {
			objectiv.getScore("ß1ßlMBuilder").setScore(8);
		} else if(player.hasPermission("Prefix.DI/TSAdmin")) {
			objectiv.getScore("ß6ßlDI/TSAdmin").setScore(8);
		} else if(player.hasPermission("Prefix.DevAzubi")) {
			objectiv.getScore("ßdßlDevAzubi").setScore(8);
		} else if(player.hasPermission("Prefix.BuildAzubi")) {
			objectiv.getScore("ßdßlBuildAzubi").setScore(8);
		} else if(player.hasPermission("Prefix.Developer")) {
			objectiv.getScore("ßbßlDeveveloper").setScore(8);
		} else if(player.hasPermission("Prefix.Admin")) {
			objectiv.getScore("ßcßlAdmin").setScore(8);
		} else if(player.hasPermission("Prefix.Builder")) {
			objectiv.getScore("ßaßlBuilder").setScore(8);
		} else if(player.hasPermission("Prefix.Moderator")) {
			objectiv.getScore("ß3ßlModerator").setScore(8);
		} else if(player.hasPermission("Prefix.Supporter")) {
			objectiv.getScore("ß2ßlSupporter").setScore(8);
		} else if(player.hasPermission("Prefix.Youtuber+")) {
			objectiv.getScore("ß5ßlYoutuberß4ßl+").setScore(8);
		} else if(player.hasPermission("Prefix.Legende")) {
			objectiv.getScore("ßcßlLegende").setScore(8);
		} else if(player.hasPermission("Prefix.Titan")) {
			objectiv.getScore("ß9ßlTitan").setScore(8);
		} else if(player.hasPermission("Prefix.Drache")) {
			objectiv.getScore("ß4ßlDrache").setScore(8);
		} else if(player.hasPermission("Prefix.Champ")) {
			objectiv.getScore("ßeßlChampion").setScore(8);
		} else if(player.hasPermission("Prefix.Ultra")) {
			objectiv.getScore("ßbßlUltra").setScore(8);
		} else if(player.hasPermission("Prefix.Premium")) {
			objectiv.getScore("ß6ßlPremium").setScore(8);
		}
		objectiv.getScore("").setScore(7);
		objectiv.getScore("ßcßlName:").setScore(6);
		objectiv.getScore("ßbßl" + player.getName()).setScore(5);
		objectiv.getScore("").setScore(4);
		objectiv.getScore("ßcßlSpieler:").setScore(3);
		objectiv.getScore("ßcßl" + player.getServer().getMaxPlayers()).setScore(2);
		objectiv.getScore("ßaViel Spaﬂ auf dem Server!").setScore(1);
		player.setScoreboard(board);
	}
}
