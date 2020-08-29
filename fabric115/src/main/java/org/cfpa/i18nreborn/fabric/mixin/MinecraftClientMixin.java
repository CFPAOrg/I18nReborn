package org.cfpa.i18nreborn.fabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resource.language.LanguageDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "<init>",at=@At("RETURN"))
    public void init(RunArgs args, CallbackInfo ci){
        MinecraftClient.getInstance().options.language = "zh_cn";
        MinecraftClient.getInstance().getLanguageManager().setLanguage(new LanguageDefinition("zh_cn", "CN", "简体中文", false));
    }
}
