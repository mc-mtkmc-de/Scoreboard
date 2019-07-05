package de.score.main;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.IScoreboardCriteria;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_12_R1.Scoreboard;
import net.minecraft.server.v1_12_R1.ScoreboardObjective;
import net.minecraft.server.v1_12_R1.ScoreboardScore;

public class Score extends JavaPlugin implements Listener {
	
	private static Score plugin;
	public static Score instance;
	
	public void onEnable() {
		
		plugin = this;
		instance = this;
		
		startScheduler();
		
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		sendScoreboard(e.getPlayer());

	}
	
	public static void startScheduler() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player on : Bukkit.getOnlinePlayers()) {
					updateScoreboard(on);
				}
				
			}
		}.runTaskTimer(instance, 20, 20);
	}
	
	public void sendScoreboard(Player p) {
		Scoreboard board = new Scoreboard();
		ScoreboardObjective obj = board.registerObjective("§6EnderGamesMC", IScoreboardCriteria.b);
		
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		obj.setDisplayName("§6EnderGamesMc.de");
		
		ScoreboardScore s01 = new ScoreboardScore(board, obj, "§4");
		ScoreboardScore s02 = new ScoreboardScore(board, obj, "§6Name");
		ScoreboardScore s03 = new ScoreboardScore(board, obj, p.getDisplayName());
		ScoreboardScore s04 = new ScoreboardScore(board, obj, "§1");
		ScoreboardScore s05 = new ScoreboardScore(board, obj, "§6Online");
		ScoreboardScore s06 = new ScoreboardScore(board, obj, "§d§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers());
		
		s01.setScore(6);
		s02.setScore(5);
		s03.setScore(4);
		s04.setScore(3);
		s05.setScore(2);
		s06.setScore(1);
		
		PacketPlayOutScoreboardScore ps01 = new PacketPlayOutScoreboardScore(s01);
		PacketPlayOutScoreboardScore ps02 = new PacketPlayOutScoreboardScore(s02);
		PacketPlayOutScoreboardScore ps03 = new PacketPlayOutScoreboardScore(s03);
		PacketPlayOutScoreboardScore ps04 = new PacketPlayOutScoreboardScore(s04);
		PacketPlayOutScoreboardScore ps05 = new PacketPlayOutScoreboardScore(s05);
		PacketPlayOutScoreboardScore ps06 = new PacketPlayOutScoreboardScore(s06);
		
		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		sendPacket(p, ps01);
		sendPacket(p, ps02);
		sendPacket(p, ps03);
		sendPacket(p, ps04);
		sendPacket(p, ps05);
		sendPacket(p, ps06);
		
	}
	
	public static void updateScoreboard(Player p) {
		Scoreboard board = new Scoreboard();
		ScoreboardObjective obj = board.registerObjective("§6EnderGamesMC", IScoreboardCriteria.b);
		
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		obj.setDisplayName("§6EnderGamesMc.de");
		
		ScoreboardScore s01 = new ScoreboardScore(board, obj, "§4");
		ScoreboardScore s02 = new ScoreboardScore(board, obj, "§6Name");
		ScoreboardScore s03 = new ScoreboardScore(board, obj, p.getDisplayName());
		ScoreboardScore s04 = new ScoreboardScore(board, obj, "§1");
		ScoreboardScore s05 = new ScoreboardScore(board, obj, "§6Online");
		ScoreboardScore s06 = new ScoreboardScore(board, obj, "§d§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers());
		
		s01.setScore(6);
		s02.setScore(5);
		s03.setScore(4);
		s04.setScore(3);
		s05.setScore(2);
		s06.setScore(1);
		
		PacketPlayOutScoreboardScore ps01 = new PacketPlayOutScoreboardScore(s01);
		PacketPlayOutScoreboardScore ps02 = new PacketPlayOutScoreboardScore(s02);
		PacketPlayOutScoreboardScore ps03 = new PacketPlayOutScoreboardScore(s03);
		PacketPlayOutScoreboardScore ps04 = new PacketPlayOutScoreboardScore(s04);
		PacketPlayOutScoreboardScore ps05 = new PacketPlayOutScoreboardScore(s05);
		PacketPlayOutScoreboardScore ps06 = new PacketPlayOutScoreboardScore(s06);
		
		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		sendPacket(p, ps01);
		sendPacket(p, ps02);
		sendPacket(p, ps03);
		sendPacket(p, ps04);
		sendPacket(p, ps05);
		sendPacket(p, ps06);
		
	}
	
	public static void sendPacket(Player p, Packet<?> packet) {
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static Score getPlugin() {
		return plugin;
	}
	
	public static Score getInstance() {
		return instance;
	}

}
