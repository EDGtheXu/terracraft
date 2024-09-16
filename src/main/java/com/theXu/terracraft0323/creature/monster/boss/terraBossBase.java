package com.theXu.terracraft0323.creature.monster.boss;

import com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan.kesuluzhiyan;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;
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

public abstract class terraBossBase extends Monster implements GeoEntity {


    public terraBossBase(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setNoGravity(true);
        addSkills();
    }
    public abstract void addSkills();


    //攻击目标
    protected void registerGoals() {
        //this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 100F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }


    //技能动画
    public bossSkills skills = new bossSkills();

    private final Map<String,RawAnimation> skillMap = new HashMap<>();
    private int lastAnimIndex = -1;
    private static final EntityDataAccessor<Integer> DATA_SKILL_INDEX = SynchedEntityData.defineId(terraBossBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SKILL_TICK = SynchedEntityData.defineId(terraBossBase.class, EntityDataSerializers.INT);

    //动画数据同步
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SKILL_INDEX, 0);
        builder.define(DATA_SKILL_TICK, 0);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //controllers.add(DefaultAnimations.genericWalkIdleController(this));
        //controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));

        controllers.add(new AnimationController<>(this, 20, state -> {
            terraBossBase entity = (terraBossBase)state.getData(DataTickets.ENTITY);
            if(!entity.isAlive()) return PlayState.STOP;


            if(skills.count()==0)return PlayState.STOP;

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

    public void addSkill( bossSkill bossSkill,RawAnimation anim){
        this.skills.pushSkill(bossSkill);
        //if(anim==null)return;
        skillMap.put(bossSkill.skill,anim);
    }
    public void addSkill(String animName, int continueTime, int triggerTime, RawAnimation anim){
        this.skills.pushSkill(animName,continueTime,triggerTime);
        //if(anim==null)return;
        skillMap.put(animName,anim);
    }
    public void addSkillNoAnim( bossSkill bossSkill){
        this.skills.pushSkill(bossSkill);
        //if(anim==null)return;
        //skillMap.put(bossSkill.skill,anim);
    }

    //技能逻辑

    @Override
    public void tick(){
        //动画同步

        if(level().isClientSide){
            skills.index = this.entityData.get(DATA_SKILL_INDEX);
            skills.tick = this.entityData.get(DATA_SKILL_TICK);
        }else{
            skills.tick();
            this.entityData.set(DATA_SKILL_INDEX,skills.index);
            this.entityData.set(DATA_SKILL_TICK,skills.tick);
        }

        collisionHurt();
        super.tick();
    }



    //开启碰撞伤害
    public boolean canCollisionHurt(){
        return true;
    }

    public void collisionHurt(){
        if(canCollisionHurt() && !level().isClientSide){
            //包围盒检测造成伤害
            var entities=level().getEntities(this,this.getBoundingBox());
            if(!entities.isEmpty()){
                for (var e:entities) {
                    if(canAttack(e))
                        e.hurt(this.damageSources().mobAttack(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                }
            }
        }
    }

    public boolean canAttack(Entity entity){
        if(entity instanceof Player
                || getTarget()!=null&&getTarget().is(entity))
        {
            return true;
        }
        return false;
    }

    //boss条
    public boolean shouldShowBossBar(){return true;};
    protected ServerBossEvent bossEvent = (ServerBossEvent)new ServerBossEvent(
            this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS
    ).setDarkenScreen(true);
    @Override//boss条显示
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        if(shouldShowBossBar())
            this.bossEvent.addPlayer(player);
    }

    @Override//boss条消失
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        if(shouldShowBossBar())
            this.bossEvent.removePlayer(player);
    }
    @Override//boss条更新
    protected void customServerAiStep() {
        super.customServerAiStep();
        if(shouldShowBossBar())
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
    @Override//死亡时
    public void onRemovedFromLevel() {

        super.onRemovedFromLevel();
    }
    @Override//取消墙体窒息伤害
    public boolean isInWall(){
        return false;
    }
    /*
    @Override//远程攻击
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        livingEntity.sendSystemMessage(Component.literal("range attack"));
    }
*/
    @Override//是否免疫摔伤
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource damageSource) {
        return true;
    }

    @Override//是否在实体上渲染着火效果
    public boolean displayFireAnimation() {
        return false;
    }
    @Override//受伤音效
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }


    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }
}