package com.theXu.terracraft0323.creature.monster.boss.worm_eaterOfWorld;

import com.theXu.terracraft0323.creature.monster.boss.terraBossBase;
import com.theXu.terracraft0323.entity.ModEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animation.RawAnimation;

public class eaterOfWorld_segment extends terraBossBase {

    public eaterOfWorld head;
    public terraBossBase lastSegment;
    public static final RawAnimation roll = RawAnimation.begin().thenPlay("worm.roll");
    public int segmentIndex;
    public boolean genNewHeadOnRemove = true;

    public void setHead(eaterOfWorld head){
        this.head = head;
    }
    public void setLastSegment(terraBossBase lastSegment){

        this.lastSegment = lastSegment;
    }




    public eaterOfWorld_segment(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public eaterOfWorld_segment(eaterOfWorld head, Level level,int index) {

        super(ModEntities.WORM_EATER_OF_EATER_SEGMENT.get(), level);
        this.head = head;
        this.segmentIndex = index;

        this.noPhysics = true;
    }

    public Vec3 getNextPos(){
        this.lookAt(lastSegment,500,500);


        Vec3 newPos = lastSegment.position().add(position().subtract(lastSegment.position()).normalize().scale(1.1));
        return newPos;
    }


    @Override
    public void addSkills() {

    }

    @Override
    public void tick(){
        super.tick();

        if(!level().isClientSide) {
            if (lastSegment != null && lastSegment.isAlive())
                this.setPos(getNextPos());


        }

    }

   public boolean hurt(DamageSource source,float damage){
       return super.hurt(source,damage);

   }

    @Override//死亡时
    public void onRemovedFromLevel() {
        if(level().isClientSide || !genNewHeadOnRemove) return;

        if(head!=null &&head.isAlive()&& head.segments.size()>segmentIndex){//重新设置头

            eaterOfWorld_segment next = (eaterOfWorld_segment) head.segments.get(segmentIndex);
            if(!next.isAlive())return;
            eaterOfWorld newHead = new eaterOfWorld(level(),false);
            newHead.setHealth(next.getHealth());
            newHead.setPos(next.position());
            newHead.setYRot(next.yRotO);
            newHead.setXRot(next.xRotO);
            newHead.genSegments =  false;
            level().addFreshEntity(newHead);
            next.genNewHeadOnRemove = false;
            next.discard();
            terraBossBase last = newHead;
            for(var n : head.segments){
                var seg = (eaterOfWorld_segment)n;
                if(seg.segmentIndex>segmentIndex+1){
                    newHead.segments.add(seg);
                    seg.segmentIndex -= (segmentIndex+1);
                    seg.head = newHead;
                    seg.lastSegment = last;
                    last=seg;

                }
            }

        }

        if(head!=null && head.isAlive()){//删除子序列
            head.segments.removeIf(o->((eaterOfWorld_segment)o).segmentIndex>=segmentIndex);
        }



        super.onRemovedFromLevel();
    }

    public boolean shouldShowBossBar(){return false;};

}
