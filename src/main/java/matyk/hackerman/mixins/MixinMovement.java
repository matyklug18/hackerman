package matyk.hackerman.mixins;

import matyk.hackerman.HackerManMod;
import matyk.hackerman.data.HacksList;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public class MixinMovement extends MovementInput {

    @Inject(method = "updatePlayerMoveState()V", at = @At("RETURN"), cancellable = true)
    public void updatePlayerMoveState(CallbackInfo ci) {
        if(HacksList.sneakHack) {
            this.moveStrafe = (float) ((double) this.moveStrafe / 0.3D);
            this.moveForward = (float) ((double) this.moveForward / 0.3D);
        }
    }
}
