package com.optimobileplusz.client.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public class ChunkRenderMixin {

    /**
     * A Mojang mappingben a ChunkRenderDispatcher megfelelője a ChunkBuilder.
     * A scheduleRunTasks metódus az újabb verziókban is elérhető, 
     * amely a chunkok feladatainak ütemezését végzi.
     */
    @Inject(method = "scheduleRunTasks", at = @At("HEAD"))
    private void onScheduleRunTasks(CallbackInfo ci) {
        // Megemeljük a prioritást, de korlátozzuk a processzor túlhajtását
        Thread currentThread = Thread.currentThread();
        
        // A \"Chunk Batcher\" szálak kezelése
        if (currentThread.getName().contains("Chunk Batcher")) {
            // A NORM_PRIORITY - 1 biztosítja, hogy a chunkok töltődjenek, 
            // de ne akadjon meg tőle a fő játékszál (main thread).
            currentThread.setPriority(Thread.NORM_PRIORITY - 1); 
        }
    }
}
