package customblockfabricator.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
  public static Fabricator fabricator = new Fabricator();

  public static void register(IForgeRegistry<Block> registry) {
    registry.registerAll(fabricator);
    GameRegistry.registerTileEntity(fabricator.getTileEntityClass(), fabricator.getRegistryName().toString());
  }

  public static void registerItemBlocks(IForgeRegistry<Item> registry) {
    registry.registerAll(fabricator.createItemBlock());
  }

  public static void registerModels() {
    fabricator.registerItemModel(Item.getItemFromBlock(fabricator));
  }
}
