package in.thekreml.plugins.multiarrow.arrows;

import in.thekreml.plugins.multiarrow.ArrowEffect;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class Torch implements ArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		target.setFireTicks(100);
	}

	public void onGroundHitEvent(Arrow arrow) {
		Block targetBlock = arrow.getWorld().getBlockAt(arrow.getLocation());

		if (targetBlock.getType() == Material.SNOW) {
			targetBlock.setType(Material.AIR);
		}

		while (!targetBlock.isEmpty() && targetBlock.getY() < 127) {
			targetBlock = targetBlock.getRelative(BlockFace.UP);
		}

		if (targetBlock.isEmpty()) {
			targetBlock.setType(Material.TORCH);
		}
	}
}
