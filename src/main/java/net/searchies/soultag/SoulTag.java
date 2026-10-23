package net.searchies.soultag;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class SoulTag implements ModInitializer {
	public static final String MOD_ID = "soultag";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		SoulTagConfig.load();
	}

	public static Collection<ItemStack> list = new ArrayList<>();
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
