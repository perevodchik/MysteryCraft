package perevodchik.capability.water;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class WaterStorage implements Capability.IStorage<IWater> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IWater> capability, IWater instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setFloat("waterLevel", instance.getWaterLevel());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IWater> capability, IWater instance, EnumFacing side, NBTBase nbt) {
        instance.setWaterLevel(((NBTTagCompound) nbt).getFloat("waterLevel"));
    }
}