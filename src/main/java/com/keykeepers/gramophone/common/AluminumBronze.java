package com.keykeepers.gramophone.common;

import com.keykeepers.gramophone.GramophoneMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class AluminumBronze {
  private static final String name;
  private static final Registry.BlockAndItem<Block> storageBlock;
  private static final Registry.BlockAndItem<SlabBlock> storageSlab;
  private static final Registry.BlockAndItem<Block> sheetmetalBlock;
  private static final Registry.BlockAndItem<SlabBlock> sheetmetalSlab;
  private static final Item ingot;
  private static final Item nugget;
  private static final Item dust;
  private static final Item plate;

  private static final Registry registry = GramophoneMod.registry;

  static {
    name = "alum_bronze";
    AbstractBlock.Properties storageProperties = AbstractBlock.Properties.create(Material.IRON)
        .sound(SoundType.METAL)
        .hardnessAndResistance(5, 10)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE)
        .harvestLevel(2);
    storageBlock = registry.registerBlock("storage_" + name, storageProperties);
    storageSlab = registry.registerSlab("slab_storage_" + name, storageProperties);
    AbstractBlock.Properties sheetMetalProperties = AbstractBlock.Properties.create(Material.IRON)
        .sound(SoundType.METAL)
        .hardnessAndResistance(3, 10);
    sheetmetalBlock = registry.registerBlock("sheetmetal_" + name, sheetMetalProperties);
    sheetmetalSlab = registry.registerSlab("slab_sheetmetal_" + name, sheetMetalProperties);
    ingot = registry.registerItem("ingot_" + name);
    nugget = registry.registerItem("nugget_" + name);
    dust = registry.registerItem("dust_" + name);
    plate = registry.registerItem("plate_" + name);
  }

  public static void init() { }

  public static String name() { return name; }
  public static Block storageBlock() { return storageBlock.block; }
  public static Item storageBlockItem() { return storageBlock.item; }
  public static SlabBlock storageSlab() { return storageSlab.block; }
  public static Item storageSlabItem() { return storageSlab.item; }
  public static Block sheetmetalBlock() { return sheetmetalBlock.block; }
  public static Item sheetmetalBlockItem() { return sheetmetalBlock.item; }
  public static SlabBlock sheetmetalSlab() { return sheetmetalSlab.block; }
  public static Item sheetmetalSlabItem() { return sheetmetalSlab.item; }
  public static Item ingot() { return ingot; }
  public static Item nugget() { return nugget; }
  public static Item dust() { return dust; }
  public static Item plate() { return plate; }
}
