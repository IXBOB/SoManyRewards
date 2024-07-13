package com.ixbob.somanyrewards.util;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static ItemStack getNamedItemStack(ItemStack itemStack, String name) {
        ItemStack result = new ItemStack(itemStack);
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text(name));
        result.setItemMeta(meta);
        return result;
    }
}
