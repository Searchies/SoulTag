package net.searchies.soultag.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.searchies.soultag.SoulTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @ModifyReturnValue(method = "respawnPlayer", at = @At("TAIL"))
    private ServerPlayerEntity giveSoulTagItems(ServerPlayerEntity original) {
        if (!SoulTag.list.isEmpty()) {
            SoulTag.list.forEach(stack -> original.getInventory().insertStack(stack));
            SoulTag.list.clear();
        }

        return original;
    }
}
