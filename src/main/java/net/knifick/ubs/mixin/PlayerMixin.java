package net.knifick.ubs.mixin;

import net.knifick.ubs.network.ModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Player self = (Player)(Object)this;
        ModVariables.PlayerVariables vars = self.getData(ModVariables.PLAYER_VARIABLES);
        if(self instanceof ServerPlayer)
        {
            vars.onFight = true;
            vars.syncPlayerVariables(self);
        }
        if (vars.onFight)
            cir.cancel(); // не даём урону пройти
    }
}


