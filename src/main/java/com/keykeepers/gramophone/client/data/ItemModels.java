package com.keykeepers.gramophone.client.data;

import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.common.AluminumBronze;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ItemModels extends ItemModelProvider {
  private static ModelFile generatedItem;

  public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, GramophoneMod.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    // BLOCK ITEMS
    // Aluminum Brass
    blockItem(AluminumBronze.storageBlock());
    blockItem(AluminumBronze.storageSlab());
    blockItem(AluminumBronze.sheetmetalBlock());
    blockItem(AluminumBronze.sheetmetalSlab());

    if (generatedItem == null)
      generatedItem = getExistingFile(mcLoc("item/generated"));

    // SIMPLE ITEMS
    // Aluminum Brass
    simpleItem(AluminumBronze.ingot());
    simpleItem(AluminumBronze.nugget());
    simpleItem(AluminumBronze.dust());
    simpleItem(AluminumBronze.plate());
    simpleItem(AluminumBronze.rod());
  }

  private <B extends Block> void blockItem(B block) {
    String name = Objects.requireNonNull(block.getRegistryName()).getPath();
    withExistingParent(name, modLoc("block/" + name));
  }

  private void simpleItem(Item item) {
    String name = Objects.requireNonNull(item.getRegistryName()).getPath();
    getBuilder(Objects.requireNonNull(item.getRegistryName()).getPath())
        .parent(generatedItem)
        .texture("layer0", "item/" + name);
  }
}
