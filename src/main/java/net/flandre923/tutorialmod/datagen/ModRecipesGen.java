package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesGen extends RecipeProvider {
    public ModRecipesGen(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ZIRCON_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#',ModItems.ZIRCON.get())
                .group("tutorial")
                .unlockedBy("has_zircon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(ModItems.ZIRCON.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ZIRCON.get(),9)
                .requires(ModBlocks.ZIRCON_BLOCK.get())
                .group("tutorial")
                .unlockedBy("has_zircon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(ModItems.ZIRCON.get()).build()))
                .save(pWriter);


        oreSmelting(pWriter, List.of(ModItems.RAW_ZIRCON.get()),RecipeCategory.MISC,ModItems.ZIRCON.get(),0.7f,200,"tutorialmod");
        oreBlasting(pWriter, List.of(ModItems.RAW_ZIRCON.get()),RecipeCategory.MISC,ModItems.ZIRCON.get(),0.7f,100,"tutorialmod");
    }
}
