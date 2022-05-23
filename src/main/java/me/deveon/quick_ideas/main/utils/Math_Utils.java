package me.deveon.quick_ideas.main.utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Math_Utils {

  //Return Blocks In Regions//
  public static ArrayList<Block> getBlocksInsideRegion(Plugin instance, Location location1, Location location2, boolean enableStackTrace) throws NullPointerException {

    ArrayList<Block> blocks = new ArrayList<>();

    try {
      Vector max = Vector.getMaximum(location1.toVector(), location2.toVector());
      Vector min = Vector.getMinimum(location1.toVector(), location2.toVector());
      for (int i = min.getBlockX(); i <= max.getBlockX(); i++) {
        for (int j = min.getBlockY(); j <= max.getBlockY(); j++) {
          for (int k = min.getBlockZ(); k <= max.getBlockZ(); k++) {
            Block block = location1.getWorld().getBlockAt(i, j, k);
            blocks.add(block);
          }
        }
      }
    } catch (NullPointerException npe) {
      new BukkitRunnable() {
        public void run() {
          if (enableStackTrace) {
            npe.printStackTrace();
          }
          ArrayList<String> message = new ArrayList<String>();
          message.add("onMethod 'getBlocksInsideRegion'");
          if (location1 != null) {
            message.add("Point2 is 'null'");
          } else if (location2 != null) {
            message.add("Point1 is 'null'");
          } else {
            message.add("Point1 is 'null'");
            message.add("Point2 is 'null'");
          }
          Messages_Templates.consoleMessageWarpper(message, Level.SEVERE);
        }
      }.runTaskLater(instance, 20);
    }
    return blocks;
  }
  // // // // // // // // // //

  //General Mathfunctions and Util//
  public static String formatTime(Integer seconds) {
    int time = seconds;

    int p1 = time % 60;
    int p2 = time / 60;
    int p3 = p2 % 60;

    p2 = p2 / 60;

    if (p3 == 0) {
      return p1 + "s";
    } else if (p1 == 0) {
      return p3 + "m";
    } else {
      return p3 + "m" + ":" + p1 + "s";
    }
  }

  public static Integer getRandomInt(Plugin instance, int max , int min) throws IllegalArgumentException {
    Random ran = new Random();
    int number = ran.nextInt(max + 1);

    if (number <= min) {
      for(int i = number; i < min; i++){
        int randint = ran.nextInt(max + 1);
        if(randint > min){
          number = randint;
          break;
        }
      }
    }

    if (number <= 0) {
      CompletableFuture.supplyAsync(() -> {
        System.out.println("Random max integer needs to be greater than "+ min +", or not 'null'.");
        return null;
      });
    } else {
      return number;
    }
    return -1;
  }
  // // // // // // // // // //

  //Player Particle Effects//
  private static Double y = 0.0;
  private static int rotate = 0;
  private static double height = 0.0;

  private static Vector rotateAboutX(Vector vect, double α) {
    double y = Math.cos(α) * vect.getY() - Math.sin(α) * vect.getZ();
    double z = Math.sin(α) * vect.getY() + Math.cos(α) * vect.getZ();

    return vect.setY(y).setZ(z);
  }

  private static Vector rotateAboutY(Vector vect, double β) {
    double x = Math.cos(β) * vect.getX() + Math.sin(β) * vect.getZ();
    double z = -Math.sin(β) * vect.getX() + Math.cos(β) * vect.getZ();

    return vect.setX(x).setZ(z);
  }

  private static Vector rotateAboutZ(Vector vect, double γ) {
    double x = Math.cos(γ) * vect.getX() - Math.sin(γ) * vect.getY();
    double y = Math.sin(γ) * vect.getX() + Math.cos(γ) * vect.getY();

    return vect.setX(x).setY(y);
  }

  public static Vector rotateFunction(Vector v, Location loc) {
    double yawRadians = Math.toRadians(loc.getYaw());
    double pitchRadians = Math.toRadians(loc.getPitch());

    v = rotateAboutX(v, pitchRadians);
    v = rotateAboutY(v, -yawRadians);

    return v;
  }

  public static ArrayList<Location> drawCircle(Player p, double radius, double offsetX, double offsetY, double offsetZ, int timesToDrawCircle, boolean playFromPlayerSight) {
    ArrayList<Location> particleShape = new ArrayList<Location>();
    Location loc = p.getLocation().add(offsetX, offsetY, offsetZ);
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;

    for (double θ = 0.0D; θ < timesToDrawCircle * 360; θ += 1.0D) {
      x = Math.cos(θ) * radius;
      z = Math.sin(θ) * radius;
      if (playFromPlayerSight) {
        x += loc.getDirection().normalize().getX();
        y += loc.getDirection().normalize().getY();
        z += loc.getDirection().normalize().getZ();
      } else {
        y = 0.0D;
      }
      loc.add(x, y, z);

      Location locNew = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());

      particleShape.add(locNew);
      loc.subtract(x, y, z);

      θ += 1.0D;
    }
    return particleShape;
  }

  public static ArrayList<Location> drawSpiral(Player p, double radius, double spiralAdjustAngle, double verticalSpacing, double maxHeight, double spiralOffset, boolean roating, int rotationAngle) {
    Location loc = p.getLocation();
    ArrayList<Location> particleLocs = new ArrayList<Location>();
    double newRadius = 0;

    loc.setYaw(rotate);
    loc.setPitch(0F);

    for (double θ = 0.0D; θ <= spiralAdjustAngle; θ += 0.05D) {
      double x = radius * Math.cos(θ);
      double z = radius * Math.sin(θ);
      height = θ;

      if (height >= maxHeight) {
        height = 0;
        rotate = 0;
        break;
      }

      Vector vec = new Vector(x, 0, z);
      Location newVec = rotateFunction(vec, loc).toLocation(loc.getWorld());
      Location locNew = new Location(p.getWorld(), loc.getX() - newVec.getX(), (loc.getY() + (θ / verticalSpacing)), loc.getZ() - newVec.getZ());

      particleLocs.add(locNew);

      radius += spiralOffset;

      if (roating == true) {
        rotate += rotationAngle;
      }
    }
    return particleLocs;
  }
  // // // // // // // // // //
}

