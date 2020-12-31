package com.keykeepers.gramophone.server.data;

import blusunrize.immersiveengineering.api.IETags;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.api.crafting.builders.AlloyRecipeBuilder;
import blusunrize.immersiveengineering.api.crafting.builders.ArcFurnaceRecipeBuilder;
import blusunrize.immersiveengineering.api.crafting.builders.CrusherRecipeBuilder;
import blusunrize.immersiveengineering.api.crafting.builders.MetalPressRecipeBuilder;
import blusunrize.immersiveengineering.common.blocks.EnumMetals;
import blusunrize.immersiveengineering.common.items.IEItems;
import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.common.AluminumBronze;
import com.keykeepers.gramophone.common.data.TagsProviders;
import com.keykeepers.gramophone.common.items.ToneArm;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipes extends Recipes {
  public ModRecipes(DataGenerator generatorIn) {
    super(generatorIn, GramophoneMod.MODID);
  }

  @Override
  @ParametersAreNonnullByDefault
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    aluminumBronze(consumer);

    // Tone Arm
    ShapedRecipeBuilder.shapedRecipe(ToneArm.item, 9)
        .key('d', Tags.Items.GEMS_DIAMOND)
        .key('p', Tags.Items.GLASS_PANES)
        .key('s', TagsProviders.subgroupTagsMap.get("sheetmetals/copper_alloy"))
        .patternLine("dps")
        .addCriterion("has_diamond", hasItem(Tags.Items.GEMS_DIAMOND))
        .build(consumer, modLoc(pathName(ToneArm.item)));
  }

  private void aluminumBronze(Consumer<IFinishedRecipe> consumer) {
    String name = AluminumBronze.name();
    Item ingot = AluminumBronze.ingot();
    Item nugget = AluminumBronze.nugget();
    Item dust = AluminumBronze.dust();
    Item plate = AluminumBronze.plate();
    Item rod = AluminumBronze.rod();

    slabRecipes(consumer, AluminumBronze.storageBlockItem(), AluminumBronze.storageSlabItem());
    slabRecipes(consumer, AluminumBronze.sheetmetalBlockItem(), AluminumBronze.sheetmetalSlabItem());

    packingRecipes(consumer, ingot, nugget,
        itemTag(nugget));
    packingRecipes(consumer, AluminumBronze.storageBlockItem(), AluminumBronze.ingot(),
        itemTag(ingot));

    ShapedRecipeBuilder.shapedRecipe(rod, 4)
        .key('i', itemTag(ingot))
        .patternLine("i")
        .patternLine("i")
        .addCriterion("has_" + pathName(ingot), hasItem(ingot))
        .build(consumer, modLoc(pathName(rod)));

    ShapelessRecipeBuilder.shapelessRecipe(plate)
        .addIngredient(itemTag(ingot))
        .addIngredient(IEItems.Tools.hammer)
        .addCriterion("has_" + pathName(ingot), hasItem(itemTag(ingot)))
        .build(consumer, modLoc(pathName(plate) + "_hammering"));

    ShapedRecipeBuilder.shapedRecipe(AluminumBronze.sheetmetalBlockItem(), 4)
        .key('p', itemTag(plate))
        .patternLine(" p ")
        .patternLine("p p")
        .patternLine(" p ")
        .addCriterion("has_" + pathName(plate), hasItem(plate))
        .build(consumer, modLoc("sheetmetal_" + name));

    ITag<Item> copperDust = IETags.getTagsFor(EnumMetals.COPPER).dust;
    ITag<Item> alumDust = IETags.getTagsFor(EnumMetals.ALUMINUM).dust;
    ShapelessRecipeBuilder.shapelessRecipe(dust, 4)
        .addIngredient(Ingredient.fromTag(copperDust), 3)
        .addIngredient(Ingredient.fromTag(alumDust))
        .addCriterion("has_dust_copper", hasItem(copperDust))
        .addCriterion("has_dust_aluminum", hasItem(alumDust))
        .build(consumer);

    smeltingRecipes(consumer, dust, ingot, 0.0f);

    ITag<Item> copperIngot = IETags.getTagsFor(EnumMetals.COPPER).ingot;
    ITag<Item> alumIngot = IETags.getTagsFor(EnumMetals.ALUMINUM).ingot;
    AlloyRecipeBuilder.builder(ingot)
        .addInput(new IngredientWithSize(Ingredient.fromTag(copperIngot), 3))
        .addInput(alumIngot)
        .build(consumer, modLoc("alloysmelter/" + pathName(ingot)));

    ArcFurnaceRecipeBuilder.builder(ingot)
        .addIngredient("input", dust)
        .setTime(100)
        .setEnergy(51200)
        .build(consumer, modLoc("arcfurnace/" + pathName(dust)));

    ArcFurnaceRecipeBuilder.builder(ingot)
        .addIngredient("input", new IngredientWithSize(Ingredient.fromTag(copperIngot), 3))
        .addInput(alumIngot)
        .setTime(100)
        .setEnergy(51200)
        .build(consumer, modLoc("arcfurnace/alloy_" + name));

    CrusherRecipeBuilder.builder(dust)
        .addInput(ingot)
        .setEnergy(3000)
        .build(consumer, modLoc("crusher/" + pathName(ingot)));

    MetalPressRecipeBuilder.builder(IEItems.Molds.moldPlate, plate)
        .addInput(ingot)
        .setEnergy(2400)
        .build(consumer, modLoc("metalpress/" + pathName(plate)));

    MetalPressRecipeBuilder.builder(IEItems.Molds.moldRod, itemTag(rod), 2)
        .addInput(ingot)
        .setEnergy(2400)
        .build(consumer, modLoc("metalpress/" + pathName(rod)));
  }

  private String pathName(Item item) {
    return Objects.requireNonNull(item.getRegistryName()).getPath();
  }
}
