import crafttweaker.item.IIngredient;

function addFabricatorToJEI(
    ticks_required as int,
    colour as int,
    block_count as int,
    counter as int,
    block_string as string,
    name as string,
    recipeName as string,
    inputs as IIngredient[][]
) {
  val blockItem = <customblockfabricator:fabricator>.withTag(
    {
      BlockEntityTag: {
        ticks_required: ticks_required,
        colour: colour,
        block_count: block_count,
        counter: counter,
        block_string: block_string
      },
      display: {
        Name: name,
      }
    }
  );
  mods.jei.JEI.addItem(blockItem);
  recipes.addShaped(recipeName, blockItem, inputs);
}

addFabricatorToJEI(
  40, 0xDEDE00, 1, 0, "minecraft:cobblestone",
  "Gold Plated Cobblestone Generator",
  "gold_cobble_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:cobblestone>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, 0, "minecraft:stone:5",
  "Gold Plated Andesite Generator",
  "gold_andesite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:5>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, 0, "minecraft:stone:1",
  "Gold Plated Granite Generator",
  "gold_granite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:1>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);

addFabricatorToJEI(
  40, 0xDEDE00, 1, 0, "minecraft:stone:3",
  "Gold Plated Diorite Generator",
  "gold_diorite_fabricator",
  [
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
    [<minecraft:water_bucket>, <minecraft:stone:3>, <minecraft:lava_bucket>],
    [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]
  ]
);