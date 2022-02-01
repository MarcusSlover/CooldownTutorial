package net.marcusslover.cooldown.gun;

import net.marcusslover.cooldown.item.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class MagicalGunCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            PlayerInventory inventory = player.getInventory();
            inventory.addItem(Items.MAGICAL_GUN);
        }
        return true;
    }
}
