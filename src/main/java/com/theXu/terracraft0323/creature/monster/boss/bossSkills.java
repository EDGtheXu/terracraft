package com.theXu.terracraft0323.creature.monster.boss;

import java.util.Deque;
import java.util.LinkedList;

public class bossSkills {
    private final Deque<String> anim = new LinkedList<>();
    private final Deque<Integer> time= new LinkedList<>();
    private final Deque<Integer> trigger = new LinkedList<>();
    public int tick = 0;
    public int index = 0;

    public bossSkills(){

    }
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
        if(time.peek()<tick) forceEnd();
    }



    public void forceEnd(){
        anim.add(anim.poll());
        time.add(time.poll());
        trigger.add(trigger.poll());
        tick = 0;
        index = (index +1)%time.size();
    }
    public boolean canTrigger(){
        if(time.isEmpty() || trigger.isEmpty()) return false;
        return trigger.peek() == this.tick;
    }
    public boolean canContinue(){
        if(time.isEmpty() || trigger.isEmpty()) return false;
        return trigger.peek() < this.tick;
    }
    public String getNextSkill(){
        return anim.peek();
    }
    public int getAllTick(){
        if(!time.isEmpty())
            return time.peek();
        return -1;
    }

}
