package com.ayan4m1.multiarrow;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.ayan4m1.multiarrow.arrows.ArrowType;
import com.iConomy.iConomy;

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
    public ConfigHandler config;
    public iConomy iconomy;

	public MultiArrow() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<String, ArrowType>();
	}

	public String toProperCase(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
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

