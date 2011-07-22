package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class HeavyArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		if (target instanceof LivingEntity) {
			LivingEntity liveTarget = (LivingEntity)target;
			liveTarget.setVelocity(new Vector(0.0, 0.0, 0.0));
		} else {
			arrow.remove();
		}
	}

	@Override
	public void hitGround(Arrow arrow, Block target) {
		if (!target.isEmpty()) {
			target.setType(Material.AIR);
		}
	}

}
