package in.thekreml.plugins.multiarrow.arrows;

import in.thekreml.plugins.multiarrow.ArrowEffect;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Animal implements ArrowEffect {
	private EntityType[] allowedAnimals = {EntityType.CHICKEN, EntityType.COW, EntityType.PIG, EntityType.SHEEP};

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		arrow.getWorld().spawnEntity(arrow.getLocation(), getRandomAnimal());
	}

	public void onGroundHitEvent(Arrow arrow) {
		arrow.getWorld().spawnEntity(arrow.getLocation(), getRandomAnimal());
	}
	
	private EntityType getRandomAnimal() {
		Random r = new Random();
		int index = r.nextInt(allowedAnimals.length);
		return allowedAnimals[index];
	}
}
