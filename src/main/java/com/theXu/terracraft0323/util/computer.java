package com.theXu.terracraft0323.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class computer {

    public static double angle(Vec3 line1,Vec3 line2){
        return Math.asin(line1.dot(line2)/line1.length()/line2.length());
    }



}
