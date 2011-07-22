package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class FireArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		target.setFireTicks(80);
	}

	@Override
	public void hitGround(Arrow arrow, Block target) {
		Block fireBlock = target.getRelative(BlockFace.UP);
		if (fireBlock != null && fireBlock.getType() == Material.AIR) {
			fireBlock.setType(Material.FIRE);
		}
	}

}
