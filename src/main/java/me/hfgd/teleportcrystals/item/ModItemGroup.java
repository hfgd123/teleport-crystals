package me.hfgd.teleportcrystals.item;

import me.hfgd.teleportcrystals.TeleportCrystals;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static RegistryKey<ItemGroup> TELEPORT_CRYSTALS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(TeleportCrystals.MOD_ID, "teleport_crystals_group"));

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, TELEPORT_CRYSTALS_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.CORRIDOR_CRYSTAL))
                .displayName(Text.translatable("itemGroup.teleportcrystals.teleport_crystals_group"))
                .build());
    }


}



