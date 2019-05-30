package customblockfabricator.tiles;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class FabricatorTile extends TileEntity implements ITickable{
  private static final String INVENTORY_TAG = "inventory";
  private ItemStackHandler inventory = new ItemStackHandler(1);

  public FabricatorTile() {
    super();
  }

  @Override
  public void update() {
    inventory.insertItem(0, new ItemStack(Blocks.COBBLESTONE), false);
    markDirty();
  }

  /*
  

  @Override
  public void updateTick(
      World world, BlockPos position, IBlockState state, Random random) {
    if (!world.isRemote) {
      FabricatorTile tile = getTileEntity(world, position);
      IItemHandler itemHandler =
          tile.getCapability(
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
            null);
      itemHandler.insertItem(0, new ItemStack(Blocks.COBBLESTONE), false);
      tile.markDirty();
    }
  }

  */

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    // compound.setPrimitive(key, value);
    compound.setTag(INVENTORY_TAG, inventory.serializeNBT());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    // primitive = compound.getPrimitive(key);
    inventory.deserializeNBT(compound.getCompoundTag(INVENTORY_TAG));
    super.readFromNBT(compound);
  }

  @Override
  public boolean hasCapability(
      Capability<?> capability, @Nullable EnumFacing facing) {
    return 
      capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ||
        super.hasCapability(capability, facing);
  }

  @Nullable
  @Override
  public <T> T getCapability(
      Capability<T> capability, @Nullable EnumFacing facing) {
    return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
      ? (T)inventory : super.getCapability(capability, facing);
  }
}
