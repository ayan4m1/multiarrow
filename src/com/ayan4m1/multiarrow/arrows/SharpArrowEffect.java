package com.ayan4m1.multiarrow.arrows;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class SharpArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		if (target instanceof LivingEntity) {
			LivingEntity liveTarget = (LivingEntity)target;
			liveTarget.damage(4, arrow);
		} else {
			arrow.remove();
		}
	}
	
	@Override
	public void hitGround(Arrow arrow, Block target) {
		arrow.remove();
	}
}
