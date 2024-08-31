package com.theXu.terracraft0323.attribute;

import com.theXu.terracraft0323.NeoMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {

    public static DeferredRegister<Attribute> AttributesTypes = DeferredRegister.create(Registries.ATTRIBUTE, NeoMod.MODID);

    private static Holder<Attribute> register(String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(NeoMod.MODID,name), attribute);
    }




    public static final DeferredHolder<Attribute,Attribute> FLY_REMAIN = AttributesTypes.register(
            "fly_time",
            ()->new RangedAttribute("attribute.can_fly:fly with gravity remaining time", 0.0, 0.0, 5.0).setSyncable(true)
    );
    public static final DeferredHolder<Attribute,Attribute> FLY_SPEED_H = AttributesTypes.register(
            "fly_speed_horizon",
            ()->new RangedAttribute("attribute.can_fly:fly speed horizon", 1.0, 0.0, 5.0).setSyncable(true)
    );
    public static final DeferredHolder<Attribute,Attribute> FLY_SPEED_V = AttributesTypes.register(
            "fly_speed_vertical",
            ()->new RangedAttribute("attribute.can_fly:fly speed vertical", 1.0, 0.0, 5.0).setSyncable(true)
    );


}
