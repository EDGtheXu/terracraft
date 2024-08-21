package com.theXu.terracraft0323.item.terraSword.xingNu;

import com.theXu.terracraft0323.item.terraSword.terraria_sword_base;
import com.theXu.terracraft0323.util.computer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Position;
import net.minecraft.core.Rotations;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.*;
import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class xing_nu extends terraria_sword_base {
    private static final int damage = 20;
    private static final float targetRange= 40;
    private static final float forwardSpeed = 1;

    public xing_nu() {
        super(damage);

    }

    @Override
    protected void genWaves(Level level, Player playerIn, InteractionHand handIn) {

        Vec3 eye = playerIn.position().add(0,1,0);
        Entity target = getTargets(eye,eye.add(playerIn.getForward().normalize().scale(targetRange)),level,playerIn);
        Vec3 waveTarget = Vec3.ZERO;

        if(target!=null){
            //周围有目标 预判
            waveTarget = target.position().add(target.getDeltaMovement().scale(20)).add(0,1,0);

        }else{
            //周围无目标
            Vec3 ori = playerIn.position().add(0,1,0);
            Vec3 end = ori.add(playerIn.getForward().normalize().scale(targetRange));
            BlockHitResult blockHitResult = level.clip(new ClipContext(ori,end, ClipContext.Block.OUTLINE,ClipContext.Fluid.NONE,playerIn));
            waveTarget = blockHitResult.getLocation();

        }
        var wave = new xing_nu_wave(playerIn,level,waveTarget);
        wave.setPos(waveTarget.add(Math.random() * 5 - 5,20,Math.random() * 5 - 5));
        wave.shoot(waveTarget.x - wave.getX(),waveTarget.y- wave.getY(),waveTarget.z - wave.getZ(),1,1);

        level.addFreshEntity(wave);

    }

    private LivingEntity getTargets(Vec3 ori,Vec3 end,Level level,Entity entity){
        //BlockHitResult blockHitResult = level.clip(new ClipContext(ori,end, ClipContext.Block.OUTLINE,ClipContext.Fluid.NONE,entity));
        AABB range = entity.getBoundingBox().inflate(targetRange);


        List<HitResult> hits = new ArrayList<>();
        List<HitResult> subHits = new ArrayList<>();
        List<? extends Entity> entities = level.getEntities(entity,range,entity1 -> entity1.isPickable() && entity1.isAlive());

        for(var e : entities){
            //获取视线交点
            Vec3 vec3 = e.getBoundingBox().clip(ori,end).orElse(null);
            //优先指向的目标
            if(vec3!=null){
                System.out.println("point direct");
                EntityHitResult entityHitResult = new EntityHitResult(e,vec3);
                hits.add(entityHitResult);
            }//自瞄其他目标
            else if(hits.isEmpty() && computer.angle(e.position().subtract(ori),end.subtract(ori)) < 30){
                EntityHitResult entityHitResult = new EntityHitResult(e,e.position());
                subHits.add(entityHitResult);
            }

        }

        if(!hits.isEmpty()){
            hits.sort((o1,o2)->o1.getLocation().distanceToSqr(ori) < o2.getLocation().distanceToSqr(ori)?-1:1);

            HitResult hitResult = hits.getFirst();
            if(hitResult instanceof  EntityHitResult entityHitResult &&
                entityHitResult.getEntity() instanceof LivingEntity livingEntity){
                return livingEntity;
            }
        }else if(!subHits.isEmpty()){
            subHits.sort((o1,o2)->o1.getLocation().distanceToSqr(ori) < o2.getLocation().distanceToSqr(ori)?-1:1);

            HitResult hitResult = subHits.getFirst();
            if(hitResult instanceof  EntityHitResult entityHitResult &&
                    entityHitResult.getEntity() instanceof LivingEntity livingEntity){
                return livingEntity;
            }
        }

        return null;

    }


    //初始附魔
    public ItemStack createStack(){
        ItemStack s = getDefaultInstance();

        Optional<HolderLookup.RegistryLookup<Enchantment>> lookup = null;
        Level level = Minecraft.getInstance().level;
        if ( level != null) {
            lookup = Minecraft.getInstance().level.registryAccess().lookup(Registries.ENCHANTMENT);
            HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = lookup.get();
            //List<Holder.Reference<Enchantment>> NEGATIVE_ENCHANTMENTS = enchantmentRegistryLookup.listElements().filter(enchantmentReference -> enchantmentReference.is(EnchantmentTags.CURSE)).toList();

            var op = enchantmentRegistryLookup.get(Enchantments.SHARPNESS);
            if(op.isPresent()){
                var enchant = op.get().getDelegate();
                s.enchant(enchant,5);
            }

        }

        return s;
    }

}
