package com.oldviking.cat_armor.datagen;

import com.oldviking.cat_armor.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ModItems.CAT_ARMOR)
                        .pattern("A  ")
                        .pattern("AAA")
                        .pattern("AFA")
                        .input('A', Items.ARMADILLO_SCUTE)
                        .input('F', Items.SALMON)
                        .criterion(hasItem(Items.ARMADILLO_SCUTE), conditionsFromItem(Items.ARMADILLO_SCUTE))
                        .criterion(hasItem(Items.SALMON), conditionsFromItem(Items.SALMON))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Cat Armor Recipe";
    }
}
