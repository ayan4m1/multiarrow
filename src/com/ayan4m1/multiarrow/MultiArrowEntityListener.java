package com.ayan4m1.multiarrow;

import java.util.List;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.ayan4m1.multiarrow.arrows.ArrowType;

/**
 * Listens for entity events and raises arrow effect events
 * @author ayan4m1
 */
public class MultiArrowEntityListener extends EntityListener {
	private MultiArrow plugin;

	public MultiArrowEntityListener(MultiArrow instance) {
		plugin = instance;
	}

	public void onProjectileHit(ProjectileHitEvent event) {
		if (!(event.getEntity() instanceof Arrow)) {
			return;
		}

		Arrow arrow = (Arrow)event.getEntity();
		if (!(arrow.getShooter() instanceof Player)) {
			return;
		}

		ArrowType arrowType = plugin.activeArrowType.get(((Player)arrow.getShooter()).getName());
		List<Entity> entities = arrow.getNearbyEntities(1D, 1D, 1D);
		int entCount = entities.size();
		for(Entity ent : entities) {
			if ((ent instanceof Arrow) || (ent instanceof Item) || (ent == arrow.getShooter())) {
				entCount--;
			}
		}
		if (entCount == 0) {
			if (plugin.activeArrowEffect.containsKey(arrow)) {
				if (plugin.chargeFee((Player)arrow.getShooter(), arrowType)) {
					plugin.activeArrowEffect.get(arrow).hitGround(arrow);
					plugin.activeArrowEffect.remove(arrow);
				}
				if (plugin.config.getArrowRemove(arrowType)) {
					arrow.remove();
				}
			}
		}
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event instanceof EntityDamageByProjectileEvent) {
			EntityDamageByProjectileEvent dpe = ((EntityDamageByProjectileEvent)event);
			if (!(dpe.getProjectile() instanceof Arrow)) {
				return;
			}

			Arrow arrow = (Arrow)dpe.getProjectile();
			if (!(arrow.getShooter() instanceof Player)) {
				return;
			}

			ArrowType arrowType = plugin.activeArrowType.get(((Player)arrow.getShooter()).getName());
			if (arrowType != ArrowType.NORMAL) {
				event.setCancelled(true);
			}

			if (plugin.activeArrowEffect.containsKey(arrow)) {
				if (plugin.chargeFee((Player)arrow.getShooter(), arrowType)) {
					plugin.activeArrowEffect.get(arrow).hitEntity(arrow, event.getEntity());
					plugin.activeArrowEffect.remove(arrow);
				}
				if (plugin.config.getArrowRemove(arrowType)) {
					arrow.remove();
				}
			}
		}
	}
}
