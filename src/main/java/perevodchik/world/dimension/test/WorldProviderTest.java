package perevodchik.world.dimension.test;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import org.jetbrains.annotations.NotNull;

public class WorldProviderTest extends WorldProvider {

    @NotNull
    @Override
    public DimensionType getDimensionType() {
        return null;
    }
}
