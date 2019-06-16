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
	
	Scoreboard board;
	
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
		setPrefix(event.getPlayer());

	
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Scoreboards.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					setPrefix(player);
					playerBalance(player, null);
					Bukkit.getOnlinePlayers().size();
					
				}
				
			}

		}, 0, SCOREBOARD_DELAY);
		
	}

	private void updateScoreboard(Player player) {
			
		board = Bukkit.getScoreboardManager().getNewScoreboard();
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
		
		board.registerNewTeam("00000Owner");
		board.registerNewTeam("00001Admin");
		board.registerNewTeam("00002Dev");
		board.registerNewTeam("00003MBuilder");
		board.registerNewTeam("00004Builder");
		board.registerNewTeam("00005Azubi");
		board.registerNewTeam("00006Mod");
		board.registerNewTeam("00007Sup");
		board.registerNewTeam("00008Freund");
		board.registerNewTeam("00009YT+");
		board.registerNewTeam("00010Champ");
		board.registerNewTeam("00011Drache");
		board.registerNewTeam("00012Titan");
		board.registerNewTeam("00013YT");
		board.registerNewTeam("00014Legende");
		board.registerNewTeam("00015Ultra");
		board.registerNewTeam("00016Premium");
		board.registerNewTeam("00017Spieler");
		
		board.getTeam("00000Owner").setPrefix("§a[Owner]");
		board.getTeam("00001Admin").setPrefix("§c[Admin]");
		board.getTeam("00002Dev").setPrefix("§b[Dev]");
		board.getTeam("00003MBuilder").setPrefix("§2[MBuilder]");
		board.getTeam("00004Builder").setPrefix("§a[Builder]");
		board.getTeam("00005Azubi").setPrefix("§d[Azubi]");
		board.getTeam("00006Mod").setPrefix("§3[Mod]");
		board.getTeam("00007Sup").setPrefix("§2[Sup]");
		board.getTeam("00008Freund").setPrefix("§c[Freund]");
		board.getTeam("00009YT+").setPrefix("§5[YT§4+§5]");
		board.getTeam("00010Champ").setPrefix("§e[champ]");
		board.getTeam("00011Drache").setPrefix("§4[Drache]");
		board.getTeam("00012Titan").setPrefix("§9[Titan]");
		board.getTeam("00013YT").setPrefix("§5[YT]");
		board.getTeam("00014Legende").setPrefix("§c[Legende]");
		board.getTeam("00015Ultra").setPrefix("§b[Ultra]");
		board.getTeam("00016Premium").setPrefix("§6[Premium]");
		board.getTeam("00017Spieler").setPrefix("§7[Spieler]");
				
	}

	private void setPrefix(Player p) {
	
		String team = "";
	
		if(p.hasPermission("tab.owner")) {
		team = "00000Owner";
		} else if(p.hasPermission("tab.admin")) {
		team = "00001Admin";
		} else if(p.hasPermission("tab.dev")) {
		team = "00002Dev";
		} else if(p.hasPermission("tab.mbuilder")) {
		team = "00003MBuilder";
		} else if(p.hasPermission("tab.builder")) {
		team = "00004Builder";
		} else if(p.hasPermission("tab.azubi")) {
		team = "00005Azubi";
		} else if(p.hasPermission("tab.mod")) {
		team = "00006Mod";
		} else if(p.hasPermission("tab.sup")) {
		team = "00007Sup";
		} else if(p.hasPermission("tab.freund")) {
		team = "00008Freund";
		} else if(p.hasPermission("tab.yt+")) {
		team = "00009YT+";
		} else if(p.hasPermission("tab.champ")) {
		team = "00010Champ";
		}  else if(p.hasPermission("tab.drache")) {
		team = "00011Drache";
		} else if(p.hasPermission("tab.titan")) {
		team = "00012Titan";
		} else if(p.hasPermission("tab.yt")) {
		team = "00013YT";
		} else if(p.hasPermission("tab.legende")) {
		team = "00014Legende";
		} else if(p.hasPermission("tab.ultra")) {
		team = "00015Ultra";
		} else if(p.hasPermission("tab.premium")) {
		team = "00016Premium";
		} else {
		team = "00017Spieler";
	}
	
	board.getTeam(team).addPlayer(p);
	p.setDisplayName(board.getTeam(team).getPrefix() + p.getName());
	
	for(Player all : Bukkit.getOnlinePlayers()) {
		all.setScoreboard(board);
		}
	
	}

	

	
	public static Scoreboards getPlugin() {
		return plugin;
	}

	public static Scoreboards getInstance() {
		return instance;
	}
	
}
