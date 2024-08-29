package com.theXu.terracraft0323.item.terraSummon.ke_yan_fa_zhang;

import com.theXu.terracraft0323.item.terraSummon.summons_base;
import com.theXu.terracraft0323.item.terraSword.iceSword.ice_sword_wave;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class ke_yan_fa_zhang_summon extends summons_base {
    private int attackInternal = 2 * 20;
    private int attackRemain = 2 * 20;
    private float attackDistance = 20 * 20;

    public ke_yan_fa_zhang_summon(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);

    }

    @Override
    public void tick() {

        super.tick();

        if(this.owner!=null){
            this.setPos(owner.position().add(0,2,0));
            this.setYRot(owner.yRotO);

            if(attackRemain-- < 0 ){
                attackRemain = attackInternal;
                performAttack();
            }
        }

    }


    private void performAttack() {
        LivingEntity tar = getTarget();
        if (tar != null) {
            if(canAttack(tar)){
                System.out.println("summon attack : " + tar.getName() +"  "+ tar.distanceTo(this));
                ice_sword_wave wave = new ice_sword_wave((Player) owner,level());
                wave.shoot(tar.getX()-getX(),tar.getY()-getY(),tar.getZ()-getZ(),3,0);
                wave.setPos(this.position());
                level().addFreshEntity(wave);

            }

        }
    }

    @Override
    protected void registerGoals() {
        // 这里添加的是我们自己的Goal

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, true, false, LIVING_ENTITY_SELECTOR));

    }

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = p_348303_ ->
            (p_348303_ instanceof Monster) && !(p_348303_ instanceof summons_base)
                    && p_348303_.attackable();

    public boolean canAttack(LivingEntity entity){

        return distanceToSqr(entity)<attackDistance && entity.isAlive();
    }

}