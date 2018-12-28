package perevodchik.util.registers;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class DimensionRegister {

    public static void initModDimension() {
        //register(2, "overlord_boss", "_boss", BossDimensionProvider.class);
    }

    private static void register(int idIn, String nameIn, String suffixIn, Class <? extends WorldProvider> clazzIn, boolean leepLoaded) {
        DimensionType.register(nameIn, suffixIn, idIn, clazzIn, leepLoaded);
    }

    private static void register(int idIn, String nameIn, String suffixIn, Class<? extends WorldProvider> clazzIn) {
        DimensionType.register(nameIn, suffixIn, idIn, clazzIn, false);
    }
}
