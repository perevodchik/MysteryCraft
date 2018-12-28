package perevodchik.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import org.jetbrains.annotations.NotNull;

public class WorldDate extends WorldSavedData {
    private static int day;
    private static int week;
    private static int month;
    private static int year;

    private static int tick;

    public WorldDate(String name) {
        super(name);
    }

    public WorldDate(World world) {
        super("time_data");
    }

    public static void tick(World world) {
        int dayCounter =(int) (world.getWorldTime() / 24000L % 2147483647L);
        year = dayCounter / 336;

        {
            month = dayCounter / 28 + 1;
            if (month > 12) month = month % 12;
            if (month == 0) month = 12;
        }

        week = 1 + ((dayCounter / 7) % 4);
        day = (dayCounter % 336) + 1;

        tick++;
    }

    public static int getDay() {
        return day;
    }

    public static int getWeek() {
        return week;
    }

    public static int getMonth() {
        return month;
    }

    public static int getYear() {
        return year;
    }

    public static int getTick() {
        return tick;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        System.out.println("time read ");
        day = nbt.getInteger("day");
        week = nbt.getInteger("week");
        month = nbt.getInteger("month");
        year = nbt.getInteger("year");

        tick = nbt.getInteger("tick");
    }

    @NotNull
    @Override
    public NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        System.out.println("time data write");

        compound.setInteger("day", day);
        compound.setInteger("week", week);
        compound.setInteger("month", month);
        compound.setInteger("year", year);

        compound.setInteger("tick", tick);

        return compound;
    }
}
