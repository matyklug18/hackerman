package matyk.hackerman.mixins;

import matyk.hackerman.data.HacksList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class MixinChat {

    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String msg, CallbackInfo info) {
        if(msg.equals(".sneak")) {
            info.cancel();
            HacksList.sneakHack = !HacksList.sneakHack;
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("FastSneak " + (HacksList.sneakHack ? "enabled!" : "disabled!")));
        } else if(msg.startsWith(".speed")) {
            info.cancel();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            try {
                HacksList.fastHackMul = Float.parseFloat(msg.substring(7, msg.length()));
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("SpeedHack speed set to " + HacksList.fastHackMul + "!"));
            } catch(NumberFormatException e) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("Not A Number!"));
            }
        } else if(msg.startsWith(".xray add")) {
            info.cancel();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            String name = msg.substring(10, msg.length());
            HacksList.xrayInvHack.put(new ResourceLocation(name), true);
            System.out.println(HacksList.xrayInvHack);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("Added " + name + " to Xray!"));
        } else if(msg.startsWith(".xray rem")) {
            info.cancel();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            String name = msg.substring(10, msg.length());
            HacksList.xrayInvHack.put(new ResourceLocation(name), false);
            System.out.println(HacksList.xrayInvHack);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("Removed " + name + " from Xray!"));
        } else if(msg.startsWith(".")) {
            info.cancel();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString("UNKNOWN COMMAND!"));
        }
    }
}