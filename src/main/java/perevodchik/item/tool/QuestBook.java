package perevodchik.item.tool;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import perevodchik.client.gui.GuiQuestBook;

public class QuestBook extends Item {

    public QuestBook(String name, CreativeTabs tab) {
        this.setMaxStackSize(1);
        this.setRegistryName("quest_book");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        FMLCommonHandler.instance().showGuiScreen(new GuiQuestBook(playerIn));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
