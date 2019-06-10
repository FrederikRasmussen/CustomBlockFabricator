import crafttweaker.item.IIngredient;

<customblockfabricator:fabricator>.addTooltip("Should not be craftable. Ignore in JEI.");

function addFabricatorToJEI(
    ticks_required as int,    // Ticks per fabrication packet
    colour as int,            // Colour mask of the block (use 0xHEX_VALUE such as 0xDEDE00)
    block_count as int,       // Size of fabrication packet
    block_string as string,   // Block that is fabricated
    name as string,           // Name of this fabricator in JEI
    recipeName as string,     // Recipe name for Minecraft internals
    inputs as IIngredient[][] // Recipe array for the fabricator
) {
  val blockItem = <customblockfabricator:fabricator>.withTag(
    {
      BlockEntityTag: {
        ticks_required: ticks_required,
        colour: colour,
        block_count: block_count,
        counter: 0,
        block_string: block_string
      },
      display: {
        Name: name,
      }
    }
  );
  blockItem.addTooltip("Generates " + block_count + " blocks every " + ticks_required + " ticks.");
  mods.jei.JEI.addItem(blockItem);
  recipes.addShaped(recipeName, blockItem, inputs);
}

addFabricatorToJEI(
  40, 0xDEDE00, 1, "minecraft:cobblestone",
  "Gold Plated Cobblestone Generator",
  "gold_cobble_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:cobblestone>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, "minecraft:stone:5",
  "Gold Plated Andesite Generator",
  "gold_andesite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:5>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, "minecraft:stone:1",
  "Gold Plated Granite Generator",
  "gold_granite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:1>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, "minecraft:stone:3",
  "Gold Plated Diorite Generator",
  "gold_diorite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:3>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);