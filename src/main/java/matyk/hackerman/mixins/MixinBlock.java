package matyk.hackerman.mixins;

import matyk.hackerman.data.HacksList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    public void getRenderType(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
        if(HacksList.xrayInvHack.getOrDefault(state.getBlock().getRegistryName(), false))
            cir.setReturnValue(EnumBlockRenderType.INVISIBLE);
    }

    @Inject(method = "isOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void isOpaqueCube(IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(HacksList.xrayInvHack.getOrDefault(state.getBlock().getRegistryName(), false))
            cir.setReturnValue(false);
    }

    @Inject(method = "isFullCube", at = @At("HEAD"), cancellable = true)
    public void isFullCube(IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(HacksList.xrayInvHack.getOrDefault(state.getBlock().getRegistryName(), false))
            cir.setReturnValue(false);
    }

    @Inject(method = "getLightValue(Lnet/minecraft/block/state/IBlockState;)I", at = @At("HEAD"), cancellable = true)
    public void getLightValue(IBlockState state, CallbackInfoReturnable<Integer> cir) {
        if(HacksList.xrayInvHack.getOrDefault(state.getBlock().getRegistryName(), false))
            cir.setReturnValue(15);
    }
}
