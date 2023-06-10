package me.hfgd.teleportcrystals.item;

import me.hfgd.teleportcrystals.TeleportCrystals;
import me.hfgd.teleportcrystals.item.custom.CorridorCrystalItem;
import me.hfgd.teleportcrystals.item.custom.CorridorCrystalLinkedItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CORRIDOR_CRYSTAL = registerItem("corridor_crystal",
            new CorridorCrystalItem(new FabricItemSettings().maxCount(16)));
    public static final Item CORRIDOR_CRYSTAL_LINKED = registerItem("corridor_crystal_linked",
            new CorridorCrystalLinkedItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TeleportCrystals.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ModItemGroup.TELEPORT_CRYSTALS_GROUP, CORRIDOR_CRYSTAL);
    }

    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item));
    }

    public static void registerModItems() {
        TeleportCrystals.LOGGER.info("Registering mod items for " + TeleportCrystals.MOD_ID);

        addItemsToItemGroup();
    }
}
