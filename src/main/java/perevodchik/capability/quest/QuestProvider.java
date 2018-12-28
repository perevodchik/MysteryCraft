package perevodchik.capability.quest;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class QuestProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IQuestContainer.class)
    public static final Capability<IQuestContainer> quest = null;

    private IQuestContainer instance = quest.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == quest;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == quest ? quest.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return Objects.requireNonNull(quest).getStorage().writeNBT(quest, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        assert false;
        quest.getStorage().readNBT(quest, this.instance, null, nbt);
    }

}
