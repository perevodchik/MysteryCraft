package perevodchik.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;

public class CraftingRegister {

    public static void register(){
        registerRecipes("sun_sword");
    }

    private static void registerRecipes(String name) {
        CraftingHelper.register(new ResourceLocation("rpg-c", name), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
    }
}
