package perevodchik.capability.cold;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ColdStorage implements Capability.IStorage<ICold> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICold> capability, ICold instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("waterLevel", instance.getCold());

        return nbt;
    }

    @Override
    public void readNBT(Capability<ICold> capability, ICold instance, EnumFacing side, NBTBase nbt) {
        instance.setCold(((NBTTagCompound) nbt).getInteger("waterLevel"));
    }
}