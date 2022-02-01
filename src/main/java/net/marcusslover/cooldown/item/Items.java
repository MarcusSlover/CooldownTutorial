package net.marcusslover.cooldown.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.marcusslover.cooldown.CooldownTutorial;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Items {

    public static final ItemStack MAGICAL_GUN;

    static {
        MAGICAL_GUN = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        MAGICAL_GUN.editMeta(meta -> {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(new NamespacedKey(CooldownTutorial.getInstance(), "magical_gun"), PersistentDataType.BYTE, (byte) 1);

            meta.displayName(
                    Component.text("Magical Gun")
                            .color(TextColor.color(77, 255, 196))
            );
        });
    }

    private Items() {
    }

    public static boolean hasTag(@NotNull ItemStack item, @NotNull String tag) {
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return false;
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        return data.has(new NamespacedKey(CooldownTutorial.getInstance(), tag), PersistentDataType.BYTE);
    }
}
