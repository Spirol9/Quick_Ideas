package me.deveon.quick_ideas.main.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Messages_Templates {

  private static Plugin instance;
  public Messages_Templates(Plugin main){
    instance = main;
  }

  private static ChatColor reset = ChatColor.RESET;
  private static ChatColor bold = ChatColor.BOLD;
  private static ChatColor white = ChatColor.WHITE;
  private static ChatColor lGray = ChatColor.GRAY;


  public static void consoleMessageWarpper(List<String> message, Level loggingPriority) {
    instance.getLogger().info("=============================================");
    for (String txt : message) {
      instance.getLogger().log(loggingPriority, txt);
    }
    instance.getLogger().info("=============================================");
  }

  public static void helpCommand(Player p, ChatColor color1, ChatColor color2, HashMap<String, String> helpCmds) {
    ArrayList<String> keys = new ArrayList<>(helpCmds.keySet());

    p.sendMessage("");
    p.sendMessage(color1 + "==================" + color2 + "{" + reset + white + instance.getName() + color2 + "}" + bold + color1 + "===================");
    p.sendMessage("");

    for (String cmd : keys) {
      p.sendMessage(white + "/" + cmd + color1 + " - " + lGray + bold +
          helpCmds.get(cmd));
      p.sendMessage("");
    }

    p.sendMessage("");
    p.sendMessage(color1 + "=====================================" + createBottomLine(instance.getName().length() + 1));
  }

  public static void customMessage(Player p, String message, String extraArgs, ChatColor color1, ChatColor color2) {
    p.sendMessage("");
    p.sendMessage(color1 + "==================" + color2 + "{" + reset + white + instance.getName() + color2 + "}" + bold + color1 + "===================");
    p.sendMessage("");

    p.sendMessage(
        //ChatColor.translateAlternateColorCodes('&', placeholderReplacer(message, p, extraArgs)));
        message);

    p.sendMessage("");
    p.sendMessage(color1 + "=====================================" + createBottomLine(instance.getName().length() + 1));
  }

  public static void noPerm(Player p, ChatColor color1, ChatColor color2) {
    p.sendMessage("");
    p.sendMessage(color1 + "==================" + color2 + "{" + reset + white + instance.getName() + color2 + "}" + bold + color1 + "===================");
    p.sendMessage("");

    p.sendMessage(lGray + "You don't have permission to use that command.");

    p.sendMessage("");
    p.sendMessage(color1 + "=====================================" + createBottomLine(instance.getName().length() + 1));
  }

  public static void usage(Player p, ChatColor color1, ChatColor color2, String usage) {
    p.sendMessage("");
    p.sendMessage(color1 + "==================" + color2 + "{" + reset + white + instance.getName() + color2 + "}" + bold + color1 + "===================");
    p.sendMessage("");
    p.sendMessage(color1 + "Too Many Or Few Args.");
    p.sendMessage("");
    p.sendMessage(lGray + "Try: " + color1 + "- " + white + usage);

    p.sendMessage("");
    p.sendMessage(color1 + "=====================================" + createBottomLine(instance.getName().length() + 1));
  }

  private static String createBottomLine(int pluginNameLength) {
    StringBuilder bottomDashes = new StringBuilder();
    for (int d = 0; d < pluginNameLength; d++) {
      bottomDashes.append("=");
    }
    return bottomDashes.toString();
  }
}

