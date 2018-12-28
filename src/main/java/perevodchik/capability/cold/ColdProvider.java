package perevodchik.capability.cold;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ColdProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(ICold.class)
    public static final Capability<ICold> cold = null;

    private ICold instance = Objects.requireNonNull(cold).getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == cold;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == cold ? cold.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return Objects.requireNonNull(cold).getStorage().writeNBT(cold, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        assert false;
        cold.getStorage().readNBT(cold, this.instance, null, nbt);
    }
}
