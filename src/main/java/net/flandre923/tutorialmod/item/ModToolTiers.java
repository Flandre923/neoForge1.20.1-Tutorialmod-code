package net.flandre923.tutorialmod.item;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier ZIRCON;
    static {
        ZIRCON = TierSortingRegistry.registerTier(
                new ForgeTier(5, 2000, 9f, 3f, 24,
                        ModTags.Blocks.NEEDS_ZIRCON_TOOL, () -> Ingredient.of(ModItems.ZIRCON.get())),
                new ResourceLocation(TutorialMod.MOD_ID, "zircon"), List.of(Tiers.NETHERITE), List.of());
    }
}