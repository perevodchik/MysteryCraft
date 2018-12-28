package perevodchik.capability.skill;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class StatProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IStat.class)
    public static final Capability<IStat> skill = null;

    private IStat instance = Objects.requireNonNull(skill).getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == skill;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == skill ? skill.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return Objects.requireNonNull(skill).getStorage().writeNBT(skill, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        assert false;
        skill.getStorage().readNBT(skill, this.instance, null, nbt);
    }

}
