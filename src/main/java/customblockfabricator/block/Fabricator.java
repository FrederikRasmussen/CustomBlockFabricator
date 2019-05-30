package customblockfabricator.block;

import java.util.Random;

import javax.annotation.Nullable;

import customblockfabricator.tiles.AbstractTileEntity;
import customblockfabricator.tiles.FabricatorTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class Fabricator extends AbstractTileEntity<FabricatorTile> {
  public Fabricator(String name) {
    super(Material.ROCK, name);
    setHardness(3.5f);
    setResistance(17.5f);
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote) {
      FabricatorTile tile = getTileEntity(world, pos);
      IItemHandler itemHandler =
        tile.getCapability(
          CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
          null);
      player.sendMessage(
        new TextComponentString("Cobblestone: " + itemHandler.getStackInSlot(0).getCount() + "(" + itemHandler.getStackInSlot(0) + ")"));
    }
    return true;
  }

  @Override
  public Fabricator setCreativeTab(CreativeTabs tab) {
    super.setCreativeTab(tab);
    return this;
  }

  @Override
  public Class<FabricatorTile> getTileEntityClass() {
    return FabricatorTile.class;
  }

  @Nullable
  @Override
  public FabricatorTile createTileEntity(World world, IBlockState state) {
    return new FabricatorTile();
  }
}
