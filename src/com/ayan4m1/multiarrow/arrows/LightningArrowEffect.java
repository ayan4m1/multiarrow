package com.ayan4m1.multiarrow.arrows;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class LightningArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		arrow.getWorld().strikeLightning(arrow.getLocation());
	}

	@Override
	public void hitGround(Arrow arrow, Block target) {
		arrow.getWorld().strikeLightning(target.getLocation());
	}

}
