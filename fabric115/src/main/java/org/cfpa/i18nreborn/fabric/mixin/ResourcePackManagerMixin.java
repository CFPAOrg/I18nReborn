package org.cfpa.i18nreborn.fabric.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(ResourcePackManager.class)
public abstract class ResourcePackManagerMixin {
    @Shadow
    private Set<ResourcePackProvider> providers;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void registerLoader(CallbackInfo info) {
        this.providers = new HashSet(this.providers);
        try{
            if(SharedConstants.getGameVersion().getName().toLowerCase().startsWith("1.15")){
                Class.forName("org.cfpa.i18nreborn.fabric.mc115.I18nUpdateModPackFinder").getMethod("init", Set.class).invoke(null, this.providers);
            }
            else if(SharedConstants.getGameVersion().getName().toLowerCase().startsWith("1.16")){
                Class.forName("org.cfpa.i18nreborn.fabric.mc116.I18nUpdateModPackFinder").getMethod("init", Set.class).invoke(null, this.providers);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}