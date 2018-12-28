package perevodchik.capability.skill;

import java.util.Random;

public class Stat implements IStat {
    private float BowSkill = 0;
    private float ScepterSkill = 0;
    private float SwordSkill = 0;
    private float ShieldSkill = 0;
    private float FarmerSkill = 0;
    private float MinerSkill = 0;

    private void normalize() {
        if(this.getBowSkill() > 3) { this.setBowSkill(3); }
        if(this.getScepterSkill() > 3) { this.setScepterSkill(3); }
        if(this.getSwordSkill() > 3) { this.setSwordSkill(3); }
        if(this.getShieldSkill() > 3) { this.setShieldSkill(3); }
        if(this.getFarmerSkill() > 3) { this.setFarmerSkill(3); }
        if(this.getMinerSkill() > 3) { this.setMinerSkill(3); }

        if(this.getBowSkill() < 0) { this.setBowSkill(0); }
        if(this.getScepterSkill() < 0) { this.setScepterSkill(0); }
        if(this.getSwordSkill() < 0) { this.setSwordSkill(0); }
        if(this.getShieldSkill() < 0) { this.setShieldSkill(0); }
        if(this.getFarmerSkill() < 0) { this.setFarmerSkill(0); }
        if(this.getMinerSkill() < 0) { this.setMinerSkill(0); }
    }

    @Override
    public void decSkills() {
        Random r = new Random();
        this.decBowSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.decScepterSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.decSwordSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.decShieldSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.decFarmerSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.decMinerSkill((float) (Math.round((r.nextFloat() / 2) * 100) / 100) / 2);
        this.normalize();
    }

    @Override
    public void incBowSkill(float a) {
        BowSkill += a;
    }

    @Override
    public void incScepterSkill(float a) {
        ScepterSkill += a;
    }

    @Override
    public void incSwordSkill(float a) {
        SwordSkill += a;
    }

    @Override
    public void incShieldSkill(float a) {
        ShieldSkill += a;
    }

    @Override
    public void incFarmerSkill(float a) {
        FarmerSkill += a;
    }

    @Override
    public void incMinerSkill(float a) {
        MinerSkill += a;
    }

    @Override
    public void decBowSkill(float c) {
        BowSkill -= c;
    }

    @Override
    public void decScepterSkill(float c) {
        ScepterSkill -= c;
    }

    @Override
    public void decSwordSkill(float c) {
        SwordSkill -= c;
    }

    @Override
    public void decShieldSkill(float c) {
        ShieldSkill -= c;
    }

    @Override
    public void decFarmerSkill(float c) {
        FarmerSkill -= c;
    }

    @Override
    public void decMinerSkill(float c) {
        MinerSkill -= c;
    }

    @Override
    public float getBowSkill() {
        return BowSkill;
    }

    @Override
    public float getScepterSkill() {
        return ScepterSkill;
    }

    @Override
    public float getSwordSkill() {
        return SwordSkill;
    }

    @Override
    public float getShieldSkill() {
        return ShieldSkill;
    }

    @Override
    public float getFarmerSkill() {
        return FarmerSkill;
    }

    @Override
    public float getMinerSkill() {
        return MinerSkill;
    }

    @Override
    public void setBowSkill(float c) {
        this.BowSkill = c;
    }

    @Override
    public void setScepterSkill(float c) {
        this.ScepterSkill = c;
    }

    @Override
    public void setSwordSkill(float c) {
        this.SwordSkill = c;
    }

    @Override
    public void setShieldSkill(float c) {
        this.ShieldSkill = c;
    }

    @Override
    public void setFarmerSkill(float c) {
        this.FarmerSkill = c;
    }

    @Override
    public void setMinerSkill(float c) {
        this.MinerSkill = c;
    }

}
