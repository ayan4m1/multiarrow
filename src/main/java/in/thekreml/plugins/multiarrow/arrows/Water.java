package in.thekreml.plugins.multiarrow.arrows;

import in.thekreml.plugins.multiarrow.TimedArrowEffect;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Water implements TimedArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		this.setToWater(arrow, 1);
	}

	public void onGroundHitEvent(Arrow arrow) {
		this.setToWater(arrow, 1);
	}

	public Runnable getDelayTriggerRunnable(final Arrow arrow) {
		return new Runnable(){
			public void run() {
				Vector centerLoc = arrow.getLocation().toVector();
				for (int x = -2; x <= 2; x++) {
					for (int y = -2; y <= 2; y++) {
						for (int z = -2; z <= 2; z++) {
							Block block = arrow.getWorld().getBlockAt(new Location(arrow.getWorld(), centerLoc.getX() + x, centerLoc.getY() + y, centerLoc.getZ() + z));
							if (block.getTypeId() == 8 || block.getTypeId() == 9) {
								block.setType(Material.AIR);
							}
						}
					}
				}
			}
		};
	}

	public long getDelayTicks() {
		return 40;
	}

	private void setToWater(Arrow arrow, int radius) {
		Vector centerLoc = arrow.getLocation().toVector();
		for (int x = -radius; x < radius; x++) {
			for (int y = 0; y < radius; y++) {
				for (int z = -radius; z < radius; z++) {
					Block block = arrow.getWorld().getBlockAt(new Location(arrow.getWorld(), centerLoc.getX() + x, centerLoc.getY() + y, centerLoc.getZ() + z));
					if (block.isEmpty()) {
						block.setType(Material.STATIONARY_WATER);
					}
				}
			}
		}
	}
}