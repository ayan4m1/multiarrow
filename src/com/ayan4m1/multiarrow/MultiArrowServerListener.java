package com.ayan4m1.multiarrow;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.iConomy.iConomy;

public class MultiArrowServerListener extends ServerListener {
	private MultiArrow plugin;

	public MultiArrowServerListener(MultiArrow instance) {
		this.plugin = instance;
	}

	@Override
	public void onPluginDisable(PluginDisableEvent event) {
		if (plugin.iconomy != null) {
			if (event.getPlugin().getDescription().getName().equals("iConomy")) {
				plugin.iconomy = null;
				plugin.log.info("MultiArrow unhooked from iConomy.");
			}
		}
	}

	@Override
	public void onPluginEnable(PluginEnableEvent event) {
		if (plugin.iconomy == null) {
			Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");

			if (iConomy != null) {
				if (iConomy.isEnabled()	&& iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
					plugin.iconomy = (iConomy)iConomy;
					plugin.log.info("MultiArrow hooked into iConomy.");
				}
			}
		}
	}
}
