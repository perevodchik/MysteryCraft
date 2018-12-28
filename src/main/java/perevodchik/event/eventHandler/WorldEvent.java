package perevodchik.event.eventHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.capability.skill.IStat;
import perevodchik.capability.skill.StatProvider;

public class WorldEvent {

    @SubscribeEvent
    public static void worldEventLoad(net.minecraftforge.event.world.WorldEvent.Load event) {
    }

    @SubscribeEvent
    public static void breakEvent(BlockEvent.BreakEvent event) {
        IBlockState block = event.getState();
        IStat stats = event.getPlayer().getCapability(StatProvider.skill, null);

        {
            if(stats.getMinerSkill() < 3) {
                if (block == Blocks.EMERALD_ORE.getDefaultState() || block == Blocks.DIAMOND_ORE.getDefaultState()) {
                    stats.incMinerSkill(0.01F);
                } else if (block == Blocks.IRON_ORE.getDefaultState() || block == Blocks.GOLD_ORE.getDefaultState()) {
                    stats.incMinerSkill(0.005F);
                } else if (block == Blocks.LAPIS_ORE.getDefaultState()) {
                    stats.incMinerSkill(0.001F);
                }
                System.out.println("miner: " + stats.getMinerSkill());
            }
        }

        {
            if (stats.getFarmerSkill() < 3) {
                if (block == Blocks.CARROTS || block == Blocks.POTATOES || block == Blocks.BEETROOTS || block == Blocks.WHEAT) {
                    stats.incFarmerSkill(0.01F);
                }
                System.out.println("farmer: " + stats.getFarmerSkill());
            }
        }
    }
}
