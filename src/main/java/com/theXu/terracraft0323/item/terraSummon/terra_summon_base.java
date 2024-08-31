package com.theXu.terracraft0323.item.terraSummon;

import com.theXu.terracraft0323.ServerManager;
import com.theXu.terracraft0323.ability.ModCapability;
import com.theXu.terracraft0323.ability.playerLevel.levelSaver;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public abstract class terra_summon_base extends Item {
    public terra_summon_base(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if(!level.isClientSide) {
            var ls = levelSaver.getServerState(ServerManager.getServerInstance()).levels;

            int summonRemain = ls.summonCountMax - ls.summonCount;

            int magicRemain = ls.magicRemain;
            System.out.println("sum:" + summonRemain + " " + getConsumeCount());
            System.out.println("mag:" + magicRemain + " " + getMagicConsume());

            if (    //summonRemain > getConsumeCount() &&
                    magicRemain  > getMagicConsume()) {
                System.out.println(player.getName());
                summon(level,player);
            }


        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        componentList.add(Component.literal("TerraCraft Summon").withColor(Color.green.getRGB()));
        componentList.add(Component.literal("伤害 "+getDamage()).withColor(Color.red.getRGB()));
        componentList.add(Component.literal("召唤栏位 "+getConsumeCount()).withColor(Color.yellow.getRGB()));
        componentList.add(Component.literal("法力消耗 " + getMagicConsume()).withColor(Color.CYAN.getRGB()));
        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
    }


    protected int getConsumeCount(){
        return 1;
    }

    protected abstract int getMagicConsume();
    protected abstract int getDamage();
    protected abstract void summon(Level level,Player owner);
}
