package me.hfgd.teleportcrystals.item.custom;

import me.hfgd.teleportcrystals.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CorridorCrystalItem extends Item {
    public CorridorCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            World w = playerEntity.getWorld();
            double x = playerEntity.getX();
            double y = playerEntity.getY();
            double z = playerEntity.getZ();
            float yaw = playerEntity.getYaw();
            float pitch = playerEntity.getPitch();
            ItemStack linkedCrystal = new ItemStack(ModItems.CORRIDOR_CRYSTAL_LINKED);
            NbtCompound nbtData = new NbtCompound();
            nbtData.putString("teleportcrystls.worldRegistry", w.getRegistryKey().getRegistry().toString());
            nbtData.putString("teleportcrystls.worldValue", w.getRegistryKey().getValue().toString());
            nbtData.putDouble("teleportcrystls.x", x);
            nbtData.putDouble("teleportcrystls.y", y);
            nbtData.putDouble("teleportcrystls.z", z);
            nbtData.putFloat("teleportcrystls.yaw", yaw);
            nbtData.putFloat("teleportcrystls.pitch", pitch);
            linkedCrystal.setNbt(nbtData);
            playerEntity.getInventory().insertStack(linkedCrystal);
            playerEntity.getItemCooldownManager().set(this, 20);
            if (!playerEntity.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }
        return super.use(world, playerEntity, hand);
    }
}
