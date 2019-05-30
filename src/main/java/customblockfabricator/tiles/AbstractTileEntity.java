package customblockfabricator.tiles;

import javax.annotation.Nullable;

import customblockfabricator.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class AbstractTileEntity<T extends TileEntity> extends AbstractBlock {
  public AbstractTileEntity(Material material, String name) {
    super(material, name);
  }

  public abstract Class<T> getTileEntityClass();

  public T getTileEntity(IBlockAccess world, BlockPos pos) {
    return (T)world.getTileEntity(pos);
  }

  @Override
  public boolean hasTileEntity(IBlockState state) {
    return true;
  }

  @Nullable
  @Override
  public abstract T createTileEntity(World world, IBlockState state);
}
