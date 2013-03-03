package in.thekreml.plugins.multiarrow;

import in.thekreml.plugins.multiarrow.ConfigHandler;
import in.thekreml.plugins.multiarrow.arrows.ArrowTypes;

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

    public ArrowTypes arrowTypes = new ArrowTypes();
    public HashMap<String, Integer> activeArrow;
    public ConfigHandler config;
    //public iConomy iconomy;

	public MultiArrow() {
		this.activeArrow = new HashMap<String, Integer>();
	}

	public void onEnable() {
		this.config = new ConfigHandler(this);

		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(playerListener, this);
		pm.registerEvents(entityListener, this);
		pm.registerEvents(serverListener, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " shutting down.");
	}

	private static final Logger logger = Logger.getLogger("Minecraft");
}
