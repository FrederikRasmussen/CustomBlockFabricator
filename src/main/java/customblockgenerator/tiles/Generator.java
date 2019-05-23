package customblockgenerator.tiles;

import slimeknights.mantle.tileentity.TileInventory;

public class Generator extends TileInventory {
  public Generator(String name, int maxStackSize) {
    super(name, 1, maxStackSize);
  }

}