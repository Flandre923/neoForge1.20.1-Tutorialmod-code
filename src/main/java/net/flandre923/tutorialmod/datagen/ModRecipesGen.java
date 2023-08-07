package net.flandre923.tutorialmod.datagen;

import com.google.gson.JsonArray;
import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.datagen.provider.GemInfusingRecipeBuilder;
import net.flandre923.tutorialmod.fluid.ModFluidTypes;
import net.flandre923.tutorialmod.fluid.ModFluids;
import net.flandre923.tutorialmod.item.ModItems;
import net.flandre923.tutorialmod.recipe.GemInfusingStationRecipe;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

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
                .group("tutorialmod")
                .unlockedBy("has_zircon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(ModItems.ZIRCON.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ZIRCON.get(),9)
                .requires(ModBlocks.ZIRCON_BLOCK.get())
                .group("tutorialmod")
                .unlockedBy("has_zircon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(ModItems.ZIRCON.get()).build()))
                .save(pWriter);


        oreSmelting(pWriter, List.of(ModItems.RAW_ZIRCON.get()),RecipeCategory.MISC,ModItems.ZIRCON.get(),0.7f,200,"tutorialmod");
        oreBlasting(pWriter, List.of(ModItems.RAW_ZIRCON.get()),RecipeCategory.MISC,ModItems.ZIRCON.get(),0.7f,100,"tutorialmod");

        gemInfusing(pWriter,ModItems.ZIRCON.get(), Fluids.WATER,200,ModItems.RAW_ZIRCON.get());
        gemInfusing(pWriter, Items.DIAMOND, ModFluids.SOURCE_SOAP_WATER.get(),500,Items.STICK);
    }


    public void gemInfusing(Consumer<FinishedRecipe> consumer, Item item, Fluid fluid,int amount,Item ...inputs){
        ItemStack output = new ItemStack(item,1);

        NonNullList<Ingredient> inputs_list = NonNullList.withSize(1, Ingredient.EMPTY);
        inputs_list.set(0,Ingredient.of(inputs));
        FluidStack stack = new FluidStack(fluid,amount);
        GemInfusingStationRecipe recipe = new GemInfusingStationRecipe(new ResourceLocation(TutorialMod.MOD_ID,"1"),output,inputs_list,stack);

        RecipeBuilder builder = new GemInfusingRecipeBuilder(recipe);

        builder.save(consumer, new ResourceLocation(TutorialMod.MOD_ID,BuiltInRegistries.ITEM.getKey(item.asItem()).getPath() + "_from_gem"));

    }
}
