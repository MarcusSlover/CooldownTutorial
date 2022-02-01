package net.marcusslover.cooldown.gun;

import net.marcusslover.cooldown.item.Items;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MagicalGunListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!Items.hasTag(item, "magical_gun")) return;
        useMagicalGun(event.getPlayer());
    }

    private void useMagicalGun(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation().clone();
        location.setY(location.getY() + 1.65);
        Vector origin = location.toVector();

        RayTraceResult result = player.rayTraceBlocks(30, FluidCollisionMode.NEVER);
        if (result == null) return;
        Vector destination = result.getHitPosition();
        Location hit = destination.toLocation(world);

        Collection<Entity> nearbyEntities = world.getNearbyEntities(hit, 3, 3, 3);
        List<LivingEntity> livingEntities = nearbyEntities.stream().filter(entity -> entity instanceof LivingEntity && entity != player).map(entity -> (LivingEntity) entity).toList();

        Entity hitEntity = result.getHitEntity();
        if (hitEntity instanceof LivingEntity livingEntity) {
            if (!livingEntities.contains(livingEntity)) livingEntities.add(livingEntity);
        }
        livingEntities.forEach(livingEntity -> livingEntity.damage(7.0, player));

        world.spawnParticle(Particle.EXPLOSION_LARGE, hit, 1, 0, 0, 0, 0);
        world.playSound(hit, Sound.ENTITY_BLAZE_HURT, 0.5f, 1.5f);

        //List<Vector> points = new ArrayList<>();
        double frequency = 0.05f;
        float x = 1.0f;

        Vector difference = origin.clone().subtract(destination);
        while (x > 0.0f) {
            Vector pointVector = destination.clone().add(difference.clone().multiply(x));
            Location point = pointVector.toLocation(world);
            world.spawnParticle(Particle.FIREWORKS_SPARK, point, 1, 0, 0, 0, 0);
            //points.add(point);
            x -= frequency;
        }

        world.playSound(location, Sound.ENTITY_LLAMA_SPIT, 1.0f, 1.5f);

        /*
        new BukkitRunnable() {
            private int index = 0;

            @Override
            public void run() {
                Vector vector = points.get(index);
                Location location = vector.toLocation(world);
                world.spawnParticle(Particle.FIREWORKS_SPARK, location, 1, 0, 0, 0, 0);

                index++;
                if (index == points.size()) this.cancel();
            }
        }.runTaskTimer(CooldownTutorial.getInstance(), 0L, 1L);
         */

    }
}
