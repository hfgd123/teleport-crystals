package me.hfgd.teleportcrystals;

import me.hfgd.teleportcrystals.item.ModItemGroup;
import me.hfgd.teleportcrystals.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeleportCrystals implements ModInitializer {
	public static final String MOD_ID = "teleportcrystals";
    public static final Logger LOGGER = LoggerFactory.getLogger("teleportcrystals");

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
	}
}