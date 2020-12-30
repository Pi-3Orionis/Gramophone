package com.keykeepers.gramophone.common.data;

import com.keykeepers.gramophone.GramophoneMod;
import com.keykeepers.gramophone.client.data.BlockStates;
import com.keykeepers.gramophone.client.data.ItemModels;
import com.keykeepers.gramophone.server.data.ModRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = GramophoneMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGatherer {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();

    TagsProviders tagsProviders = tagsProviders(generator, fileHelper);
    generator.addProvider(tagsProviders.blockTagsProvider);
    generator.addProvider(tagsProviders.itemTagsProvider);

    if (event.includeServer()) {
      generator.addProvider(new ModRecipes(generator));
    }

    if (event.includeClient()) {
      generator.addProvider(blockStateProvider(generator, fileHelper));
      generator.addProvider(itemModelProvider(generator, fileHelper));
    }
  }

  protected static TagsProviders tagsProviders(DataGenerator generator, ExistingFileHelper fileHelper) {
    return new TagsProviders(generator, GramophoneMod.MODID, fileHelper);
  }

  protected static BlockStateProvider blockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
    return new BlockStates(generator, fileHelper);
  }

  protected static ItemModelProvider itemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
    return new ItemModels(generator, fileHelper);
  }
}
