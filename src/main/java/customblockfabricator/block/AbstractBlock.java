package customblockfabricator.block;

import customblockfabricator.CustomBlockFabricator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class AbstractBlock extends Block {
  protected String name;

  public AbstractBlock(Material material, String name) {
    super(material);

    this.name = name;

    setUnlocalizedName(name);
    setRegistryName(name);
  }

  public void registerItemModel(Item itemBlock) {
    CustomBlockFabricator.proxy.registerItemRenderer(itemBlock, 0, name);
  }

  public Item createItemBlock() {
    return new ItemBlock(this).setRegistryName(getRegistryName());
  }

  @Override
  public AbstractBlock setCreativeTab(CreativeTabs tab) {
    super.setCreativeTab(tab);
    return this;
  }
}
