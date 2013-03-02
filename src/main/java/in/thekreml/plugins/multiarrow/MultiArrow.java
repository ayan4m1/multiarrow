package in.thekreml.plugins.multiarrow;

import in.thekreml.plugins.multiarrow.ConfigHandler;
import in.thekreml.plugins.multiarrow.arrows.ArrowType;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

//import com.iConomy.iConomy;

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
    //public iConomy iconomy;

	public MultiArrow() {
		this.log = Logger.getLogger("Minecraft");
		this.activeArrowType = new HashMap<String, ArrowType>();
	}

	public String toProperCase(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	public void onEnable() {
		this.config = new ConfigHandler(this);

		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(playerListener, this);
		pm.registerEvents(entityListener, this);
		pm.registerEvents(serverListener, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " shutting down.");
	}
}

