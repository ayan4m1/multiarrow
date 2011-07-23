package com.ayan4m1.multiarrow.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class HeavyArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		if (target instanceof LivingEntity) {
			LivingEntity liveTarget = (LivingEntity)target;
			liveTarget.setVelocity(liveTarget.getVelocity().zero());
		} else {
			arrow.remove();
		}
	}

	@Override
	public void hitGround(Arrow arrow) {
		arrow.remove();
	}
}
