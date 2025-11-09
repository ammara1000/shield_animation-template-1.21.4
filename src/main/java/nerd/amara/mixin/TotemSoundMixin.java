package nerd.amara.mixin;

import nerd.amara.ConfigManager;
import nerd.amara.Shield_animation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class TotemSoundMixin {

    @Inject(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V", at = @At("HEAD"), cancellable = true)
    private void onPlaySound(double x, double y, double z, SoundEvent event, SoundCategory category, float volume, float pitch, boolean useDistance, long seed, CallbackInfo ci){
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (ConfigManager.totem) {
            if (event.id().equals(SoundEvents.ITEM_TOTEM_USE.id()) && x == player.getX() && y == player.getY() && z == player.getZ()) {
                System.out.println("Totem pop: " + String.valueOf(MinecraftClient.getInstance().world.getTime()));
                for (int i = 0; i < Shield_animation.number_of_bells; i++) {
                    player.playSound(Shield_animation.totemSound, 100F, 1F);
                    ci.cancel();
                }
            }
        }
        if (ConfigManager.stunSlam) {
            if (event.id().equals(SoundEvents.ITEM_MACE_SMASH_AIR.id()) || event.id().equals(SoundEvents.ITEM_MACE_SMASH_GROUND.id()) || event.id().equals(SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY.id())) {
                player.sendMessage(Text.of(String.valueOf(MinecraftClient.getInstance().world.getTime() - Shield_animation.lastStun)), true);
            }
            if (event.id().equals(SoundEvents.ITEM_SHIELD_BREAK.id()) && x == player.getX() && y == player.getY() && z == player.getZ()) {
                System.out.println("Sheild broke: " + String.valueOf(MinecraftClient.getInstance().world.getTime()));
            }
            if (event.id().equals(SoundEvents.ITEM_SHIELD_BREAK.id())) {
                Shield_animation.lastStun = MinecraftClient.getInstance().world.getTime();
            }
        }
    }
}
