package org.cfpa.i18nreborn.fabric.mc116;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Consumer;

public class I18nUpdateModPackFinder implements ResourcePackProvider {
    public static final I18nUpdateModPackFinder RESOUCE = new I18nUpdateModPackFinder("Resource Pack", new File(System.getProperty("user.home") + "/.i18n/"+ SharedConstants.getGameVersion().getName().replaceAll("(.+\\..+)\\..+","$1") +"/i18n.zip"));

    private final File loaderDirectory;
    private I18nUpdateModPackFinder(String type, File loaderDirectory) {

        this.loaderDirectory = loaderDirectory;
    }
    public static void init(Set<ResourcePackProvider>  set){
        set.add(RESOUCE);
    }

    @Override
    public void register(Consumer<ResourcePackProfile> consumer, ResourcePackProfile.Factory factory) {
        final String packName = "i18n";
        final ResourcePackProfile packInfo = ResourcePackProfile.of(packName, true, () -> new ZipResourcePack(loaderDirectory), factory, ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.field_25347);
        if (packInfo != null) {
            consumer.accept(packInfo);
        }
    }
}
