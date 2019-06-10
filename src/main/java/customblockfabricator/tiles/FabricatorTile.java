package customblockfabricator.tiles;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class FabricatorTile extends TileEntity implements ITickable {
  private static final String INVENTORY_TAG = "inventory";
  private ItemStackHandler inventory = new ItemStackHandler(1);

  private static final String COUNTER_TAG = "counter";
  private int counter = 0;

  private static final String BLOCK_STRING_TAG = "block_string";
  private String blockAsString = "minecraft:stone:5";

  private static final String BLOCK_COUNT_TAG = "block_count";
  private int blocksPerFabrication = 1;

  private static final String TICKS_REQUIRED_TAG = "ticks_required";
  private int ticksPerFabrication = 40;

  private static final String COLOUR_TAG = "colour";
  private int colour = 0x00ff6a;

  private transient Block block;
  private transient int meta;

  public FabricatorTile() {
    super();
    setBlockAndMeta();
  }

  private void setBlockAndMeta() {
    String[] splitName = blockAsString.split(":");
    if (splitName.length == 2) {
      meta = 0;
    } else if (splitName.length == 3) {
      meta = Integer.parseInt(splitName[2]);
    } else {
      throw new RuntimeException("Bad blockname in config: " + blockAsString);
    }
    block = Block.getBlockFromName(splitName[0] + ":" + splitName[1]);
  }

  @Override
  public void update() {
    counter++;
    if (counter >= ticksPerFabrication) {
      counter = 0;
      inventory.insertItem(0, new ItemStack(block, blocksPerFabrication, meta), false);
      markDirty();
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.setTag(INVENTORY_TAG, inventory.serializeNBT());
    compound.setInteger(COUNTER_TAG, counter);
    compound.setString(BLOCK_STRING_TAG, blockAsString);
    compound.setInteger(BLOCK_COUNT_TAG, blocksPerFabrication);
    compound.setInteger(TICKS_REQUIRED_TAG, ticksPerFabrication);
    compound.setInteger(COLOUR_TAG, colour);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    inventory.deserializeNBT(compound.getCompoundTag(INVENTORY_TAG));
    counter = compound.getInteger(COUNTER_TAG);
    blockAsString = compound.getString(BLOCK_STRING_TAG);
    blocksPerFabrication = compound.getInteger(BLOCK_COUNT_TAG);
    ticksPerFabrication = compound.getInteger(TICKS_REQUIRED_TAG);
    colour = compound.getInteger(COLOUR_TAG);
    setBlockAndMeta();
    super.readFromNBT(compound);
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    NBTTagCompound compound = super.getUpdateTag();
    compound.setString(BLOCK_STRING_TAG, blockAsString);
    compound.setInteger(COLOUR_TAG, colour);
    return compound;
  }

  @Override
  public void handleUpdateTag(NBTTagCompound compound) {
    colour = compound.getInteger(COLOUR_TAG);
    blockAsString = compound.getString(BLOCK_STRING_TAG);
    setBlockAndMeta();
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(pos, 4, getUpdateTag());
  }

  @Override
  public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity dataPacket) {
    handleUpdateTag(dataPacket.getNbtCompound());
  }

  @Override
  public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
    return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
  }

  @Nullable
  @Override
  public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
    return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory
        : super.getCapability(capability, facing);
  }

  public int getColour() {
    return colour;
  }

  public ItemStack getItemStack() {
    return new ItemStack(block, 1, meta);
  }
}
