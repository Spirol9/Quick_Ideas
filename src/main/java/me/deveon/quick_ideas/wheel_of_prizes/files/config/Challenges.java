package me.deveon.quick_ideas.wheel_of_prizes.files.config;

import java.util.Arrays;
import java.util.logging.Level;

import me.deveon.quick_ideas.main.Main;
import me.deveon.quick_ideas.main.utils.Messages_Templates;
import me.deveon.quick_ideas.wheel_of_prizes.files.FileGenerator;
import me.deveon.quick_ideas.wheel_of_prizes.main.WOPMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Challenges {

  private final Main instance;
  private final FileGenerator generator;
  private Plugin mainInstance;


  public Challenges(WOPMain plugin, Main mainInstance) {
    instance = mainInstance;
    this.mainInstance = mainInstance;
    this.generator = new FileGenerator(mainInstance.getDataFolder(), "Challenges");
    configDefaults();
    Bukkit.getLogger().warning("1");
  }

  private void configDefaults() {
    Bukkit.getLogger().warning("2");
    if (getConfig().getConfigurationSection("Challenges") == null) {
      getConfig().addDefault("Challenges", Arrays.asList("Find a Gold Ore in 5mins." ,"Survive a polar bear attack", "Sing me a song (NON-COPY RIGHT) :("));

      getConfig().options().copyDefaults(true);
      this.generator.saveConfig();
    }

    getConfig().addDefault("ColorCodes",
        "https://proxy.spigotmc.org/9b807c84f6dabfe63ef9c8ca915f69f89acb9cb6?url=http%3A%2F%2Fwww11.pic-upload.de%2F21.07.15%2Fvwk4qs2sng4u.png");
    //getConfig().addDefault("Placeholders", "%Result%, %TimeLeft%, %TotalPlayersVoted%, %PlayerName%");

    getConfig().options().copyDefaults(true);
    this.generator.saveConfig();
  }

  public void configSave() {
    this.generator.saveConfig();
  }

  public void reloadConfig() {
    this.generator.reloadConfig();
  }

  public YamlConfiguration getConfig() {
    return this.generator.getConfig();
  }
}
