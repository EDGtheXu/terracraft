package com.theXu.terracraft0323.ui.jewelrySlots;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;

public enum terraEquipmentSlot implements StringRepresentable {
    MAINHAND(terraEquipmentSlot.Type.HAND, 0, 0, "mainhand"),
    OFFHAND(terraEquipmentSlot.Type.HAND, 1, 5, "offhand"),
    FEET(terraEquipmentSlot.Type.HUMANOID_ARMOR, 0, 1, 1, "feet"),
    LEGS(terraEquipmentSlot.Type.HUMANOID_ARMOR, 1, 1, 2, "legs"),
    CHEST(terraEquipmentSlot.Type.HUMANOID_ARMOR, 2, 1, 3, "chest"),
    HEAD(terraEquipmentSlot.Type.HUMANOID_ARMOR, 3, 1, 4, "head"),
    BODY(terraEquipmentSlot.Type.ANIMAL_ARMOR, 0, 1, 6, "body"),
    SUB1(terraEquipmentSlot.Type.SUB_ARMOR,0,1,7,"jewel1"),
    SUB2(terraEquipmentSlot.Type.SUB_ARMOR,1,1,8,"jewel2"),
    SUB3(terraEquipmentSlot.Type.SUB_ARMOR,2,1,9,"jewel3"),
    SUB4(terraEquipmentSlot.Type.SUB_ARMOR,3,1,10,"jewel4"),
    SUB5(terraEquipmentSlot.Type.SUB_ARMOR,4,1,11,"jewel5"),
    SUB6(terraEquipmentSlot.Type.SUB_ARMOR,5,1,12,"jewel6"),
    SUB7(terraEquipmentSlot.Type.SUB_ARMOR,6,1,13,"jewel7");




    public static final int NO_COUNT_LIMIT = 0;
    public static final StringRepresentable.EnumCodec<terraEquipmentSlot> CODEC = StringRepresentable.fromEnum(terraEquipmentSlot::values);
    private final terraEquipmentSlot.Type type;
    private final int index;
    private final int countLimit;
    private final int filterFlag;
    private final String name;

    private terraEquipmentSlot(terraEquipmentSlot.Type type, int index, int countLimit, int filterFlag, String name) {
        this.type = type;
        this.index = index;
        this.countLimit = countLimit;
        this.filterFlag = filterFlag;
        this.name = name;
    }

    private terraEquipmentSlot(terraEquipmentSlot.Type type, int index, int filterFlag, String name) {
        this(type, index, 0, filterFlag, name);
    }

    public terraEquipmentSlot.Type getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public int getIndex(int baseIndex) {
        return baseIndex + this.index;
    }

    public ItemStack limit(ItemStack stack) {
        return this.countLimit > 0 ? stack.split(this.countLimit) : stack;
    }

    public int getFilterFlag() {
        return this.filterFlag;
    }

    public String getName() {
        return this.name;
    }

    public boolean isArmor() {
        return this.type == terraEquipmentSlot.Type.HUMANOID_ARMOR || this.type == terraEquipmentSlot.Type.ANIMAL_ARMOR;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static terraEquipmentSlot byName(String targetName) {
        terraEquipmentSlot terraEquipmentSlot = CODEC.byName(targetName);
        if (terraEquipmentSlot != null) {
            return terraEquipmentSlot;
        } else {
            throw new IllegalArgumentException("Invalid slot '" + targetName + "'");
        }
    }

    public static enum Type {
        HAND,
        HUMANOID_ARMOR,
        ANIMAL_ARMOR,

        SUB_ARMOR;
    }
}
