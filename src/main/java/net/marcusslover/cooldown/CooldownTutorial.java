package net.marcusslover.cooldown;

import lombok.Getter;
import net.marcusslover.cooldown.gun.MagicalGunCommand;
import net.marcusslover.cooldown.gun.MagicalGunListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CooldownTutorial extends JavaPlugin {

    @Getter
    private static CooldownTutorial instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MagicalGunListener(), this);
        registerCommand("gun", new MagicalGunCommand());
    }

    private void registerCommand(@NotNull String label, @NotNull CommandExecutor executor) {
        PluginCommand command = getCommand(label);
        if (command == null) return;
        command.setExecutor(executor);
    }

    @Override
    public void onDisable() {

    }
}
