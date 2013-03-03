package in.thekreml.plugins.multiarrow.arrows;

import in.thekreml.plugins.multiarrow.ArrowEffect;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_4_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftEntity;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosiveArrowEffect implements ArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		this.triggerExplosion(arrow, 2.0F);
	}

	public void onGroundHitEvent(Arrow arrow) {
		this.triggerExplosion(arrow, 2.5F);
	}

	private void triggerExplosion(Arrow arrow, Float radius) {
		ExplosionPrimeEvent event = new ExplosionPrimeEvent(arrow, radius, false);
		arrow.getServer().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			Location loc = arrow.getLocation();
			((CraftWorld)arrow.getWorld()).getHandle().createExplosion(((CraftEntity)arrow).getHandle(), loc.getX(), loc.getY(), loc.getZ(), radius, false, true);
		}
	}
}
