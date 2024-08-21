package com.theXu.terracraft0323.creature.monster.boss.kesuluzhiyan;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class kesuluzhiyanGoal extends Goal {
    public final kesuluzhiyan firstAnimal;
    public kesuluzhiyanGoal(kesuluzhiyan firstAnimal){
        this.firstAnimal = firstAnimal;
    }
    // 重写了Goal类中的canUse方法，该方法用于判断是否应当执行这个行为目标。
    // 这里返回true 表示一直执行。
    // 逻辑很简单就是给最近的玩家添加一个饥饿的效果。
    @Override
    public boolean canUse() {
        Level level = this.firstAnimal.level();
        if(!level.isClientSide){
            Player nearestPlayer = level.getNearestPlayer(this.firstAnimal, 10);
            if(nearestPlayer!=null){
                nearestPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 3 * 20, 3));
            }

        }
        return true;
    }

}