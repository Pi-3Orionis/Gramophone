package com.keykeepers.gramophone.common;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

// TODO: Mod Agnostic

public class Registry {
  private final ItemGroup itemGroup;
  private final DeferredRegister<Block> blockRegistry;
  private final DeferredRegister<Item> itemRegistry;

  public Registry(String modId, ItemGroup itemGroup) {
    this.itemGroup = itemGroup;
    blockRegistry =  DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
    itemRegistry =  DeferredRegister.create(ForgeRegistries.ITEMS, modId);
  }

  public void registerBus(IEventBus bus) {
    blockRegistry.register(bus);
    itemRegistry.register(bus);
  }

  public BlockAndItem<Block> registerBlock(String name, AbstractBlock.Properties blockProperties,
                                 Item.Properties itemProperties) {
    return new BlockAndItem<Block>(name, Block::new, blockProperties, itemProperties.group(itemGroup));
  }

  public BlockAndItem<Block> registerBlock(String name, AbstractBlock.Properties properties) {
    return new BlockAndItem<Block>(name, Block::new, properties, new Item.Properties().group(itemGroup));
  }

  public BlockAndItem<SlabBlock> registerSlab(String name, AbstractBlock.Properties blockProperties,
                                    Item.Properties itemProperties) {
    return new BlockAndItem<SlabBlock>(name, SlabBlock::new, blockProperties, itemProperties.group(itemGroup));
  }

  public BlockAndItem<SlabBlock> registerSlab(String name, AbstractBlock.Properties properties) {
    return new BlockAndItem<SlabBlock>(name, SlabBlock::new, properties, new Item.Properties().group(itemGroup));
  }

  public Item registerItem(String name, Item.Properties properties) {
    Item item = new Item(properties.group(itemGroup));
    itemRegistry.register(name, () -> item);
    return item;
  }

  public Item registerItem(String name) {
    Item item = new Item(new Item.Properties().group(itemGroup));
    itemRegistry.register(name, () -> item);
    return item;
  }

  public class BlockAndItem<B extends Block> {
    public final String name;
    public final B block;
    public final BlockItem item;

    private BlockAndItem(String name,
                         Function<AbstractBlock.Properties, B> blockSupplier,
                         AbstractBlock.Properties blockProperties,
                         Item.Properties itemProperties) {
      this.name = name;
      block = blockSupplier.apply(blockProperties);
      blockRegistry.register(name, () -> block);
      item = new BlockItem(block, itemProperties);
      itemRegistry.register(name, () -> item);
    }
  }
}
