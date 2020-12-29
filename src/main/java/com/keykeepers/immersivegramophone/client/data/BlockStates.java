package com.keykeepers.immersivegramophone.client.data;

import com.keykeepers.immersivegramophone.ImmersiveGramophone;
import com.keykeepers.immersivegramophone.common.AluminumBronze;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class BlockStates extends BlockStateProvider {
  public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, ImmersiveGramophone.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    // Aluminum Brass
    simpleBlock(AluminumBronze.storageBlock().getBlock());
    slabBlock(AluminumBronze.storageSlab(), AluminumBronze.storageBlock().getRegistryName(),
        slabTexture(AluminumBronze.storageBlock()));
    simpleBlock(AluminumBronze.sheetmetalBlock().getBlock());
    slabBlock(AluminumBronze.sheetmetalSlab(), AluminumBronze.sheetmetalBlock().getRegistryName(),
        slabTexture(AluminumBronze.sheetmetalBlock()));
  }

  private ResourceLocation slabTexture(Block block) {
    String name = Objects.requireNonNull(block.getRegistryName()).getPath();
    return modLoc("block/" + name);
  }
}
