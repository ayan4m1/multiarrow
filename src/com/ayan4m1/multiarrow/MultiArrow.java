package com.ayan4m1.multiarrow;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Arrow;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.ayan4m1.multiarrow.arrows.*;

/**
 * MultiArrow for Bukkit
 *
 * @author ayan4m1
 */
public class MultiArrow extends JavaPlugin {
    private final MultiArrowPlayerListener playerListener = new MultiArrowPlayerListener(this);
    private final MultiArrowEntityListener entityListener = new MultiArrowEntityListener(this);
    private final MultiArrowBlockHitDetector blockListener = new MultiArrowBlockHitDetector(this);
    private int blockHitDetectorThreadId;

    public Logger log;
    public HashMap<String, ArrowType> activeArrowType;
    public HashMap<Arrow, CustomArrowEffect> activeArrowEffect;
    public ConfigHandler config;

	public MultiArrow() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<String, ArrowType>();
		this.activeArrowEffect = new HashMap<Arrow, CustomArrowEffect>();
	}

	public void onEnable() {
		this.config = new ConfigHandler(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Low, this);
		pm.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Low, this);
		pm.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Low, this);

		this.blockHitDetectorThreadId = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, blockListener, 20L, 10L);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " shutting down.");

		this.getServer().getScheduler().cancelTask(this.blockHitDetectorThreadId);
		this.getServer().getScheduler().cancelTasks(this);
	}
}

