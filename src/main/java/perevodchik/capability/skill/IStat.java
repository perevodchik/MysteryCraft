package perevodchik.capability.skill;

public interface IStat {

    void decSkills();

    void incBowSkill(float a);
    void incScepterSkill(float a);
    void incSwordSkill(float a);
    void incShieldSkill(float a);
    void incFarmerSkill(float a);
    void incMinerSkill(float a);

    void decBowSkill(float c);
    void decScepterSkill(float c);
    void decSwordSkill(float c);
    void decShieldSkill(float c);
    void decFarmerSkill(float c);
    void decMinerSkill(float c);

    float getBowSkill();
    float getScepterSkill();
    float getSwordSkill();
    float getShieldSkill();
    float getFarmerSkill();
    float getMinerSkill();

    void setBowSkill(float c);
    void setScepterSkill(float c);
    void setSwordSkill(float c);
    void setShieldSkill(float c);
    void setFarmerSkill(float c);
    void setMinerSkill(float c);
}
