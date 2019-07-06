package de.score.main;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.economy.Economy;
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
		ScoreboardScore s02 = new ScoreboardScore(board, obj, "§6Server:");
		ScoreboardScore s03 = new ScoreboardScore(board, obj, "§b" + Bukkit.getServerName());
		ScoreboardScore s04 = new ScoreboardScore(board, obj, "§4");
		ScoreboardScore s05 = new ScoreboardScore(board, obj, "§6Name:");
		ScoreboardScore s06 = new ScoreboardScore(board, obj, p.getDisplayName());
		ScoreboardScore s07 = new ScoreboardScore(board, obj, "§1");
		ScoreboardScore s08 = new ScoreboardScore(board, obj, "§6Kontostand");
		ScoreboardScore s09 = new ScoreboardScore(board, obj, "§c§l" + playerBalance(p, null));
		ScoreboardScore s10 = new ScoreboardScore(board, obj, "§f");
		ScoreboardScore s11 = new ScoreboardScore(board, obj, "§6Online:");
		ScoreboardScore s12 = new ScoreboardScore(board, obj, "§d§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers());
		
		s01.setScore(12);
		s02.setScore(11);
		s03.setScore(10);
		s04.setScore(9);
		s05.setScore(8);
		s06.setScore(7);
		s07.setScore(6);
		s08.setScore(5);
		s09.setScore(4);
		s10.setScore(3);
		s11.setScore(2);
		s12.setScore(1);
		
		PacketPlayOutScoreboardScore ps01 = new PacketPlayOutScoreboardScore(s01);
		PacketPlayOutScoreboardScore ps02 = new PacketPlayOutScoreboardScore(s02);
		PacketPlayOutScoreboardScore ps03 = new PacketPlayOutScoreboardScore(s03);
		PacketPlayOutScoreboardScore ps04 = new PacketPlayOutScoreboardScore(s04);
		PacketPlayOutScoreboardScore ps05 = new PacketPlayOutScoreboardScore(s05);
		PacketPlayOutScoreboardScore ps06 = new PacketPlayOutScoreboardScore(s06);
		PacketPlayOutScoreboardScore ps07 = new PacketPlayOutScoreboardScore(s07);
		PacketPlayOutScoreboardScore ps08 = new PacketPlayOutScoreboardScore(s08);
		PacketPlayOutScoreboardScore ps09 = new PacketPlayOutScoreboardScore(s09);
		PacketPlayOutScoreboardScore ps10 = new PacketPlayOutScoreboardScore(s10);
		PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
		PacketPlayOutScoreboardScore ps12 = new PacketPlayOutScoreboardScore(s12);
		
		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		sendPacket(p, ps01);
		sendPacket(p, ps02);
		sendPacket(p, ps03);
		sendPacket(p, ps04);
		sendPacket(p, ps05);
		sendPacket(p, ps06);
		sendPacket(p, ps07);
		sendPacket(p, ps08);
		sendPacket(p, ps09);
		sendPacket(p, ps10);
		sendPacket(p, ps11);
		sendPacket(p, ps12);
		
	}
	
	public static void updateScoreboard(Player p) {
		Scoreboard board = new Scoreboard();
		ScoreboardObjective obj = board.registerObjective("§6EnderGamesMC", IScoreboardCriteria.b);
		
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		obj.setDisplayName("§6EnderGamesMc.de");
		
		ScoreboardScore s01 = new ScoreboardScore(board, obj, "§4");
		ScoreboardScore s02 = new ScoreboardScore(board, obj, "§6Server:");
		ScoreboardScore s03 = new ScoreboardScore(board, obj, "§b" + Bukkit.getServerName());
		ScoreboardScore s04 = new ScoreboardScore(board, obj, "§4");
		ScoreboardScore s05 = new ScoreboardScore(board, obj, "§6Name:");
		ScoreboardScore s06 = new ScoreboardScore(board, obj, p.getDisplayName());
		ScoreboardScore s07 = new ScoreboardScore(board, obj, "§1");
		ScoreboardScore s08 = new ScoreboardScore(board, obj, "§6Kontostand");
		ScoreboardScore s09 = new ScoreboardScore(board, obj, "§c§l" + playerBalance(p, null));
		ScoreboardScore s10 = new ScoreboardScore(board, obj, "§f");
		ScoreboardScore s11 = new ScoreboardScore(board, obj, "§6Online:");
		ScoreboardScore s12 = new ScoreboardScore(board, obj, "§d§l" + Bukkit.getOnlinePlayers().size() + "§b§l/§c§l" + Bukkit.getMaxPlayers());
		
		s01.setScore(12);
		s02.setScore(11);
		s03.setScore(10);
		s04.setScore(9);
		s05.setScore(8);
		s06.setScore(7);
		s07.setScore(6);
		s08.setScore(5);
		s09.setScore(4);
		s10.setScore(3);
		s11.setScore(2);
		s12.setScore(1);
		
		PacketPlayOutScoreboardScore ps01 = new PacketPlayOutScoreboardScore(s01);
		PacketPlayOutScoreboardScore ps02 = new PacketPlayOutScoreboardScore(s02);
		PacketPlayOutScoreboardScore ps03 = new PacketPlayOutScoreboardScore(s03);
		PacketPlayOutScoreboardScore ps04 = new PacketPlayOutScoreboardScore(s04);
		PacketPlayOutScoreboardScore ps05 = new PacketPlayOutScoreboardScore(s05);
		PacketPlayOutScoreboardScore ps06 = new PacketPlayOutScoreboardScore(s06);
		PacketPlayOutScoreboardScore ps07 = new PacketPlayOutScoreboardScore(s07);
		PacketPlayOutScoreboardScore ps08 = new PacketPlayOutScoreboardScore(s08);
		PacketPlayOutScoreboardScore ps09 = new PacketPlayOutScoreboardScore(s09);
		PacketPlayOutScoreboardScore ps10 = new PacketPlayOutScoreboardScore(s10);
		PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
		PacketPlayOutScoreboardScore ps12 = new PacketPlayOutScoreboardScore(s12);
		
		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		sendPacket(p, ps01);
		sendPacket(p, ps02);
		sendPacket(p, ps03);
		sendPacket(p, ps04);
		sendPacket(p, ps05);
		sendPacket(p, ps06);
		sendPacket(p, ps07);
		sendPacket(p, ps08);
		sendPacket(p, ps09);
		sendPacket(p, ps10);
		sendPacket(p, ps11);
		sendPacket(p, ps12);
		
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
