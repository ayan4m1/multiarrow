package com.ayan4m1.multiarrow;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.ayan4m1.multiarrow.arrows.*;
import com.iConomy.iConomy;
import com.iConomy.system.Holdings;

/**
 * MultiArrow for Bukkit
 * @author ayan4m1
 */
public class MultiArrow extends JavaPlugin {
    private final MultiArrowPlayerListener playerListener = new MultiArrowPlayerListener(this);
    private final MultiArrowEntityListener entityListener = new MultiArrowEntityListener(this);
    private final MultiArrowServerListener serverListener = new MultiArrowServerListener(this);

    public Logger log;
    public HashMap<String, ArrowType> activeArrowType;
    public HashMap<Arrow, CustomArrowEffect> activeArrowEffect;
    public ConfigHandler config;
    public iConomy iconomy;

	public MultiArrow() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<String, ArrowType>();
		this.activeArrowEffect = new HashMap<Arrow, CustomArrowEffect>();
	}

	public boolean chargeFee(Player player, ArrowType type) {
		Double arrowFee = config.getArrowFee(type);
		if (this.iconomy != null && player.hasPermission("multiarrow.free-fees") && arrowFee > 0D) {
			try {
				if (iConomy.hasAccount(player.getName())) {
					Holdings balance = iConomy.getAccount(player.getName()).getHoldings();
					if (balance.hasEnough(arrowFee)) {
						balance.subtract(arrowFee);
						//player.sendMessage("Balance is now " + iConomy.format(balance.balance()) + "");
					} else {
						player.sendMessage("You need " + iConomy.format(arrowFee) + ", but only have " + iConomy.format(balance.balance()));
						return false;
					}
				} else {
					player.sendMessage("Couldn't find your iConomy holdings, cannot pay fee of " + iConomy.format(arrowFee));
					return false;
				}
			} catch (Exception e) {
				this.log.warning("Exception when trying to charge " + player.getName() + " " + iConomy.format(arrowFee));
			}
			return true;
		} else return true;
	}

	public void onEnable() {
		this.config = new ConfigHandler(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		pm.registerEvent(Type.PROJECTILE_HIT, entityListener, Priority.Normal, this);
		pm.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Low, this);

		pm.registerEvent(Type.PLUGIN_ENABLE, serverListener, Priority.Monitor, this);
		pm.registerEvent(Type.PLUGIN_DISABLE, serverListener, Priority.Monitor, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " shutting down.");
	}
}

