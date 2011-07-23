package com.ayan4m1.multiarrow.arrows;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class FireArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		target.setFireTicks(100);
	}

	@Override
	public void hitGround(Arrow arrow) {
		arrow.getWorld().getBlockAt(arrow.getLocation()).setType(Material.FIRE);
	}
}
