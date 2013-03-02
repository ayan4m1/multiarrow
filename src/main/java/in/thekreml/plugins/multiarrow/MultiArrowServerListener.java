package in.thekreml.plugins.multiarrow;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

//import com.iConomy.iConomy;

/**
 * Handles external plugin integration
 * @author ayan4m1
 */
public class MultiArrowServerListener implements Listener {
	private MultiArrow plugin;

	public MultiArrowServerListener(MultiArrow instance) {
		this.plugin = instance;
	}

	@EventHandler(priority=EventPriority.MONITOR)
	public void onPluginDisable(PluginDisableEvent event) {
		/*if (plugin.iconomy != null) {
			if (event.getPlugin().getDescription().getName().equals("iConomy")) {
				plugin.iconomy = null;
				plugin.log.info("MultiArrow unhooked from iConomy.");
			}
		}*/
	}

	@EventHandler(priority=EventPriority.MONITOR)
	public void onPluginEnable(PluginEnableEvent event) {
		/*if (plugin.iconomy == null) {
			Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");

			if (iConomy != null) {
				if (iConomy.isEnabled()	&& iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
					plugin.iconomy = (iConomy)iConomy;
					plugin.log.info("MultiArrow hooked into iConomy.");
				}
			}
		}*/
	}
}
