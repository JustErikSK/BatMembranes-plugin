package me.minecraft.plugin.batMembranes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class BatMembranes extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "BatMembranes >> Plugin has been enabled!");
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void batDeath(EntityDeathEvent e) {
        LivingEntity ent = e.getEntity();
        Random ran = new Random();
        int num = ran.nextInt(100);
        if (ent.getType() == EntityType.BAT) {
            if (num < 25) {
                e.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE));
            } else if (num > 85) {
                e.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, 2));
            }
        }
    }

    @EventHandler
    public void phantomSpawn(EntitySpawnEvent e) {
        Entity ent = e.getEntity();
        if (ent.getType() == EntityType.PHANTOM) {
            e.setCancelled(true);
        }
    }
}
