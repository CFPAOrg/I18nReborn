package org.cfpa.i18nreborn.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;

import java.io.*;
import java.util.Date;

@Environment(EnvType.CLIENT)
public class I18nrebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        String path = System.getProperty("user.home") + "/.i18n/"+SharedConstants.getGameVersion().getName() ;
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
            FileDownloadManager t = new FileDownloadManager("https://ae01.alicdn.com/kf/H0733a1a38d6d4406b0b8e304d0b1f83bU.jpg", "i18n.zip", path);
            t.setSuccessTask(() -> {
                try {
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
