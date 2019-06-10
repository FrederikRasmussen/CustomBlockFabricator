package customlavagenerator;

import customlavagenerator.events.LavaFlowEvents;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Configuration {
  @SubscribeEvent
  public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if (CustomLavaGenerator.MOD_ID.equals(event.getModID())) {
      ConfigManager.sync(CustomLavaGenerator.MOD_ID, Config.Type.INSTANCE);
      updateConfig();
    }
  }

  public static void updateConfig() {
    LavaFlowEvents.updateConfig(CLGConfiguration.blocks);
  }

  @Config(modid = CustomLavaGenerator.MOD_ID)
  public static class CLGConfiguration {
    @Comment({"List of blocks to create when water and lava meet. First block happens at water level 0, next at level 1 etc."})
    public static String[] blocks = LavaFlowEvents.DEFAULT_BLOCK_CONFIG;
  }
}