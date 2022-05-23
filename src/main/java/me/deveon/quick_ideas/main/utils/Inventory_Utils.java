package me.deveon.quick_ideas.main.utils;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory_Utils {

  public static Inventory createNewInventory(int inventorySize, String inventoryName) {
    String guiName = ChatColor.translateAlternateColorCodes('&', inventoryName);
    return Bukkit.createInventory(null, inventorySize, guiName);
  }

  public static ItemStack createItem(Material itemMaterial, String itemName, String[] itemLore) {
    ItemStack item = new ItemStack(itemMaterial, 1);

    ItemMeta im = item.getItemMeta();
    ArrayList<String> loreList = new ArrayList();

    for (String lore : itemLore) {
      if (lore != null) {
        loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
      }
    }

    im.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));

    im.setLore(loreList);
    item.setItemMeta(im);

    return item;
  }

  public static ItemStack updateItem(ItemStack item, Material newMaterial, String[] newLore) {
    return createItem(newMaterial, item.getItemMeta().getDisplayName(), newLore);
  }
}
