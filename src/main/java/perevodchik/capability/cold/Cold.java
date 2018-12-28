package perevodchik.capability.cold;

public class Cold implements ICold{
    private int cold = 0;

    @Override
    public void incCold() {
        this.cold += 1;
    }

    @Override
    public void decCold() {
        this.cold -= 1;
    }

    @Override
    public void customChgCold(int c) {
        this.cold += c;
    }

    @Override
    public int getCold() {
        return cold;
    }

    @Override
    public void setCold(int c) {
        this.cold = c;
    }
}
