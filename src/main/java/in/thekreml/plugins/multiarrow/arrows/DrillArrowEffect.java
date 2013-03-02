package in.thekreml.plugins.multiarrow.arrows;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class DrillArrowEffect implements ArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		this.drillDown(target.getWorld().getBlockAt(target.getLocation()));
	}

	public void onGroundHitEvent(Arrow arrow) {
		this.drillDown(arrow.getWorld().getBlockAt(arrow.getLocation()));
	}
	
	private void drillDown(Block startBlock) {
		for(int y = 0; y >= -2; y--) {
			//Don't increase the count for snow
			if (startBlock.getType() == Material.SNOW) {
				startBlock.setType(Material.AIR);
				y++;
			}
			for (int x = -1; x < 1; x++) {
				for (int z = -1; z < 1; z++) {
					Vector offset = startBlock.getLocation().toVector().add(new Vector(x, y, z));
					Block locBlock = startBlock.getWorld().getBlockAt(new Location(startBlock.getWorld(), offset.getX(), offset.getY(), offset.getZ()));

					locBlock.setType(Material.AIR);
				}
			}
		}
	}
}
