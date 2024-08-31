package com.theXu.terracraft0323.item.terraBow;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public abstract class terraria_bow_base extends CrossbowItem {
    protected int internal = 1000;
    private boolean ok = true;
    public int damage;

    public terraria_bow_base(int damage) {
        super(createProperties());
        this.damage = damage;
    }

    public static Item.Properties createProperties(){
        return new Item.Properties()
                .rarity(Rarity.RARE)
                .fireResistant()
                .component(DataComponents.UNBREAKABLE,new Unbreakable(true))
                .stacksTo(1);

    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext pContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        // 添加基础的悬停文本
        componentList.add(Component.literal("TerraCraft Bow").withColor(Color.green.getRGB()));

        super.appendHoverText(itemStack, pContext, componentList, tooltipFlag);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {

        return super.use(level,playerIn,handIn);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int count) {
        System.out.println("use");
        livingEntity.stopUsingItem();
        if(!(livingEntity instanceof Player))return;
        if(!level.isClientSide()) {
            if(!ok){
                livingEntity.stopUsingItem();
                return ;
            }
            ok  = false;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ok = true;
                }
            },getAttackInternal());


            genWaves(level, (Player) livingEntity,InteractionHand.MAIN_HAND);

        }

    }

    @Override
    public  @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }
    public int getAttackInternal(){
        return internal;
    };

    protected static final ChargingSounds DEFAULT_SOUNDS = new ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END)
    );

    protected ChargingSounds getChargingSounds(ItemStack stack) {
        return EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_SOUNDS);
    }
    protected abstract void genWaves(Level level, Player playerIn, InteractionHand handIn);
    public abstract ItemStack createStack();

}
