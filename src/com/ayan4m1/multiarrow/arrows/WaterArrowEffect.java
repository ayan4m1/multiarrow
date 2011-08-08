package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class WaterArrowEffect implements CustomArrowEffect {
	public void hitEntity(Arrow arrow, Entity target) {
		this.setToWater(arrow, 2);
	}

	public void hitGround(Arrow arrow) {
		this.setToWater(arrow, 1);
	}
	
	private void setToWater(Arrow arrow, int radius) {
		Vector centerLoc = arrow.getLocation().toVector();
		for (int x = -1; x < 1; x++) {
			for (int y = -1; y < 1; y++) {
				for (int z = -1; z < 1; z++) {
					Block block = arrow.getWorld().getBlockAt(new Location(arrow.getWorld(), centerLoc.getX() + x, centerLoc.getY() + y, centerLoc.getZ() + z));
					block.setType(Material.WATER);
				}
			}
		}
	}
}
