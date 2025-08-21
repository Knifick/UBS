package net.knifick.ubs.events;

import net.knifick.ubs.network.ModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onEntityHurt(LivingDamageEvent.Pre event){
        if(!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        ModVariables.PlayerVariables vars = player.getData(ModVariables.PLAYER_VARIABLES);
        vars.onFight = true;
        vars.syncPlayerVariables(player);
    }
}
