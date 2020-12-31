package com.keykeepers.gramophone.common.items;

import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.common.Registry;
import net.minecraft.item.Item;

public class ToneArm {
  public static final Item item;

  private static final Registry registry = GramophoneMod.registry;

  static {
    item = registry.registerItem("tone_arm");
  }

  public static void init() { }
}
