package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.module.FrameBudgetManager;
import com.optimobileplusz.module.ParticleLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_2394;
import net.minecraft.class_702;
import net.minecraft.class_703;

@Mixin(class_702.class)
public class ParticleMixin {
    
    // Yarn 1.21.1 stabil injektálási pont a részecske létrehozás elejére
    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", at = @At("HEAD"), cancellable = true)
    private void handleParticles(class_2394 parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<class_703> cir) {
        
        // 1. Ha a configban teljesen le vannak tiltva a részecskék, azonnal töröljük
        if (!OptiMobileConfig.particlesEnabled) {
            cir.setReturnValue(null);
            return;
        }

        // 2. Intelligens korlátozás: leérjük a szorzót (pl. EXTREME esetén 15)
        int chance = ParticleLimiter.getParticleMultiplier();
        chance = FrameBudgetManager.getMaxParticleChance(chance);
        if (chance < 100) {
            // Generálunk egy számot 0 és 99 között. Ha nagyobb mint a limitünk, eldobjuk a részecskét.
            if (ThreadLocalRandom.current().nextInt(100) >= chance) {
                cir.setReturnValue(null); // Megszakítjuk a részecske létezését
            }
        }
    }
}