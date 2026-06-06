package com.optimobileplusz.client.mixin;

import net.minecraft.class_846;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_846.class)
public class ChunkRenderMixin {

    /**
     * Yarn 1.21.1-ben a ChunkRenderDispatcher helyett ChunkBuilder-t használunk.
     * A "sendChunks" vagy "scheduleRunTasks" metódusok felelősek a feladatok ütemezéséért.
     */
    @Inject(method = "scheduleRunTasks", at = @At("HEAD"))
    private void onScheduleRunTasks(CallbackInfo ci) {
        // Megemeljük a prioritást, de korlátozzuk a processzor túlhajtását
        Thread currentThread = Thread.currentThread();
        if (currentThread.getName().contains("Chunk Batcher")) {
            // A NORM_PRIORITY - 1 biztosítja, hogy a chunkok töltődjenek, 
            // de ne akadjon meg tőle a játék (fő játékszál).
            currentThread.setPriority(Thread.NORM_PRIORITY - 1); 
        }
    }
}
