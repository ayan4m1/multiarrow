package com.ayan4m1.multiarrow.arrows;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;

public class AnimalArrowEffect implements CustomArrowEffect {
	private CreatureType[] allowedAnimals = {CreatureType.CHICKEN, CreatureType.COW, CreatureType.PIG, CreatureType.SHEEP};
		
	@Override
	public void hitEntity(Arrow arrow, Entity target) {
		arrow.getWorld().spawnCreature(arrow.getLocation(), getRandomAnimal());
	}

	@Override
	public void hitGround(Arrow arrow) {
		arrow.getWorld().spawnCreature(arrow.getLocation(), getRandomAnimal());
	}
	
	private CreatureType getRandomAnimal() {
		Random r = new Random();
		int index = r.nextInt(allowedAnimals.length);
		return allowedAnimals[index];
	}
}
