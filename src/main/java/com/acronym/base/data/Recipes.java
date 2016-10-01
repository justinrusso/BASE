package com.acronym.base.data;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.blocks.BaseBlocks;
import com.acronym.base.items.BaseItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Map;

public class Recipes {

    public static void preInit() {
        MaterialRegistry.registerNativeMaterial(0, Materials.WOOD);
        MaterialRegistry.registerNativeMaterial(1, Materials.STONE);
        MaterialRegistry.registerNativeMaterial(2, Materials.IRON);
        MaterialRegistry.registerNativeMaterial(3, Materials.GOLD);
        MaterialRegistry.registerNativeMaterial(4, Materials.DIAMOND);

        MaterialRegistry.registerNativeMaterial(5, Materials.COPPER);
        MaterialRegistry.registerNativeMaterial(6, Materials.TIN);
        MaterialRegistry.registerNativeMaterial(7, Materials.LEAD);
        MaterialRegistry.registerNativeMaterial(8, Materials.SILVER);
        MaterialRegistry.registerNativeMaterial(9, Materials.ELECTRUM);
    }

    public static void preInitLate(){
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : MaterialRegistry.getMaterials().entrySet()) {
            MaterialType mat = ent.getValue();
            for (MaterialType.EnumPartType type : mat.getTypes()) {
                switch (type) {
                    case INGOT:
                        OreDictionary.registerOre("ingot" + mat.getName(), new ItemStack(BaseItems.INGOT, 1, ent.getKey().getRight()));
                        break;
                    case DUST:
                        OreDictionary.registerOre("dust" + mat.getName(), new ItemStack(BaseItems.DUST, 1, ent.getKey().getRight()));
                        break;
                    case GEAR:
                        OreDictionary.registerOre("gear" + mat.getName(), new ItemStack(BaseItems.GEAR, 1, ent.getKey().getRight()));
                        break;
                    case NUGGET:
                        OreDictionary.registerOre("nugget" + mat.getName(), new ItemStack(BaseItems.NUGGET, 1, ent.getKey().getRight()));
                        break;
                    case PLATE:
                        OreDictionary.registerOre("plate" + mat.getName(), new ItemStack(BaseItems.PLATE, 1, ent.getKey().getRight()));
                        break;
                    //TODO add ORES and BLOCKS
                }
            }
        }
    }

    public static void init() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.WOOD)), " x ", "x x", " x ", 'x', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.STONE)), " x ", "xsx", " x ", 'x', "stone", 's', "gearWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.IRON)), " x ", "xsx", " x ", 'x', "ingotIron", 's', "gearStone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.GOLD)), " x ", "xsx", " x ", 'x', "ingotGold", 's', "gearIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), " x ", "xsx", " x ", 'x', "gemDiamond", 's', "gearGold"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), " x ", "xsx", " x ", 'x', "gearIron", 's', "gearGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), " x ", "xsx", " x ", 'x', "gearIron", 's', "gearGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), " x ", "xsx", " x ", 'x', "gearIron", 's', "gearGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), " x ", "xsx", " x ", 'x', "gearIron", 's', "gearGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), " x ", "xsx", " x ", 'x', "gearIron", 's', "gearGold"));


        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.IRON)), "ingotIron"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), "gemDiamond"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), "ingotCopper"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.TIN)), "ingotTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), "ingotLead"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), "ingotSilver"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), "ingotElectrum"));


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.IRON_INGOT), "xxx", "xxx", "xxx", 'x', "nuggetIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.DIAMOND), "xxx", "xxx", "xxx", 'x', "nuggetDiamond"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), "xxx", "xxx", "xxx", 'x', "nuggetCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), "xxx", "xxx", "xxx", 'x', "nuggetTin"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), "xxx", "xxx", "xxx", 'x', "nuggetLead"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), "xxx", "xxx", "xxx", 'x', "nuggetSilver"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), "xxx", "xxx", "xxx", 'x', "nuggetElectrum"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.COPPER)), "xxx", "xxx", "xxx", 'x', "ingotCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.TIN)), "xxx", "xxx", "xxx", 'x', "ingotTin"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.LEAD)), "xxx", "xxx", "xxx", 'x', "ingotLead"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.SILVER)), "xxx", "xxx", "xxx", 'x', "ingotSilver"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.ELECTRUM)), "xxx", "xxx", "xxx", 'x', "ingotElectrum"));

        GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.COPPER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.TIN)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.LEAD)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.SILVER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.ELECTRUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), 50);

        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.IRON)), new ItemStack(Items.IRON_INGOT), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.GOLD)), new ItemStack(Items.GOLD_INGOT), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), new ItemStack(Items.DIAMOND), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.COPPER)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), 50);
        GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), 50);

    }

    public static void postInit() {
    }
}
