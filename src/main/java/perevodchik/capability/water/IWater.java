package perevodchik.capability.water;

public interface IWater {
    void setWaterLevel(float l);
    float getWaterLevel();

    void decWaterLevel(float l);
    void incWaterLevel(float l);
}
