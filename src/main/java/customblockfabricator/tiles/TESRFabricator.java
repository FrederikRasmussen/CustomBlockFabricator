package customblockfabricator.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class TESRFabricator extends TileEntitySpecialRenderer<FabricatorTile> {
  @Override
  public void render(FabricatorTile tile, double x, double y, double z, float partialTicks, int destroyStage,
      float alpha) {
    GlStateManager.enableRescaleNormal();
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
    GlStateManager.enableBlend();
    RenderHelper.enableStandardItemLighting();
    GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 0.5, y + 0.2, z + 0.5);
    GlStateManager.rotate((tile.getWorld().getTotalWorldTime() + partialTicks) * 2, 0, 1, 0);
    GlStateManager.scale(1.5, 1.5, 1.5);
    ItemStack stack = tile.getItemStack();
    IBakedModel model =
        Minecraft
            .getMinecraft()
            .getRenderItem()
            .getItemModelWithOverrides(stack, tile.getWorld(), null);
    model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
    GlStateManager.popMatrix();
    GlStateManager.disableRescaleNormal();
    GlStateManager.disableBlend();
  }
}