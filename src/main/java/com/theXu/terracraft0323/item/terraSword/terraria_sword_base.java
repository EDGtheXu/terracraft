package com.theXu.terracraft0323.item.terraSword;


import com.theXu.terracraft0323.item.tiers.modTiers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public abstract class terraria_sword_base extends SwordItem {
    protected int internal = 1000;
    protected int animDur = 200;
    private boolean ok;
    public int damage;

    public terraria_sword_base(int damage) {
        super(new modTiers(),  new Item.Properties()
                //.rarity(Rarity.RARE)
                .fireResistant()
                .component(DataComponents.UNBREAKABLE,new Unbreakable(true))
                .component(DataComponents.ATTRIBUTE_MODIFIERS,createAttributes(new modTiers(),damage,10))

        );
        this.damage = damage;
        ok = true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        componentList.add(Component.literal("TerraCraft Sword").withColor(Color.green.getRGB()));

        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {

        if(!level.isClientSide()) {
            if(!ok) return  super.use(level,playerIn,handIn);
            ok  = false;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ok = true;
                }
            },getAttackInternal());
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    playerIn.stopUsingItem();
                }
            },getAnimDur());
            playerIn.startUsingItem(handIn);

            genWaves(level,playerIn,handIn);

        }

        return super.use(level,playerIn,handIn);
    }



    @Override
    public  @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }
    public int getAttackInternal(){
        return internal;
    };
    public int getAnimDur(){
        return animDur;
    }
    protected abstract void genWaves(Level level, Player playerIn, InteractionHand handIn);
}
