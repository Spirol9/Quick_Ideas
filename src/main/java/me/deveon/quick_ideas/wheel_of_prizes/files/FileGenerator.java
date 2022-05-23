package me.deveon.quick_ideas.wheel_of_prizes.files;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * This is a simple file system that is going to be used to make custom files easily
 *
 * @version 1.0.0
 * @author xDeSpam
 */
public class FileGenerator {

    private final String fileName;
    private final File file;

    private YamlConfiguration config;

    public FileGenerator(String dataFolderPath, String fileName) {
        this.fileName = fileName;
        this.file = new File(dataFolderPath, (fileName.endsWith(".yml") ? fileName : fileName + ".yml"));

        if (!this.file.getParentFile().exists())
            this.file.getParentFile().mkdir();

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileGenerator(File dataFolder, String fileName) {
        this.file = new File(dataFolder, (fileName.endsWith(".yml") ? fileName : fileName + ".yml"));
        this.fileName = fileName;

        if (!this.file.getParentFile().exists())
            this.file.getParentFile().mkdir();

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * this will save the config
     */
    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this will reload the config if there is an changes from the outside
     */
    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * this will get the config options for the yaml file
     * @return the config for the yaml file
     */
    public YamlConfiguration getConfig() {
        return config;
    }

    /**
     * get the yaml file itself
     * @return yaml file
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @return gets the name for the yaml file
     */
    public String getFileName() {
        return fileName;
    }
}
