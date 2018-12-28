package perevodchik.capability.skill;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StatStorage implements Capability.IStorage<IStat> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IStat> capability, IStat instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setFloat("bow", instance.getBowSkill());
        nbt.setFloat("scepter", instance.getScepterSkill());
        nbt.setFloat("sword", instance.getSwordSkill());
        nbt.setFloat("shield", instance.getShieldSkill());
        nbt.setFloat("farmer", instance.getFarmerSkill());
        nbt.setFloat("miner", instance.getMinerSkill());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IStat> capability, IStat instance, EnumFacing side, NBTBase nbt) {
        instance.setBowSkill(((NBTTagCompound) nbt).getFloat("bow"));
        instance.setScepterSkill(((NBTTagCompound) nbt).getFloat("scepter"));
        instance.setSwordSkill(((NBTTagCompound) nbt).getFloat("sword"));
        instance.setShieldSkill(((NBTTagCompound) nbt).getFloat("shield"));
        instance.setFarmerSkill(((NBTTagCompound) nbt).getFloat("farmer"));
        instance.setMinerSkill(((NBTTagCompound) nbt).getFloat("miner"));

    }

}
