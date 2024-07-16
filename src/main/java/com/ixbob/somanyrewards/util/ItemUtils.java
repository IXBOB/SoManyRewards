package com.ixbob.somanyrewards.util;

import com.ixbob.somanyrewards.lang.LangManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    private static final LangManager langManager = LangManager.getInstance();

    public static ItemStack getNamedItemStack(ItemStack itemStack, Player player, String title, @Nullable ArrayList<String> lores) {
        ItemStack result = new ItemStack(itemStack);
        ItemMeta meta = itemStack.getItemMeta();
        System.out.println(title + "========");
        System.out.println(langManager.getLang(player, title) + "1231231231231231");
        meta.displayName(Component.text(langManager.getLang(player, title)));
        if (lores != null) {
            List<Component> loreList = meta.lore();
            if (loreList == null) {
                loreList = new ArrayList<>();
            }
            for (String lore : lores) {
                loreList.add(Component.text(langManager.getLang(player, lore)));
            }
            meta.lore(loreList);
        }
        result.setItemMeta(meta);
        return result;
    }
}
