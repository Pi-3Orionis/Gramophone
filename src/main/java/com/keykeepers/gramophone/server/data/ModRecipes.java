package com.keykeepers.gramophone.server.data;

import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.common.AluminumBronze;
import com.keykeepers.gramophone.common.data.TagsProviders;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;

public class ModRecipes extends Recipes {
  public ModRecipes(DataGenerator generatorIn) {
    super(generatorIn, GramophoneMod.MODID);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    // Aluminum Bronze
    slabRecipes(consumer, AluminumBronze.storageBlockItem(), AluminumBronze.storageSlabItem());
    slabRecipes(consumer, AluminumBronze.sheetmetalBlockItem(), AluminumBronze.sheetmetalSlabItem());
    packingRecipes(consumer, AluminumBronze.ingot(), AluminumBronze.nugget(),
        TagsProviders.itemTagsMap.get(AluminumBronze.nugget()));
    packingRecipes(consumer, AluminumBronze.storageBlockItem(), AluminumBronze.ingot(),
        TagsProviders.itemTagsMap.get(AluminumBronze.ingot()));
  }
}
