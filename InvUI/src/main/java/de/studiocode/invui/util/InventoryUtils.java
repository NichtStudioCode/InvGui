package de.studiocode.invui.util;

import de.studiocode.invui.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    
    public static Inventory createMatchingInventory(GUI gui, String title) {
        InventoryType type;
        
        if (gui.getWidth() == 9) type = null;
        else if (gui.getWidth() == 3 && gui.getHeight() == 3) type = InventoryType.DROPPER;
        else if (gui.getWidth() == 5 && gui.getHeight() == 1) type = InventoryType.HOPPER;
        else throw new UnsupportedOperationException("Invalid bounds of GUI");
        
        if (type == null) return Bukkit.createInventory(null, gui.getSize(), title);
        else return Bukkit.createInventory(null, type, title);
    }
    
    public static boolean containsSimilar(Inventory inventory, ItemStack itemStack) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack currentStack = inventory.getItem(i);
            if (currentStack != null && currentStack.getType().isAir()) currentStack = null;
            
            if ((currentStack == null && itemStack == null)
                || (currentStack != null && currentStack.isSimilar(itemStack))) return true;
        }
        
        return false;
    }
    
    public static void dropItemLikePlayer(Player player, ItemStack itemStack) {
        Location location = player.getLocation();
        location.add(0, 1.5, 0); // not the eye location
        Item item = location.getWorld().dropItem(location, itemStack);
        item.setPickupDelay(40);
        item.setVelocity(location.getDirection().multiply(0.35));
    }
    
}
