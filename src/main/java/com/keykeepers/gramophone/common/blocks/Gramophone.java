package com.keykeepers.gramophone.common.blocks;

import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.common.Registry;
import com.keykeepers.gramophone.common.Registry.BlockAndItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class Gramophone {
  private static final BlockAndItem<Block> blockAndItem;
  private static final Registry registry = GramophoneMod.registry;

  static {
    AbstractBlock.Properties properties = AbstractBlock.Properties.create(Material.IRON)
        .sound(SoundType.METAL);
    blockAndItem = registry.registerBlock("gramophone", properties);
  }

  public static void init() { }

  public static Block block() {
    return blockAndItem.block;
  }

  public static Item item() {
    return  blockAndItem.item;
  }
}
