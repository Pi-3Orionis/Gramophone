package com.keykeepers.immersivegramophone.client.data;

import com.keykeepers.immersivegramophone.ImmersiveGramophone;
import com.keykeepers.immersivegramophone.common.AluminumBrass;
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
    simpleBlock(AluminumBrass.storageBlock().getBlock());
    slabBlock(AluminumBrass.storageSlab(), AluminumBrass.storageBlock().getRegistryName(),
        slabTexture(AluminumBrass.storageBlock()));
    simpleBlock(AluminumBrass.sheetmetalBlock().getBlock());
    slabBlock(AluminumBrass.sheetmetalSlab(), AluminumBrass.sheetmetalBlock().getRegistryName(),
        slabTexture(AluminumBrass.sheetmetalBlock()));
  }

  private ResourceLocation slabTexture(Block block) {
    String name = Objects.requireNonNull(block.getRegistryName()).getPath();
    return modLoc("block/" + name);
  }
}
