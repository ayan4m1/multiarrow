package com.ayan4m1.multiarrow.arrows;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public interface CustomArrowEffect {
	public abstract void hitEntity(Arrow arrow, Entity target);
	public abstract void hitGround(Arrow arrow, Block target);
}
