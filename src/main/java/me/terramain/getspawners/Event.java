package me.terramain.getspawners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;

public class Event implements Listener {

    @EventHandler
    public void destroyBlock(BlockBreakEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (block.getType() != Material.SPAWNER) return;

        if (
                player.getInventory().getItemInMainHand().
                        getEnchantments().
                        containsKey(
                                Enchantment.SILK_TOUCH
                        )
        ){
            CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();


            SpawnEgg spawnEgg = new SpawnEgg( creatureSpawner.getSpawnedType() );
            spawnEgg.setSpawnedType( creatureSpawner.getSpawnedType() );


            Location location = creatureSpawner.getLocation();
            location.getWorld().dropItem(location,spawnEgg.toItemStack(1));
            location.getWorld().dropItem(location, new ItemStack(Material.SPAWNER));
        }
    }
}
