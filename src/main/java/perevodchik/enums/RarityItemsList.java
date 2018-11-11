package perevodchik.enums;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class RarityItemsList {
    public static EnumRarity RARITY_LEGENDARY = EnumHelper.addRarity("RARITY_LEGENDARY", TextFormatting.GOLD, "Legendary");
    public static EnumRarity RARITY_EPIC = EnumHelper.addRarity("RARITY_LEGENDARY", TextFormatting.AQUA, "Epic");
}
