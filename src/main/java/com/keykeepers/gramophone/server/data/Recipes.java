package com.keykeepers.gramophone.server.data;

import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.function.Consumer;

// TODO Mod agnostic

public class Recipes extends RecipeProvider {
  protected final String modId;

  public Recipes(DataGenerator generatorIn, String modId) {
    super(generatorIn);
    this.modId = modId;
  }

  protected void packingRecipes(Consumer<IFinishedRecipe> consumer, Item storageItem, Item componentItem,
                             ITag.INamedTag<Item> componentTag) {
    ShapedRecipeBuilder.shapedRecipe(storageItem)
        .key('i', componentItem)
        .key('t', componentTag)
        .patternLine("ttt")
        .patternLine("tit") // lulz ^_^
        .patternLine("ttt")
        .addCriterion("has_" + itemPath(componentItem), hasItem(componentItem))
        .build(consumer, recipeName(storageItem, componentItem));

    ShapelessRecipeBuilder.shapelessRecipe(componentItem, 9)
        .addIngredient(storageItem)
        .addCriterion("has_" + itemPath(storageItem), hasItem(storageItem))
        .build(consumer, recipeName(componentItem, storageItem));
  }

  protected void slabRecipes(Consumer<IFinishedRecipe> consumer, Item blockItem, Item slabItem) {
    ShapedRecipeBuilder.shapedRecipe(slabItem, 6)
        .key('b', blockItem)
        .patternLine("bbb")
        .addCriterion("has_" + itemPath(blockItem), hasItem(blockItem))
        .build(consumer, recipeName(slabItem, blockItem));
    ShapedRecipeBuilder.shapedRecipe(blockItem)
        .key('s', slabItem)
        .patternLine("s")
        .patternLine("s")
        .addCriterion("has_" + itemPath(blockItem), hasItem(blockItem))
        .build(consumer, recipeName(blockItem, slabItem));
  }

  protected String itemPath(IItemProvider item) {
    return Objects.requireNonNull(item.asItem().getRegistryName()).getPath();
  }

  protected ResourceLocation recipeName(IItemProvider output, IItemProvider input) {
    return new ResourceLocation(modId, itemPath(output) + "_from_" + itemPath(input));
  }
}
