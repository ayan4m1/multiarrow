package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class SharpArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		if (target instanceof LivingEntity) {
			LivingEntity liveTarget = (LivingEntity)target;
			liveTarget.damage(Math.min(0, liveTarget.getHealth() - 4), arrow);
		} else {
			arrow.remove();
		}
	}
	
	@Override
	public void hitGround(Arrow arrow) {
		arrow.getWorld().getBlockAt(arrow.getLocation()).getRelative(BlockFace.DOWN).setType(Material.AIR);
		arrow.remove();
	}
}
