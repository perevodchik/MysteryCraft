package perevodchik.world.dimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class BossDimension {
    private static final int DIM_ID = -2;

    public static void registryDimension() {
        DimensionManager.registerDimension(DIM_ID, DimensionType.getById(DIM_ID));
    }
}
