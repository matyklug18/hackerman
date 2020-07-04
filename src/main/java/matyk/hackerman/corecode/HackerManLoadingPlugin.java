package matyk.hackerman.corecode;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(-5000)
public class HackerManLoadingPlugin implements IFMLLoadingPlugin {

    public HackerManLoadingPlugin () {

        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.hackerman.json");
    }

    @Override
    public String[] getASMTransformerClass () {

        return new String[0];
    }

    @Override
    public String getModContainerClass () {

        return null;
    }

    @Nullable
    @Override
    public String getSetupClass () {

        return null;
    }

    @Override
    public void injectData (Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass () {

        return null;
    }
}