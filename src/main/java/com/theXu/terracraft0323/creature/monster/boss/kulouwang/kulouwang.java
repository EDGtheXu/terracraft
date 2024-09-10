package com.theXu.terracraft0323.creature.monster.boss.kulouwang;

import com.theXu.terracraft0323.creature.monster.boss.bossSkill;
import com.theXu.terracraft0323.creature.monster.boss.bossSkills;
import com.theXu.terracraft0323.entity.ModEntities;
import com.theXu.terracraft0323.entity.wave.following_wave;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;

public class kulouwang extends Monster implements GeoEntity , RangedAttackMob {


    private final bossSkills skills = new bossSkills();
    private final RawAnimation roll = RawAnimation.begin().thenPlay("animation.model.roll");
    private final RawAnimation fire = RawAnimation.begin().thenPlay("animation.model.fire");
    private final Map<String,RawAnimation> skillMap = new HashMap<>();
    enum HandState{
        follow,attack
    }
    HandState handState = HandState.follow;
    kulouwangHand  handLeft;
    kulouwangHand  handRight;
    public kulouwang(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setNoGravity(true);

        skills.pushSkill(new bossSkill("animation.model.roll",5*20,1));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));
        skills.pushSkill(new bossSkill("hand",100,20));
        skills.pushSkill(new bossSkill("dash",4*20,0));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));
        skills.pushSkill(new bossSkill("animation.model.fire",30,25));



        skillMap.put("animation.model.roll",roll);
        skillMap.put("animation.model.fire",fire);

        handLeft = new kulouwangHand(this,level(),false);
        handRight = new kulouwangHand(this,level(),true);
        handLeft.setPos(position());
        handRight.setPos(position());
        level().addFreshEntity(handLeft);
        level().addFreshEntity(handRight);

    }

    protected void registerGoals() {


        //this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0, 100, 20.0F));
        //this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 2, false));

        //this.goalSelector.addGoal(4, new MoveTowardsTargetGoal(this, 2, 10));

        //this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0));

        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 100F));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // Add our animations




    // Let's add our animation controller
    int lastAnimIndex = -1;
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //controllers.add(DefaultAnimations.genericWalkIdleController(this));
        //controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));

        controllers.add(new AnimationController<>(this, 20, state -> {
            kulouwang entity = (kulouwang)state.getData(DataTickets.ENTITY);
            if(!entity.isAlive()) return PlayState.STOP;

            String skillString = entity.skills.getNextSkill();
            if(skillString==null) return PlayState.STOP;
            RawAnimation skill = skillMap.get(skillString);

            if(skill!=null) {
                if(lastAnimIndex != skills.index){
                    lastAnimIndex = skills.index;
                    state.resetCurrentAnimation();
                    return PlayState.STOP;
                }
                state.setAnimation(skill);
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));

    }

    private Vec3 normalDash;
    @Override
    public void tick(){

        //释放技能
        boolean onDash = false;
        boolean onNormalDash = false;
        skills.tick();
        Entity entity = getTarget();

        if(!level().isClientSide && entity != null){
            this.lookAt(entity,10,10);
            String skillName = skills.getNextSkill();
            switch (skillName){
                case "animation.model.roll":
                    handState = HandState.follow;
                    onDash = true;
                    if(!skills.canContinue())break;
                    skillRoll();
                    break;
                case "animation.model.fire":
                    handState = HandState.follow;
                    if(skills.canTrigger())
                        skillFire();
                    break;
                case "dash":
                    handState = HandState.follow;
                    if(!skills.canContinue())return;
                    onDash = true;
                    onNormalDash = true;
                    if(skills.tick==1) normalDash = entity.position().add(0,2,0);

                    break;
                case "hand":
                    handState = HandState.attack;
                    break;

            }

            //非冲撞增加向上向量
            if(!onDash)
                this.addDeltaMovement(entity.position().subtract(position()).add(0,10,0).multiply(0,1,0).normalize().scale(0.1));
            //冲撞位移
            if(onNormalDash && normalDash!=null){
                dash(normalDash);
            }


        }


        if(!level().isClientSide){
            //包围盒检测造成伤害
            var entities=level().getEntities(this,this.getBoundingBox());

            if(!entities.isEmpty()){

                for (var e:entities) {
                    if(canAttack(e))
                        e.hurt(this.damageSources().mobAttack(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                }
            }
        }


        super.tick();
    }

    public boolean canAttack(Entity entity){
        if(entity instanceof Player
                || getTarget()!=null&&getTarget().is(entity))
        {
            return true;
        }
        return false;
    }


    private void skillRoll(){
        Entity target = getTarget();
        if(target==null) return;
        this.addDeltaMovement(target.position().subtract(position()).normalize().scale(0.15));
    }
    private void skillFire(){
        Entity target = getTarget();

        following_wave wave = new following_wave(this,target,level(),0.5f,1){
            public boolean canHitEntity(Entity entity){
                if(entity.getType() == ModEntities.KU_LOU_WANG_HAND.get()){
                    return false;
                }
                return super.canHitEntity(entity);
            }
        };
        wave.setPos(this.position());

        wave.shoot(target.getX()-getX(),target.getY()+1-getY(),target.getZ()-getZ(),1F,5);
        level().addFreshEntity(wave);
    }

    private void dash(Vec3 pos){
        Entity target = getTarget();
        if(target==null)return;
        this.addDeltaMovement(pos.subtract(position()).normalize().scale( 0.3));
    }


    @Override
    public void aiStep() {
        super.aiStep();

    }


    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void onRemovedFromLevel() {
        handLeft.discard();
        handRight.discard();

        super.onRemovedFromLevel();
    }

    //属性设置
    public boolean isInWall(){
        return false;
    }

    private final ServerBossEvent bossEvent = (ServerBossEvent)new ServerBossEvent(
            this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS
    ).setDarkenScreen(true);
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        livingEntity.sendSystemMessage(Component.literal("range attack"));
    }

    @Override//是否免疫摔伤
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource damageSource) {
        return true;
    }

    @Override//是否在实体上渲染着火效果
    public boolean displayFireAnimation() {
        return false;
    }
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }
}