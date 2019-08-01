/*
 * EndPortalBreakFix Copyright © 2019 neobenedict
 * SPDX-License-Identifier: GPL-3.0-only
 */

package eu.ninebsixt.endportalbreakfix;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndPortalBreakFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("EndPortalBreakFix loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll();
    }

    @EventHandler
    public void onBlockPlaceEvent(PlayerBucketEmptyEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlockClicked().getRelative(e.getBlockFace());
        Material matBroken = block.getType();
        if (matBroken.equals(Material.ENDER_PORTAL) || matBroken.equals(Material.END_GATEWAY)) {
            e.setCancelled(true);
            getLogger().warning(String.format("Prevented destruction of %s with %s at %s %d, %d, %d by %s",
                    matBroken, e.getBucket(), player.getWorld().getName(),
                    block.getX(), block.getY(), block.getZ(), player.getName()));
            player.updateInventory();
        }
    }
}
