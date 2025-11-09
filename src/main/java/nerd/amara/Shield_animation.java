package nerd.amara;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shield_animation implements ClientModInitializer {
	public static final String MOD_ID = "shield_animation";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static long lastStun = 0;
    public static int number_of_bells = 10;
    public static SoundEvent totemSound = SoundEvents.BLOCK_BELL_USE;

	@Override
	public void onInitializeClient() {
        ConfigManager.load();
        ModCommand.register();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        ClientChunkEvents.CHUNK_LOAD.register((world, chunk)->{
            //MinecraftClient.getInstance().player.playSound(SoundEvents.ITEM_TOTEM_USE);
        });


		LOGGER.info("Hello Fabric world!");
	}
}