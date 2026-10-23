package net.searchies.soultag.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.rule.GameRules;
import net.searchies.soultag.SoulTag;
import net.searchies.soultag.SoulTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V"), method = "dropInventory")
    private void deleteSoulTagItems(ServerWorld world, CallbackInfo ci) {
        if (!world.getGameRules().getValue(GameRules.KEEP_INVENTORY).booleanValue()) {
            PlayerEntity player = (PlayerEntity) (Object) this;

            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack itemStack = player.getInventory().getStack(i);
                if (itemStack.isEmpty())
                    continue;

                if (SoulTagConfig.INSTANCE.exceptions.contains(itemStack.getItem().toString())) {
                    SoulTag.list.add(itemStack);
                    player.getInventory().removeStack(i);
                }
            }
        }
    }
}
