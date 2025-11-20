package nerd.amara.mixin;


import nerd.amara.ConfigManager_maceUtils;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static nerd.amara.Shield_animation.worldBorder;


@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "getWorldBorder", at = @At("HEAD"), cancellable = true)
    private void disableWorldBorder(CallbackInfoReturnable<WorldBorder> cir) {
        //if (worldBorder){
        if (ConfigManager_maceUtils.worldBorder) {
            WorldBorder fake = new WorldBorder();
            fake.setCenter(0, 0);
            fake.setSize(Double.MAX_VALUE); // aucune limite
            cir.setReturnValue(fake);
        }
    }
}
