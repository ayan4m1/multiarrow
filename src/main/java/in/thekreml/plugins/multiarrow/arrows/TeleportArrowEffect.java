package in.thekreml.plugins.multiarrow.arrows;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class TeleportArrowEffect implements ArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Random r = new Random();
		Location newLoc = target.getLocation().add(r.nextInt(10) - 5, r.nextInt(10) - 5, r.nextInt(10) - 5);
		while (!newLoc.getBlock().isEmpty() && newLoc.getY() < 127) {
			newLoc.add(0, 1, 0);
		}
		target.teleport(newLoc);
	}

	public void onGroundHitEvent(Arrow arrow) {
		Random r = new Random();
		Location newLoc = arrow.getLocation().add(r.nextInt(5) - 2, r.nextInt(5) - 2, r.nextInt(5) - 2);
		while (!newLoc.getBlock().isEmpty() && newLoc.getY() < 127) {
			newLoc.add(0, 1, 0);
		}
		newLoc.setPitch(0F);
		arrow.getShooter().teleport(newLoc);
	}
}
