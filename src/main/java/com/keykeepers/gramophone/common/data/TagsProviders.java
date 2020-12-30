package com.keykeepers.gramophone.common.data;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

// TODO Mod agnostic

public class TagsProviders {
  public static final HashMap<Block, INamedTag<Block>> blockTagsMap = new HashMap<>();
  public static final HashMap<Item, INamedTag<Item>> itemTagsMap = new HashMap<>();
  public static final HashMap<String, ArrayList<INamedTag<Item>>> subgroupItemsMap = new HashMap<>();
  public static final HashMap<String, INamedTag<Item>> subgroupTagsMap = new HashMap<>();

  public static ResourceLocation forgeLoc(String name) {
    return new ResourceLocation("forge", name);
  }

  public static INamedTag<Block> addBlockTag(Block block, String tagString) {
    INamedTag<Block> tag = BlockTags.makeWrapperTag(forgeLoc(tagString).toString());
    blockTagsMap.put(block, tag);
    return tag;
  }

  public static INamedTag<Item> addItemTag(Item item, String tagString) {
    INamedTag<Item> tag = ItemTags.makeWrapperTag(forgeLoc(tagString).toString());
    itemTagsMap.put(item, tag);
    return tag;
  }

  public static INamedTag<Item> addItemSubgroupTag(INamedTag<Item> itemTag, String subgroup) {
    ArrayList<INamedTag<Item>> items = subgroupItemsMap.computeIfAbsent(subgroup, k -> new ArrayList<>());
    items.add(itemTag);
    if (!subgroupTagsMap.containsKey(subgroup))
      subgroupTagsMap.put(subgroup, ItemTags.makeWrapperTag(forgeLoc(subgroup).toString()));
    return subgroupTagsMap.get(subgroup);
  }

  public final ModBlockTagsProvider blockTagsProvider;
  public final ModItemTagsProvider itemTagsProvider;

  public TagsProviders(DataGenerator generator, String modId, @Nullable ExistingFileHelper fileHelper) {
    blockTagsProvider = new ModBlockTagsProvider(generator, modId, fileHelper);
    itemTagsProvider = new ModItemTagsProvider(generator, blockTagsProvider, modId, fileHelper);
  }

  private static class ModBlockTagsProvider extends BlockTagsProvider {
  private ModBlockTagsProvider(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
      super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
      for (Block block : blockTagsMap.keySet()) {
        INamedTag<Block> tag = blockTagsMap.get(block);
        getOrCreateBuilder(tag).add(block);
      }
    }
  }

  private static class ModItemTagsProvider extends ItemTagsProvider {

    private ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId,
                                @Nullable ExistingFileHelper existingFileHelper) {
      super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
      for (Block block : blockTagsMap.keySet()) {
        INamedTag<Block> blockTag = blockTagsMap.get(block);
        copy(blockTag, ItemTags.makeWrapperTag(forgeLoc(blockTag.getName().getPath()).toString()));
      }

      for (Item item : itemTagsMap.keySet()) {
        INamedTag<Item> tag = itemTagsMap.get(item);
        getOrCreateBuilder(tag).add(item);
      }

      for (String subGroup : subgroupTagsMap.keySet()) {
        INamedTag<Item> tag = subgroupTagsMap.get(subGroup);
        for (INamedTag<Item> itemTag : subgroupItemsMap.get(subGroup))
          getOrCreateBuilder(tag).addTag(itemTag);
      }
    }
  }
}
