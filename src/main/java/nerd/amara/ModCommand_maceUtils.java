package nerd.amara;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class ModCommand_maceUtils {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    literal("mace-utils")
                            .then(literal("set")
                                    .then(literal("stun_slam").then(argument("value", BoolArgumentType.bool())
                                            .executes(ctx -> set("stun_slam", BoolArgumentType.getBool(ctx, "value")))))
                                    .then(literal("world_border").then(argument("value", BoolArgumentType.bool())
                                            .executes(ctx -> set("world_border", BoolArgumentType.getBool(ctx, "value")))))
                                    .then(literal("totem").then(argument("value", BoolArgumentType.bool())
                                            .executes(ctx -> set("totem", BoolArgumentType.getBool(ctx, "value")))))
                                    .then(literal("hotbar").then(argument("value", BoolArgumentType.bool())
                                            .executes(ctx -> set("hotbar", BoolArgumentType.getBool(ctx, "value")))))
                            )
            );
        });
    }

    private static int set(String key, boolean value) {
        switch (key) {
            case "stun_slam" -> ConfigManager_maceUtils.stunSlam = value;
            case "world_border" -> ConfigManager_maceUtils.worldBorder = value;
            case "totem" -> ConfigManager_maceUtils.totem = value;
            case "hotbar" -> ConfigManager_maceUtils.hotbar = value;
        }
        ConfigManager_maceUtils.save();
        ConfigManager_maceUtils.sync();
        return 1;
    }
}