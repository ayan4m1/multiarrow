package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class DrillArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		this.drillDown(target.getWorld().getBlockAt(target.getLocation()), 1);
		arrow.remove();
	}

	@Override
	public void hitGround(Arrow arrow) {
		this.drillDown(arrow.getWorld().getBlockAt(arrow.getLocation()), 2);
		arrow.remove();
	}
	
	private void drillDown(Block startBlock, int numBlocks) {
		Block entityBlock = startBlock;
		for(int i = 0; i < 2; i++) {
			//Don't increase the count for snow
			if (entityBlock.getType() == Material.SNOW) {
				i--;
			}
			entityBlock.setType(Material.AIR);
			entityBlock = entityBlock.getRelative(BlockFace.DOWN);
		}
	}

}
