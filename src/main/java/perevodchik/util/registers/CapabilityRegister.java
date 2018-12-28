package perevodchik.util.registers;

import net.minecraftforge.common.capabilities.CapabilityManager;
import perevodchik.capability.cold.Cold;
import perevodchik.capability.cold.ColdStorage;
import perevodchik.capability.cold.ICold;
import perevodchik.capability.quest.IQuestContainer;
import perevodchik.capability.quest.QuestContainer;
import perevodchik.capability.quest.QuestStorage;
import perevodchik.capability.skill.IStat;
import perevodchik.capability.skill.Stat;
import perevodchik.capability.skill.StatStorage;
import perevodchik.capability.water.IWater;
import perevodchik.capability.water.WaterLevel;
import perevodchik.capability.water.WaterStorage;

public class CapabilityRegister {

    public static void register() {
        CapabilityManager.INSTANCE.register(IStat.class, new StatStorage(), Stat.class);
        CapabilityManager.INSTANCE.register(IQuestContainer.class, new QuestStorage(), QuestContainer.class);
        CapabilityManager.INSTANCE.register(IWater.class, new WaterStorage(), WaterLevel.class);
        CapabilityManager.INSTANCE.register(ICold.class, new ColdStorage(), Cold.class);
        //CapabilityManager.INSTANCE.register(IFood.class, new FoodStorage(), Food.class);
    }

}
