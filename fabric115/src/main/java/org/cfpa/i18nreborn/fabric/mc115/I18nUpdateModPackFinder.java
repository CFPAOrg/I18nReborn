package org.cfpa.i18nreborn.fabric.mc115;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ZipResourcePack;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class I18nUpdateModPackFinder implements ResourcePackProvider {
    public static final I18nUpdateModPackFinder RESOUCE = new I18nUpdateModPackFinder("Resource Pack", new File(System.getProperty("user.home") + "/.i18n/"+ SharedConstants.getGameVersion().getName() +"/i18n.zip"));

    private final File loaderDirectory;
    private I18nUpdateModPackFinder(String type, File loaderDirectory) {

        this.loaderDirectory = loaderDirectory;
    }
    public static void init(Set<ResourcePackProvider> set){
        set.add(RESOUCE);
    }

    @Override
    public <T extends ResourcePackProfile> void register(Map<String, T> registry, ResourcePackProfile.Factory<T> factory) {
        final String packName = "i18n";
        final T packInfo = ResourcePackProfile.of(packName, true, () -> new ZipResourcePack(loaderDirectory), factory, ResourcePackProfile.InsertionPosition.TOP);
        if (packInfo != null) {
            registry.put(packName,packInfo);
        }
    }
}
