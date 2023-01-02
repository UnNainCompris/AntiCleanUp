package fr.unnaincompris.anticleanup.utils;

import com.google.gson.annotations.Expose;
import fr.unnaincompris.anticleanup.Main;

import java.io.File;

public class Config {

    public static File PLUGIN_FOLDER;

    @Expose public static String SERVER_NAME = "AntiCleanup";
    @Expose public static String SERVER_NAME_TRANSLATE = ColorUtils.translate("&cAnti&7&lCleanup");
    @Expose public static String SERVER_PREFIX_CONSOLE = "AC";
    @Expose public static String SERVER_PREFIX_INGAME = ColorUtils.translate("&7[&cA&5&lC&7]");
    public static String SERVER_VERSION = "v0.1.0";

    public Config() {
        // this.setup();
    }

    private void setup() {
        PLUGIN_FOLDER = FileUtils.getOrCreateFile(Main.getInstance().getDataFolder().getParentFile(), "AntiCleanup"); // Risky to change
    }

    public void saveConfig() {
        JsonUtils.writeData(JsonUtils.getOrCreateJson(PLUGIN_FOLDER, "config"), this);
    }

}
