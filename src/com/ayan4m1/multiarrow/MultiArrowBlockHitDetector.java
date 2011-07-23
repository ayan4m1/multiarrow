package com.ayan4m1.multiarrow;

import java.util.HashMap;

import org.bukkit.entity.Arrow;
import org.bukkit.Location;

public class MultiArrowBlockHitDetector implements Runnable {
	private MultiArrow plugin;
	private HashMap<Arrow, Location> locations;
	
	public MultiArrowBlockHitDetector(MultiArrow instance) {
		plugin = instance;
		locations = new HashMap<Arrow, Location>();
	}
	
	@Override
	public void run() {
		if (plugin.activeArrowEffect.size() > 0) {
			Object[] activeArrows = plugin.activeArrowEffect.keySet().toArray();
			for(int i = 0; i < activeArrows.length; i++) {
				Arrow arrow = (Arrow)activeArrows[i];
	            if(locations.containsKey(arrow)) {
	                Location loc = (Location)locations.get(arrow);
	                Location loca = arrow.getLocation();
	                if (loc.getBlockX() == loca.getBlockX() && loc.getBlockY() == loca.getBlockY() && loc.getBlockZ() == loca.getBlockZ()) {
	                    plugin.activeArrowEffect.get(arrow).hitGround(arrow);
	                    plugin.activeArrowEffect.remove(arrow);
	                    locations.remove(arrow);
	                } else {
	                    locations.put(arrow, arrow.getLocation());
	                }
	            } else {
	                locations.put(arrow, arrow.getLocation());
	            }
	        }
		}
	}
}
