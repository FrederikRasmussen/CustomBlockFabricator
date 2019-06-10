package customblockfabricator.proxy;

import customblockfabricator.CustomBlockFabricator;
import customblockfabricator.tiles.FabricatorTile;
import customblockfabricator.tiles.TESRFabricator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

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
}
