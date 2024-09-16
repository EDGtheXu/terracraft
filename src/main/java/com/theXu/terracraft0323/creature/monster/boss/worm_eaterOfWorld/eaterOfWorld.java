package com.theXu.terracraft0323.creature.monster.boss.worm_eaterOfWorld;

import com.theXu.terracraft0323.creature.monster.boss.bossSkill;
import com.theXu.terracraft0323.creature.monster.boss.bossSkills;
import com.theXu.terracraft0323.creature.monster.boss.terraBossBase;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.entity.wave.following_wave;
import com.theXu.terracraft0323.util.computer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class eaterOfWorld extends terraBossBase {
    int segmentCount = 15;
    int genTick = 5;
    boolean shouldMove;
    boolean shouldFollowTarget = true;
    float turnSpeed = 3f;
    float moveSpeed = 0.4f;
    boolean genSegments = true;
    List<terraBossBase>segments = new ArrayList<>();
    private void genSegments(){
        Vec3 dir = this.getForward().normalize().scale(-1.1);
        eaterOfWorld_segment temp = null;
        for(int i=1;i<=segmentCount;i++){
            eaterOfWorld_segment newSegment = new eaterOfWorld_segment(this,level(),i);
            newSegment.setPos(position().add(dir.scale(i)));

            newSegment.setLastSegment(Objects.requireNonNullElse(temp, this));

            temp = newSegment;
            segments.add(newSegment);
            level().addFreshEntity(newSegment);
        }
    }
    public eaterOfWorld(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        if(!level.isClientSide){
            if(getTarget()!=null){
                this.moveTo(getTarget().position());
            }
        }
        this.noPhysics = true;
    }
    public eaterOfWorld(Level level,boolean genSegments) {
        super(ModEntities.WORM_EATER_OF_EATER.get(), level);
        if(!level.isClientSide){
            if(getTarget()!=null){
                this.moveTo(getTarget().position());
            }
        }
        this.noPhysics = true;
        this.genSegments = genSegments;
    }
    //这里必须static，否则不会初始化时生成
    private static final RawAnimation roll = RawAnimation.begin().thenPlay("worm.roll");
    private static final RawAnimation bite = RawAnimation.begin().thenPlay("worm.bite");

    @Override
    public void addSkills() {
        addSkillNoAnim(new bossSkill("dash",120,10));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("fire",10,5));
        addSkillNoAnim(new bossSkill("follow",120,1));

    }
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    boolean isDashing = false;
    @Override
    public void tick(){
        super.tick();
        shouldMove = true;
        if(!level().isClientSide){
            if(this.tickCount==genTick && genSegments){//召唤的瞬间位置为初始值，要延迟召唤segments
                genSegments(); }
            if(getTarget()!=null && shouldFollowTarget){ this.lookAt(getTarget(), turnSpeed,30); }
            if(shouldMove) this.setPos(position().add(getForward().normalize().scale(moveSpeed)));
            skills.tick();
            var target = getTarget();
            switch (skills.getNextSkill()){
                case "fire":
                    isDashing = false;
                    moveSpeed = 0.4f;
                    turnSpeed = 3;
                    shouldMove = true;
                    if(!skills.canTrigger())break;
                    if(target==null)break;
                    following_wave wave = new following_wave(this,getTarget(),level(),0f,(float) (this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()*0.8)){
                        public boolean canHitEntity(Entity entity){
                            if(entity instanceof Monster){
                                return false;
                            }
                            return super.canHitEntity(entity);
                        }
                    };

                    wave.setPos(position());
                    wave.shoot(target.getX()-getX(),target.getY()+1-getY(),target.getZ()-getZ(),1F,5);
                    level().addFreshEntity(wave);
                    break;

                case "dash":
                    if(target==null)break;
                    if(skills.canContinue()) {
                        if(computer.angle(this.getForward(),target.position().subtract(position()))>20
                            &&!isDashing
                        ){
                            skills.tick = 0;
                        }
                        isDashing = true;
                        moveSpeed = 2f;
                        turnSpeed = 2;
                        shouldMove = true;
                    }
                    break;
                case "follow":
                    isDashing = false;
                    if(skills.canContinue()){
                        moveSpeed = 0.4f;
                        turnSpeed = 3;
                        shouldMove = true;
                    }
                    break;
            }
        }
    }
    @Override//死亡时
    public void onRemovedFromLevel() {
        if(!level().isClientSide && !segments.isEmpty()){
            eaterOfWorld newHead = new eaterOfWorld(level(),false);
            newHead.setXRot(xRotO);
            newHead.setYRot(yRotO);
            newHead.setPos(position());
            newHead.genSegments = false;

            level().addFreshEntity(newHead);
            terraBossBase last = newHead;

            for(var n : segments){
                var seg = (eaterOfWorld_segment)n;
                if(seg.segmentIndex>1){
                    newHead.segments.add(n);
                    seg.segmentIndex -=1;
                    seg.head = newHead;
                    seg.lastSegment = last;
                    last = seg;
                }else{//移除第二节点
                    ((eaterOfWorld_segment) n).genNewHeadOnRemove = false;
                    n.discard();
                }
            }
        }
        super.onRemovedFromLevel();
    }

    @Override//boss条更新
    protected void customServerAiStep() {
        super.customServerAiStep();
        if(shouldShowBossBar()){
            float health = this.getHealth();
            float maxHp = this.getMaxHealth();
            for (terraBossBase segment : segments) {
                health += segment.getHealth();
                maxHp += segment.getMaxHealth();
            }
            this.bossEvent.setProgress(health / maxHp);
        }

    }
}
