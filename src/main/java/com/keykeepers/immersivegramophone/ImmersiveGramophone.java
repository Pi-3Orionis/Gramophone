package com.keykeepers.immersivegramophone;

import blusunrize.immersiveengineering.api.wires.WireType;
import blusunrize.immersiveengineering.common.items.IEItems;
import com.keykeepers.immersivegramophone.common.AluminumBrass;
import com.keykeepers.immersivegramophone.common.Registry;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(ImmersiveGramophone.MODID)
public class ImmersiveGramophone {
  public static final String MODID = "immersivegramophone";
  public static final String MODNAME = "Immersive Gramophone";
  public static final String VERSION = "${version}";
  public static final ItemGroup itemGroup;
  public static final Registry registry;

  private static final Logger logger = LogManager.getLogger(MODID);

  static {
    itemGroup = new ItemGroup(MODID) {
      @Override
      public ItemStack createIcon() { return new ItemStack(IEItems.Misc.wireCoils.get(WireType.REDSTONE)); }
    };
    registry = new Registry(MODID, itemGroup);
  }

  public ImmersiveGramophone() {
    registry.registerBus(FMLJavaModLoadingContext.get().getModEventBus());
    AluminumBrass.init();

    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    // Register the enqueueIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    // Register the doClientStuff method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event)
  {
    // some preinit code
    logger.info("HELLO FROM PREINIT");
    logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
  }

  private void doClientStuff(final FMLClientSetupEvent event) {
    // do something that can only be done on the client
    logger.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
  }

  private void enqueueIMC(final InterModEnqueueEvent event)
  {
    // some example code to dispatch IMC to another mod
    InterModComms.sendTo("examplemod", "helloworld", () -> { logger.info("Hello world from the MDK"); return "Hello world";});
  }

  private void processIMC(final InterModProcessEvent event)
  {
    // some example code to receive and process InterModComms from other mods
    logger.info("Got IMC {}", event.getIMCStream().
        map(m->m.getMessageSupplier().get()).
        collect(Collectors.toList()));
  }
  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(FMLServerStartingEvent event) {
    // do something when the server starts
    logger.info("HELLO from server starting");
  }
}
