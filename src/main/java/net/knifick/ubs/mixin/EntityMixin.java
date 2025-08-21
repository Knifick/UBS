package net.knifick.ubs.mixin;

import net.knifick.ubs.network.ModVariables;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void onMove(MoverType type, Vec3 pos, CallbackInfo ci) {
        Entity self = ((Entity)(Object)this);
        if (self instanceof LocalPlayer) {
            ModVariables.PlayerVariables vars = self.getData(ModVariables.PLAYER_VARIABLES);
            if(vars.onFight)
                ci.cancel();
        }
    }
}

