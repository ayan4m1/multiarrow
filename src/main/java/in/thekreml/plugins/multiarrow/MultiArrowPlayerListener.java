package in.thekreml.plugins.multiarrow;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;

//import com.iConomy.iConomy;

/**
 * Changes arrow types, fires arrows
 * @author ayan4m1
 */
public class MultiArrowPlayerListener implements Listener {
	private final MultiArrow plugin;

	public MultiArrowPlayerListener(MultiArrow instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.activeArrow.containsKey(event.getPlayer().getName())) {
			plugin.activeArrow.remove(event.getPlayer().getName());
		}
	}

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getType() == Material.BOW) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR	|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);

				if (!plugin.activeArrow.containsKey(player.getName())) {
					plugin.activeArrow.put(player.getName(), -1);
				}

				if (canFire(player)) {
					player.launchProjectile(Arrow.class);
				}
			} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				updateArrowIndex(player);
				
				Integer arrowIndex = plugin.activeArrow.get(player.getName());
				String arrowName = plugin.arrowTypes.getName(arrowIndex);
				//Double arrowFee = plugin.config.getArrowFee(arrowType);
				/*if (plugin.iconomy != null && arrowFee > 0D) {
					message += " (" + iConomy.format(arrowFee) + ")";
				}*/
				player.sendMessage(new StringBuilder().append("Selected ").append(arrowName).toString());
			}
		}
	}

	private Boolean canFire(Player player) {
		Integer arrowIndex = plugin.activeArrow.get(player.getName());
		MaterialData arrowMaterial = plugin.config.getReqdMaterialData(plugin.arrowTypes.getName(arrowIndex));

		PlayerInventory inventory = player.getInventory();
		if (!player.hasPermission("multiarrow.free-materials")
				&& !arrowMaterial.getItemType().equals(Material.AIR)) {
			String arrowMaterialName = arrowMaterial.getItemType().toString().toLowerCase().replace('_', ' ');
			if (arrowMaterial.getData() > 0) {
				arrowMaterialName += " (" + ((Byte)arrowMaterial.getData()).toString() + ")";
			}
			if (inventory.contains(arrowMaterial.getItemType())) {
				ItemStack reqdStack = inventory.getItem(inventory.first(arrowMaterial.getItemType()));
				if (reqdStack.getAmount() > 1) {
					reqdStack.setAmount(reqdStack.getAmount() - 1);
				} else {
					inventory.clear(inventory.first(arrowMaterial.getItemType()));
				}
			} else {
				player.sendMessage("You do not have any " + arrowMaterialName);
				return false;
			}
		}

		if (!player.hasPermission("multiarrow.infinite")) {
			if (inventory.contains(Material.ARROW)) {
				ItemStack arrowStack = inventory.getItem(inventory.first(Material.ARROW));
				if (arrowStack.getAmount() > 1) {
					arrowStack.setAmount(arrowStack.getAmount() - 1);
				} else {
					inventory.remove(arrowStack);
				}
			} else {
				player.sendMessage("Out of arrows!");
				return false;
			}
		}

		//HACK: Without this the arrow count does not update correctly
		player.updateInventory();
		return true;
	}

	private Boolean updateArrowIndex(Player player) {
		final String playerName = player.getName();
		final Boolean playerSneaking = player.isSneaking();

		if (plugin.activeArrow.containsKey(playerName)) {
			final Integer originalIndex = plugin.activeArrow.get(playerName);

			if (player.hasPermission("multiarrow.use.all")) {
				plugin.activeArrow.put(playerName, nextIndex(originalIndex, playerSneaking));
				return true;
			}

			Integer currentIndex = nextIndex(originalIndex, playerSneaking);
			while (currentIndex != originalIndex) {
				String arrowName = plugin.arrowTypes.getName(currentIndex);
				String permissionNode = "multiarrow.use." + arrowName.toLowerCase();
				if (player.hasPermission(permissionNode)) {
					plugin.activeArrow.put(playerName, currentIndex);
					return true;
				}
				currentIndex = nextIndex(currentIndex, playerSneaking);
			}

			return false;
		} else {
			plugin.activeArrow.put(playerName, -1);
		}

		return true;
	}
	
	private Integer nextIndex(Integer index, Boolean increment) {
		if ((!increment && index == -1) || (increment && index == plugin.arrowTypes.getMaxIndex())) {
			return (index == -1) ? plugin.arrowTypes.getMaxIndex() : -1;
		} else {
			return index + ((increment) ? 1 : -1);
		}
	}
	
	//private final Logger logger = Logger.getLogger("Minecraft");
}
