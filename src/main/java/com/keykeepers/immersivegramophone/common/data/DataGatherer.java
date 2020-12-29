package com.keykeepers.immersivegramophone.common.data;

import com.keykeepers.immersivegramophone.ImmersiveGramophone;
import com.keykeepers.immersivegramophone.client.data.BlockStates;
import com.keykeepers.immersivegramophone.client.data.ItemModels;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = ImmersiveGramophone.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGatherer {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    net.minecraft.data.DataGenerator generator = event.getGenerator();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();

    if (event.includeClient()) {
      generator.addProvider(new BlockStates(generator, fileHelper));
      generator.addProvider(new ItemModels(generator, fileHelper));
    }
  }
}
