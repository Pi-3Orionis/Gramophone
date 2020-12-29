package com.keykeepers.immersivegramophone.common;

import com.keykeepers.immersivegramophone.ImmersiveGramophone;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ImmersiveGramophone.MODID, bus = Bus.MOD)
public class Config {
  public static final ForgeConfigSpec ALL;
  public static final General GENERAL;

  static {
    Builder builder = new Builder();

    GENERAL = new General(builder);

    ALL = builder.build();
  }

  public static class General {
    public final BooleanValue removeAluminumBronze;

    General(Builder builder) {
      builder.push("General");

      // Aluminum Brass
      removeAluminumBronze = builder.comment((("Disables alloy recipes for making Aluminum Bronze via " +
          ("this mod. If there isn't some alternative for making brass, bronze or aluminum brass, this" +
              "will make many recipes in this mod impossible!"))))
          .define("removeAluminumBronze", false);

      builder.pop();
    }
  }
}
