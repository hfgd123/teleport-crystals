package me.hfgd.teleportcrystals.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.nio.channels.FileLock;
import java.util.EnumSet;
import java.util.List;

public class CorridorCrystalLinkedItem extends Item {
    public CorridorCrystalLinkedItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            if (playerEntity.getStackInHand(hand).hasNbt()){
                ItemStack itemStack = playerEntity.getStackInHand(hand);
                String coordsWorldRegistry = itemStack.getNbt().getString("teleportcrystls.worldRegistry");
                String coordsWorldValue = itemStack.getNbt().getString("teleportcrystls.worldValue");
                ServerWorld coordsWorld = world.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier(coordsWorldValue)));
                double coordsX = itemStack.getNbt().getDouble("teleportcrystls.x");
                double coordsY = itemStack.getNbt().getDouble("teleportcrystls.y");
                double coordsZ = itemStack.getNbt().getDouble("teleportcrystls.z");
                float coordsYaw = itemStack.getNbt().getFloat("teleportcrystls.yaw");
                float coordsPitch = itemStack.getNbt().getFloat("teleportcrystls.pitch");
                playerEntity.teleport( coordsWorld, coordsX, coordsY, coordsZ, EnumSet.noneOf(PositionFlag.class), coordsYaw, coordsPitch);
                playerEntity.getItemCooldownManager().set(this, 20);
                if (!playerEntity.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
        }
        return super.use(world, playerEntity, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (itemStack.hasNbt()) {
            String coordsWorld = itemStack.getNbt().getString("teleportcrystls.worldValue");
            double coordsX = itemStack.getNbt().getDouble("teleportcrystls.x");
            double coordsY = itemStack.getNbt().getDouble("teleportcrystls.y");
            double coordsZ = itemStack.getNbt().getDouble("teleportcrystls.z");
            tooltip.add(Text.translatable("item.teleportcrystals.corridor_crystal_linked.tooltip").formatted(Formatting.YELLOW).append(" '" + coordsWorld + ", " + (int) coordsX + ", " + (int) coordsY + ", " + (int) coordsZ + "'"));
        }

    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }


}
