package customblockfabricator;

import customblockfabricator.block.ModBlocks;
import customblockfabricator.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
  modid = CustomBlockFabricator.MOD_ID,
  name = CustomBlockFabricator.NAME,
  version = CustomBlockFabricator.VERSION
)
public class CustomBlockFabricator {
  public static final String MOD_ID = "customblockfabricator";
  public static final String NAME = "Custom Block Fabricator";
  public static final String VERSION = "@VERSION@";

  @SidedProxy(
    serverSide = "customblockfabricator.proxy.CommonProxy",
    clientSide = "customblockfabricator.proxy.ClientProxy")
  public static CommonProxy proxy;

  public CustomBlockFabricator() {
  }

  @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerRenderers();
	}

  @EventHandler
	public void init(FMLInitializationEvent event) {
    ModBlocks.registerItemAndBlockColors();
	}

  @Mod.EventBusSubscriber
  public static class RegistrationHandler {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
      ModBlocks.register(event.getRegistry());
    } 

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
      ModBlocks.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
      ModBlocks.registerModels();
    }
  }
}
