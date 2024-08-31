package com.theXu.terracraft0323.ability.playerLevel;

import net.minecraft.nbt.CompoundTag;

public class playerLevel {
    public int swordL = 25;
    public float swordR = 0.36F;
    public int fishL = 50;
    public float fishR = 0.28F;

    public float flyRemain = 3000;

    public boolean canFly = false;//翅膀飞行
    public float flySpeedV;
    public float flySpeedH;
    public float flyMaxTime;
    public boolean inputJumping;
    public boolean flying = true;
    public boolean flyrefresh = false;

    public int summonCount = 0;
    public int summonCountMax = 1;
    public int magicRemain = 200;
    public int magicMax = 200;

    public playerLevel(){

    }
    public playerLevel get(){
        return new abilityRegister().get();
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("fishL",fishL);
        nbt.putFloat("fishR",fishR);
        nbt.putInt("swordL",swordL);
        nbt.putFloat("swordR",swordR);
        nbt.putFloat("flyRemain",flyRemain);
        nbt.putBoolean("canFly",canFly);
        nbt.putBoolean("jump",inputJumping);

        nbt.putInt("summonCount",summonCount);
        nbt.putInt("summonCountMax",summonCountMax);

        nbt.putInt("magicRemain",magicRemain);
        nbt.putInt("magicMax",magicMax);
    }
    public CompoundTag toNBTData(){
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("fishL",fishL);
        nbt.putFloat("fishR",fishR);
        nbt.putInt("swordL",swordL);
        nbt.putFloat("swordR",swordR);
        nbt.putFloat("flyRemain",flyRemain);
        nbt.putBoolean("canFly",canFly);
        nbt.putBoolean("jump",inputJumping);

        nbt.putInt("summonCount",summonCount);
        nbt.putInt("summonCountMax",summonCountMax);

        nbt.putInt("magicRemain",magicRemain);
        nbt.putInt("magicMax",magicMax);
        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){
        fishL = nbt.getInt("fishL");
        fishR = nbt.getFloat("fishR");
        swordL = nbt.getInt("swordL");
        swordR = nbt.getFloat("swordR");
        flyRemain = nbt.getFloat("flyRemain");
        canFly = nbt.getBoolean("canFly");
        inputJumping = nbt.getBoolean("jump");

        summonCount = nbt.getInt("summonCount");
        summonCountMax = nbt.getInt("summonCountMax");
    }

}
