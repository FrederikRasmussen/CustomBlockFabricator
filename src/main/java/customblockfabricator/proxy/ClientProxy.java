package customblockfabricator.proxy;

import customblockfabricator.CustomBlockFabricator;
import customblockfabricator.tiles.FabricatorTile;
import customblockfabricator.tiles.TESRFabricator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Objects;

import static customblockfabricator.block.ModBlocks.fabricator;

public class ClientProxy extends CommonProxy {
  @Override
  public void registerItemRenderer(Item item, int meta, String id) {
    ModelLoader.setCustomModelResourceLocation(
      item,
      meta,
      new ModelResourceLocation(
        CustomBlockFabricator.MOD_ID + ":" + id,
        "inventory"));
  }

  @Override
  public void registerRenderers() {
    ClientRegistry.bindTileEntitySpecialRenderer(FabricatorTile.class, new TESRFabricator());
  }

  @Override
  public void registerItemAndBlockColors() {
    Minecraft minecraft = Minecraft.getMinecraft();
    BlockColors blockColors = minecraft.getBlockColors();
    blockColors.registerBlockColorHandler(ColoredBlockBlockColor.INSTANCE, fabricator);
    ItemColors itemColors = minecraft.getItemColors();
    itemColors.registerItemColorHandler(ColoredItemItemColor.INSTANCE, fabricator);
  }

  @SideOnly(Side.CLIENT)
  private static class ColoredBlockBlockColor implements IBlockColor
  {
    public static final ColoredBlockBlockColor INSTANCE = new ColoredBlockBlockColor();

    private ColoredBlockBlockColor() {

    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess world,
            @Nullable BlockPos pos,
            int tintIndex)
    {
      TileEntity tile;
      if (world instanceof ChunkCache) {
        ChunkCache chunkCache = (ChunkCache) world;
        tile = chunkCache.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK);
      } else {
        tile = world.getTileEntity(pos);
      }
      if (tile instanceof FabricatorTile) {
        return ((FabricatorTile) tile).getColour();
      } else {
        return 0xffffff;
      }
    }
  }

  @SideOnly(Side.CLIENT)
  private static class ColoredItemItemColor implements IItemColor
  {
    public static final ColoredItemItemColor INSTANCE = new ColoredItemItemColor();

    private ColoredItemItemColor() {

    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
      NBTTagCompound compound =  stack.getSubCompound("BlockEntityTag");
      if (Objects.nonNull(compound)) {
        return compound.getInteger("colour");
      } else {
        return 0xffffff;
      }
    }
  }
}
