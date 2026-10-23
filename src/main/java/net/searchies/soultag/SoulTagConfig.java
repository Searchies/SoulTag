package net.searchies.soultag;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoulTagConfig {
    public static SoulTagConfig INSTANCE;

    public List<String> exceptions = new ArrayList<>();

    public static void load() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("soultag.json").toFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                INSTANCE = gson.fromJson(reader, SoulTagConfig.class);
            } catch (IOException e) {
                INSTANCE = new SoulTagConfig();
            }
        } else {
            INSTANCE = new SoulTagConfig();
            save();
        }
    }

    public static void save() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("soultag.json").toFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(gson.toJson(INSTANCE));
        } catch (IOException e) {
            SoulTag.LOGGER.error("Config not saved: ", e);
        }
    }
}