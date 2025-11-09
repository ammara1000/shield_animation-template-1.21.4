package nerd.amara.mixin;

import nerd.amara.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class toolbarMixin {
    private MinecraftClient client = MinecraftClient.getInstance();
    @Final
    @Shadow private GameOptions options;
    @Shadow private ClientPlayerEntity player;
    @Shadow private Screen currentScreen;
    @Final
    @Shadow private InGameHud inGameHud;



    @Inject(
            method = "handleInputEvents",
            at = @At("HEAD")
    )
    private void onHandleInputEvents(CallbackInfo ci) {
        if (ConfigManager.hotbar) {
            for (int i = 8; i >= 0; i--) {
                boolean bl = this.options.saveToolbarActivatorKey.isPressed();
                boolean bl2 = this.options.loadToolbarActivatorKey.isPressed();
                if (this.options.hotbarKeys[i].wasPressed()) {
                    if (this.player.isSpectator()) {
                        this.inGameHud.getSpectatorHud().selectSlot(i);
                    } else if (!this.player.isCreative() || this.currentScreen != null || !bl2 && !bl) {
                        this.player.getInventory().selectedSlot = i;
                    } else {
                        CreativeInventoryScreen.onHotbarKeyPress(client, i, bl2, bl);
                    }
                }
            }
        }
    }
}
