package net.flandre923.tutorialmod.datagen.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.machinezoo.noexception.throwing.ThrowingUnaryOperator;
import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.recipe.GemInfusingStationRecipe;
import net.flandre923.tutorialmod.util.FluidJSONUtil;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GemInfusingRecipeBuilder implements RecipeBuilder {
    private final GemInfusingStationRecipe recipe;

    public GemInfusingRecipeBuilder(GemInfusingStationRecipe recipe) {
        this.recipe = recipe;
    }



    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return null;
    }

    @Override
    public Item getResult() {
        return recipe.getResultItem().getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new Result(pRecipeId,recipe));
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final GemInfusingStationRecipe recipe;

        public Result(ResourceLocation id, GemInfusingStationRecipe recipe) {
            this.id = id;
            this.recipe = recipe;
        }
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.addProperty("type", new ResourceLocation(TutorialMod.MOD_ID,"gem_infusing").toString());

            JsonArray ingredients = new JsonArray();
            for (Ingredient ing : recipe.getIngredients()) {
                ingredients.add(ing.toJson());
            }
            pJson.add("ingredients", ingredients);

            FluidStack fluidStack = new FluidStack(recipe.getFluid(),recipe.getFluid().getAmount());
            pJson.add("fluid", FluidJSONUtil.toJson(fluidStack));

            JsonObject output = new JsonObject();
            ResourceLocation resultItem = BuiltInRegistries.ITEM.getKey(recipe.getResultItem().getItem());
            output.addProperty("item", resultItem.toString());

            pJson.add("output", output);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return recipe.getSerializer();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

}
