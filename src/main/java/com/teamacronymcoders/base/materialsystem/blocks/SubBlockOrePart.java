package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.materialsystem.parttype.OrePartType.DROP_DATA_NAME;

public class SubBlockOrePart extends SubBlockPart {
    private ItemStack itemStackToDrop = null;
    private String itemDrop;
    private IBaseMod mod;
    private ResourceLocation variantLocation;

    public SubBlockOrePart(MaterialPart materialPart, ResourceLocation variantLocation, MaterialUser materialUser) {
        super(materialPart, MaterialSystem.materialCreativeTab);
        MaterialPartData data = materialPart.getData();
        this.mod = materialUser.getMod();
        if (data.containsDataPiece(DROP_DATA_NAME)) {
            itemDrop = data.getDataPiece(DROP_DATA_NAME);
        }
        this.variantLocation = variantLocation;
    }

    @Override
    public void getDrops(int fortune, List<ItemStack> itemStacks) {
        if (itemStackToDrop == null) {
            if (itemDrop != null && !itemDrop.isEmpty()) {
                String[] itemDropArray = itemDrop.split(":");
                String itemString = itemDropArray[0];

                if (itemString.equalsIgnoreCase("oredict")) {
                    itemStackToDrop = OreDictUtils.getPreferredItemStack(itemDropArray[1]);
                } else {
                    int meta = 0;
                    if (itemDropArray.length > 1) {
                        itemString += ":" + itemDropArray[1];
                        if (itemDropArray.length > 2) {
                            meta = Integer.parseInt(itemDropArray[2]);
                        }
                    }
                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
                    if (item != null) {
                        itemStackToDrop = new ItemStack(item, 1, meta);
                    } else {
                        this.mod.getLogger().error("Could not find Item for name: " + itemString);
                    }
                }
            } else {
                this.itemStackToDrop = this.getItemStack();
            }
        }
        if (ItemStackUtils.isValid(this.itemStackToDrop)) {
            itemStacks.add(itemStackToDrop.copy());
        } else {
            mod.getLogger().fatal("Couldn't drop null ItemStack for " + getMaterialPart().getLocalizedName());
        }
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(this.mod.getID(), "materials/" + this.getUnLocalizedName());
    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("ore_block_state");
        Map<String, String> replacements = Maps.newHashMap();

        String unlocalizedName = this.getMaterialPart().getPart().getShortUnlocalizedName();
        String variantTexture = new ResourceLocation(variantLocation.getResourceDomain(),
                "blocks/" + variantLocation.getResourcePath()).toString();
        replacements.put("texture", variantTexture);
        replacements.put("particle", variantTexture);
        String modid = this.getMaterialPart().getPart().getOwnerId();
        replacements.put("ore_shadow", modid + ":blocks/" + unlocalizedName + "_shadow");
        replacements.put("ore", modid + ":blocks/" + unlocalizedName);
        templateFile.replaceContents(replacements);

        return new GeneratedModel("materials/" + this.getMaterialPart().getUnlocalizedName(), ModelType.BLOCKSTATE, templateFile.getFileContents());
    }


}
