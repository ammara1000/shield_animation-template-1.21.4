package nerd.amara;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nerd.amara.mixin.TotemSoundMixin;
import nerd.amara.mixin.WorldMixin;
import nerd.amara.mixin.toolbarMixin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager_maceUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE_maceUtils = new File("config/mace_utils.json");

    public static boolean stunSlam = false;
    public static boolean worldBorder = false;
    public static boolean totem = false;
    public static boolean hotbar = false;

    public static void sync(){
        Shield_animation.stunSlam=stunSlam;
        Shield_animation.toolbar=hotbar;
        Shield_animation.totem=totem;
        Shield_animation.worldBorder=worldBorder;
    }

    public static void load() {
        if (!CONFIG_FILE_maceUtils.exists()) {
            save(); // crée le fichier avec valeurs par défaut
            sync();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE_maceUtils)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            stunSlam = data.stun_slam;
            worldBorder = data.world_border;
            totem = data.totem;
            hotbar = data.hotbar;
            sync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData(stunSlam, worldBorder, totem, hotbar);

        try (FileWriter writer = new FileWriter(CONFIG_FILE_maceUtils)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
        boolean stun_slam;
        boolean world_border;
        boolean totem;
        boolean hotbar;

        public ConfigData(boolean stun_slam, boolean world_border, boolean totem, boolean hotbar) {
            this.stun_slam = stun_slam;
            this.world_border = world_border;
            this.totem = totem;
            this.hotbar = hotbar;
        }
    }
}
