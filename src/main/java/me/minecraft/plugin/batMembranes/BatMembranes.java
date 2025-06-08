package me.minecraft.plugin.batMembranes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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
        saveDefaultConfig();

        FileConfiguration config = this.getConfig();
        config.addDefault("membrane_drop_percentage", 25);
        config.addDefault("membrane_drop_amount", 1);
        config.addDefault("membrane_bonus_drop_percentage", 15);
        config.addDefault("membrane_bonus_drop_amount", 2);
    }

    @EventHandler
    public void batDeath(EntityDeathEvent e) {
        LivingEntity ent = e.getEntity();
        Random ran = new Random();
        int num = ran.nextInt(100);
        int num_bon = ran.nextInt(100);

        int mem_drop_per = this.getConfig().getInt("membrane_drop_percentage", 25);
        int mem_drop_am = this.getConfig().getInt("membrane_drop_amount", 1);
        int mem_bon_drop_per = this.getConfig().getInt("membrane_bonus_drop_percentage", 15);
        int mem_bon_drop_am = this.getConfig().getInt("membrane_bonus_drop_amount", 2);

        if (mem_drop_per > 100 || mem_drop_per < 1) {
            mem_drop_per = 25;
        }
        if (mem_drop_am > 10 || mem_drop_am < 1) {
            mem_drop_am = 1;
        }
        if (mem_bon_drop_per > 30 || mem_bon_drop_per < 1) {
            mem_bon_drop_per = 15;
        }
        if (mem_bon_drop_am > 3 || mem_bon_drop_am < 1) {
            mem_bon_drop_am = 2;
        }

        if (ent.getType() == EntityType.BAT) {
            if (num <= mem_drop_per) {
                e.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, mem_drop_am));
            }
            if (num_bon <= mem_bon_drop_per) {
                e.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, mem_bon_drop_am));
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
