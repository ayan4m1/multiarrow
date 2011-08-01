package com.ayan4m1.multiarrow.arrows;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class TeleportArrowEffect implements CustomArrowEffect {

	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		Random r = new Random();
		Location newLoc = target.getLocation().add(r.nextInt(10) - 5, r.nextInt(10) - 5, r.nextInt(10) - 5);
		while (!newLoc.getBlock().isEmpty() && newLoc.getY() < 127) {
			newLoc.add(0, 1, 0);
		}
		target.teleport(newLoc);
	}

	@Override
	public void hitGround(Arrow arrow) {
		Random r = new Random();
		Location newLoc = arrow.getLocation().add(r.nextInt(5) - 2, r.nextInt(5) - 2, r.nextInt(5) - 2);
		while (!newLoc.getBlock().isEmpty() && newLoc.getY() < 127) {
			newLoc.add(0, 1, 0);
		}
		arrow.getShooter().teleport(newLoc);
	}
}
