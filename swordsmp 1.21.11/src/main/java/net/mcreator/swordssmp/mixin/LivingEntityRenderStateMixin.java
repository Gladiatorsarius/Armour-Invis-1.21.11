package net.mcreator.swordssmp.mixin;

import net.mcreator.swordssmp.SwordsmpInvisibilityState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements SwordsmpInvisibilityState {
    private int swordsmp_invisibilityAmplifier = -1;

    @Override
    public int swordsmp_getInvisibilityAmplifier() {
        return swordsmp_invisibilityAmplifier;
    }

    @Override
    public void swordsmp_setInvisibilityAmplifier(int amplifier) {
        this.swordsmp_invisibilityAmplifier = amplifier;
    }
}
