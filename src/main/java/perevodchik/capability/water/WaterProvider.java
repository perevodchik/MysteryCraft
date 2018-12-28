package perevodchik.capability.water;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class WaterProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IWater.class)
    public static final Capability<IWater> water = null;

    private IWater instance = Objects.requireNonNull(water).getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == water;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == water ? water.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return Objects.requireNonNull(water).getStorage().writeNBT(water, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        assert false;
        water.getStorage().readNBT(water, this.instance, null, nbt);
    }
}
