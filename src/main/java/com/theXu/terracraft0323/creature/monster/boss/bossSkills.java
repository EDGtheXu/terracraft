package com.theXu.terracraft0323.creature.monster.boss;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class bossSkills {
    private final List<String> anim = new LinkedList<>();
    private final List<Integer> time= new LinkedList<>();
    private final List<Integer> trigger = new LinkedList<>();
    public int tick = 0;
    public int index = 0;

    public bossSkills(){

    }
    public int count(){return anim.size();};
    public boolean pushSkill(String anim,int continueTime,int triggerTime){
        this.anim.add(anim);
        this.time.add(continueTime);
        this.trigger.add(triggerTime);
        return true;
    }
    public boolean pushSkill(bossSkill skill){
        this.anim.add(skill.skill);
        this.time.add(skill.timeContinue);
        this.trigger.add(skill.timeTrigger);
        if(anim.size()==1) tick = 0;
        return true;
    }
    public void tick(){
        this.tick++;
        if(time.isEmpty())return;
        if(time.get(index) < tick) forceEnd();
    }



    public void forceEnd(){
        tick = 0;
        index = (index +1)%time.size();
    }
    public boolean canTrigger(){
        if(time.isEmpty() || trigger.isEmpty()) return false;
        return trigger.get(index) == this.tick;
    }
    public boolean canContinue(){
        if(time.isEmpty() || trigger.isEmpty()) return false;
        return trigger.get(index) < this.tick;
    }
    public String getNextSkill(){
        if(!time.isEmpty())
            return anim.get(index);
        return "";
    }
    public int getAllTick(){
        if(!time.isEmpty())
            return time.get(index);
        return -1;
    }

}
