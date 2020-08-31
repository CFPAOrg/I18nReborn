package org.cfpa.i18nreborn.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.Date;

@Environment(EnvType.CLIENT)
public class I18nrebornClient implements ClientModInitializer {
    public static String URL = "http://downloader1.meitangdehulu.com:22943/Minecraft-Mod-Language-Modpack-1-16.zip";

    @Override
    public void onInitializeClient() {

        String path = System.getProperty("user.home") + "/.i18n/"+SharedConstants.getGameVersion().getName().replaceAll("(.+\\..+)\\..+","$1") ;
        File filename = new File(path + "/update.txt");
        try {
            if (filename.exists()) {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
                BufferedReader br = new BufferedReader(reader);
                String line = "";
                line = br.readLine();
                br.close();
                if (line == null || new Date().getTime() - Long.parseLong(line) < 7 * 24 * 60 * 60) {
                    return;
                }
            }
            FileDownloadManager t = new FileDownloadManager(URL, "Minecraft-Mod-Language-Modpack.zip", path);
            t.setSuccessTask(() -> {
                try {
                    MinecraftClient.getInstance().reloadResources();
                    File writename = new File(path + "/update.txt");
                    if (!writename.exists())
                        writename.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                    out.write(String.valueOf(new Date().getTime()));
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start("dl i18n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
