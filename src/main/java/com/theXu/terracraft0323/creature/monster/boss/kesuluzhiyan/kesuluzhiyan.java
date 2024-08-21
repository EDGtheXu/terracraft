package com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan;

import com.theXu.terracraft0323.effect.ModEffects;
import com.theXu.terracraft0323.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import net.minecraft.world.Difficulty;

import java.util.function.Predicate;

public class kesuluzhiyan extends Monster {
    private static final EntityDataAccessor<Vector3f> DATA_ROTATE = SynchedEntityData.defineId(kesuluzhiyan.class, EntityDataSerializers.VECTOR3);
    private static final EntityDataAccessor<Integer> DATA_TARGET = SynchedEntityData.defineId(kesuluzhiyan.class, EntityDataSerializers.INT);


    //攻击行为数据
    private int bossStep = 1;//阶段数
    private int attactInterval = 5 * 20;
    private int attactContinue = 2 * 20;


    private int attactIntervalRemain = 5 * 20;


    private int attactRemain = 20;
    private float accelerateFactor = 1f;
    private float followDistance = 10;
    private float followFactor = 0.2f;

    //第二阶段
    private int maxContinueAttackTime = 5;
    private int continueAttackTime = 0;


    private final ServerBossEvent bossEvent = (ServerBossEvent)new ServerBossEvent(
            this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS
    )
            .setDarkenScreen(true);




    public kesuluzhiyan(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.xpReward = 50;

    }



    @Override
    public void tick() {

        super.tick();
        if(!level().isClientSide) {
            var v3f = new Vector3f(this.xRotO, this.yBodyRot, yBodyRotO);
            this.entityData.set(DATA_ROTATE,v3f );

        }
        else {
            var v3f = this.entityData.get(DATA_ROTATE);

        }

    }
//属性生成
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4616.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FLYING_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 200.0)
                .add(Attributes.ARMOR, 4.0)
                .add(Attributes.SAFE_FALL_DISTANCE,1020)
                .add(Attributes.ATTACK_DAMAGE,10)
                .add(Attributes.GRAVITY,0);


    }



//行为注册
    @Override
    protected void registerGoals() {
        // 这里添加的是我们自己的Goal
        this.goalSelector.addGoal(0,new kesuluzhiyanGoal(this));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0));
        //this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 200.0F,0.8f,true));

        //this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));

    }

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = p_348303_ ->
            (p_348303_ instanceof Player)
                    && p_348303_.attackable();


    private Vec3 attackDir = Vec3.ZERO;
    private float attackRotX = 0;
    @Override
    public void aiStep() {
        super.aiStep();
        //移动
        LivingEntity target = getTarget();
        if(target!=null){
            //基础移速
            Vec3 vec3 = Vec3.ZERO;//this.getForward().normalize().scale(getAttribute(Attributes.MOVEMENT_SPEED).getValue());


            if(this.attactRemain>=this.tickCount ){//冲刺攻击
                vec3 = vec3.add(attackDir.normalize().scale(accelerateFactor));
                this.setXRot(attackRotX);

            }else{//否则看向玩家
                lookAt(target,20,90);
                //target 到 上方的相对向量
                Vec3 delta = position().subtract(target.position()).multiply(1,0,1).normalize().scale(followDistance).add(0,followDistance,0);
                //this 到上方的向量
                vec3 = target.position().add(delta.subtract(position())) ;
                //乘以移速系数
                vec3 = vec3.scale(getAttribute(Attributes.MOVEMENT_SPEED).getValue() * followFactor);
            }
            this.setDeltaMovement(vec3);
            this.setPos(position().add(vec3));
        }

        //旋转
//        if (vec3.horizontalDistanceSqr() > 0.05) {
//            this.setYRot((float) Mth.atan2(vec3.z, vec3.x) * (180.0F / (float)Math.PI) - 90.0F);
//        }

        //攻击
        var entities=level().getEntities(this,this.getBoundingBox());
        if(!entities.isEmpty()){
            for (var e:entities) {
                if(e instanceof LivingEntity && canAttack((LivingEntity) e))
                    e.hurt(this.damageSources().mobAttack(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
            }
        }

//粒子效果
        float f = this.getScale();
        this.level()
                .addParticle(
                        this.bossStep==1? ParticleTypes.CLOUD : ParticleTypes.ASH,
                        getX() + this.random.nextGaussian() * (double)f,
                        getY() + this.random.nextGaussian() * (double)f,
                        getZ() + this.random.nextGaussian() * (double)f,
                        0.0,
                        0.0,
                        0.0
                );

    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.tickCount >= this.attactIntervalRemain) {

            this.attactIntervalRemain = this.tickCount + attactInterval ;
            if(bossStep==2){
                continueAttackTime+=1;
                if(continueAttackTime == maxContinueAttackTime){
                    attactIntervalRemain += 3 * 20;
                    continueAttackTime = 0;
                }
            }

            //难度设置
            if ((this.level().getDifficulty() == Difficulty.NORMAL || this.level().getDifficulty() == Difficulty.HARD)) {

                this.performAttack();
            }
        }

        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    private void performAttack(){
        LivingEntity tar = getTarget();
        this.setAggressive(true);
        if(tar!=null) {
            System.out.println("attack : " + tar.getName());
            this.attactRemain = this.tickCount + attactContinue;
            this.attackDir = this.getForward();
            this.attackRotX = this.getXRot();
        }

    }

    @Override
    public boolean hurt(DamageSource source, float damage){
        super.hurt(source,damage);
        //开启二阶段
        if(this.getHealth()-damage < this.getMaxHealth()/2 && bossStep == 1){
            bossStep +=1;
            this.attactInterval = 3 * 20;
            this.attactContinue = (int) (1.5*20);
            this.accelerateFactor = 1.5f;
            this.followFactor = 0.5F;

        }
        return true;
    }
//掉落物
    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);
        ItemEntity itementity = this.spawnAtLocation(ModItems.TAI_LA_XUE);
        if (itementity != null) {
            itementity.setExtendedLifetime();
        }
    }

//药水免疫
    @Override
    public boolean canBeAffected(MobEffectInstance potioneffect) {
        return !potioneffect.is(ModEffects.ICE_FROZEN) && super.canBeAffected(potioneffect);
    }


//数据同步
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ROTATE, new Vector3f());
        builder.define(DATA_TARGET, 0);
    }

    public Vector3f getRot(){
        return this.entityData.get(DATA_ROTATE);

    }


//属性设置
    public boolean isInWall(){
            return false;
    }
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
}
