package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class LightArrowEffect implements CustomArrowEffect {
	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		Block targetBlock = target.getLocation().getBlock();
		
		if (!targetBlock.isEmpty()) {
			targetBlock = targetBlock.getRelative(BlockFace.UP);
		}
		
		targetBlock.setType(Material.TORCH);
		arrow.remove();
	}

	@Override
	public void hitGround(Arrow arrow) {
		Block targetBlock = arrow.getWorld().getBlockAt(arrow.getLocation());

		if (targetBlock.getType() == Material.SNOW) {
			targetBlock.setType(Material.AIR);
		}

		if (!targetBlock.isEmpty()) {
			targetBlock = targetBlock.getRelative(BlockFace.UP);
		}

		targetBlock.setType(Material.TORCH);
		arrow.remove();
	}
}
