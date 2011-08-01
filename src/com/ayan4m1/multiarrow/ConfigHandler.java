package com.ayan4m1.multiarrow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.*;

import com.ayan4m1.multiarrow.arrows.ArrowType;


public class ConfigHandler {
	private MultiArrow plugin;
	private LinkedHashMap<String, LinkedHashMap<String, Integer>> data;
	private final String defaultConfigFile = "requirements:\nremove-arrow:";

	public int getRequiredTypeId(ArrowType type) {
		LinkedHashMap<String, Integer> requirements = data.get("requirements");
		String typeName = type.toString().toLowerCase();
		if (requirements.containsKey(typeName)) {
			return requirements.get(typeName);
		} else return 0;
	}

	private boolean createDataDirectory() {
	    File file = this.plugin.getDataFolder();
	    if (!file.isDirectory()){
	        if (!file.mkdirs()) {
	            return false;
	        }
	    }
	    return true;
	}

	public ConfigHandler(MultiArrow instance) {
		this.plugin = instance;
		if (this.createDataDirectory()) {
			Yaml yaml = new Yaml();
			File configFile = new File(this.plugin.getDataFolder().getAbsolutePath() + File.separator + "config.yml");
			try {
				if (!configFile.exists()) {
					this.plugin.log.info("MultiArrow created new config.yml");
					configFile.createNewFile();
					if (configFile.canWrite()) {
						FileOutputStream fo = new FileOutputStream(configFile);
						fo.write(defaultConfigFile.getBytes());
						fo.flush();
						fo.close();
					}
				}
				FileInputStream fs = new FileInputStream(configFile);
				this.data = (LinkedHashMap<String, LinkedHashMap<String, Integer>>)yaml.load(fs);
				if (this.data != null) {
					this.plugin.log.info("Loaded "  + this.data.get("requirements").size() + " requirement nodes");
				} else {
					this.plugin.log.warning("Could not load config.yml");
				}
			} catch (IOException e) {
				this.plugin.log.warning("IOException reading MultiArrow/config.yml + (" + e.getMessage() + ")");
			}
		} else {
			this.plugin.log.warning("Could not create plugin directory for MultiArrow!");
			this.plugin.log.info("MultiArrow continuing with no config...");
		}
	}
}
