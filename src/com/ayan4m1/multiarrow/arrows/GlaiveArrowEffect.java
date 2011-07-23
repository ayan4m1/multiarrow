package com.ayan4m1.multiarrow.arrows;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class GlaiveArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		if (target instanceof LivingEntity) {
			LivingEntity liveEntity = (LivingEntity)target;
			liveEntity.setHealth(Math.min(0, liveEntity.getHealth() - 4));
			arrow.teleport(arrow.getLocation().toVector().add(new Vector(0, 1, 0)).toLocation(arrow.getWorld()));
			LivingEntity nextTarget = this.findNextTarget(arrow);
			arrow.setVelocity(nextTarget.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize());
		}
	}

	@Override
	public void hitGround(Arrow arrow) {
		arrow.teleport(arrow.getLocation().toVector().add(new Vector(0, 1, 0)).toLocation(arrow.getWorld()));
		LivingEntity nextTarget = this.findNextTarget(arrow);
		arrow.setVelocity(nextTarget.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize());
	}
	
	private LivingEntity findNextTarget(Arrow arrow) {
		List<Entity> targetList = arrow.getNearbyEntities(20, 10, 20);
		int i = 0;
		Entity nextTarget = targetList.get(i);
		while (!(nextTarget instanceof LivingEntity)) {
			nextTarget = targetList.get(i++);
		}
		return (LivingEntity)nextTarget;
	}

}
