package ayan4m1.MultiArrow;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * MultiArrow block listener
 * @author ayan4m1
 */
public class MultiArrowBlockListener extends BlockListener {
    private final MultiArrow plugin;

    public MultiArrowBlockListener(final MultiArrow plugin) {
        this.plugin = plugin;
    }

    //put all Block related code here
}
