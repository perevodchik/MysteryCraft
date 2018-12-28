package perevodchik.capability.cold;

public interface ICold {
    void incCold();
    void decCold();

    void customChgCold(int c);

    int getCold();
    void setCold(int c);
}
