package perevodchik.capability.water;

public class WaterLevel implements IWater{
    private float waterLevel = 1;

    @Override
    public void setWaterLevel(float l) {
        this.waterLevel = l;
    }

    @Override
    public float getWaterLevel() {
        return waterLevel;
    }

    @Override
    public void decWaterLevel(float l) {
        this.waterLevel -= l;
    }

    @Override
    public void incWaterLevel(float l) {
        this.waterLevel += l;
    }

}
