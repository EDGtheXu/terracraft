package com.theXu.terracraft0323.partical;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Rotations;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.SurfaceSystem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.*;
import org.joml.Math;

import java.awt.*;

public class DragonFireParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final boolean mirrored;
    Vector3f speedInit;

    public DragonFireParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {

        super(level, xCoord, yCoord, zCoord, xd, yd, zd);


        speedInit = new Vector3f((float) xd, (float) yd, (float) zd).normalize();


        //设置粒子初始旋转
        Vector3f curSpeed = new Vector3f((float) (xd), (float) (yd), (float) zd);
        curSpeed=curSpeed.add(curSpeed.cross((float) yd, (float) zd, (float) xd).normalize().mul(0.5f));


        this.xd = curSpeed.x ;
        this.yd = curSpeed.y ;
        this.zd = curSpeed.z ;



        this.scale(this.random.nextFloat() * 1.75f + 1f);

        this.lifetime = 100 + (int) (Math.random() * 10);
        sprites = spriteSet;
        this.setSpriteFromAge(spriteSet);
        //this.gravity = -0.015F;
        this.mirrored = this.random.nextBoolean();

    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
        super.render(buffer, renderInfo, partialTicks);
    }

    @Override
    public void tick() {
        super.tick();

        /*
        this.xd += this.random.nextFloat() / 5.0F * (float) (this.random.nextBoolean() ? 1 : -1);
        this.yd += this.random.nextFloat() / 10.0F;
        this.zd += this.random.nextFloat() / 5.0F * (float) (this.random.nextBoolean() ? 1 : -1);
*/


        float r = 0.5f;
        float rotAngle = 20f;
        Vector3f curSpeed = new Vector3f((float) xd, (float) yd, (float) zd);

        curSpeed = new Matrix4f()
                .translate(new Vector3f(0,0,0))
                .rotate(Math.toRadians(rotAngle), speedInit)
                .transformPosition(curSpeed);

        this.xd = curSpeed.x ;
        this.yd = curSpeed.y ;
        this.zd = curSpeed.z ;


        /**
        curSpeed = curSpeed.xRot((float) speedInit.get(Direction.Axis.X));
        curSpeed = curSpeed.yRot((float) speedInit.get(Direction.Axis.Y));
        curSpeed = curSpeed.zRot(rotAngle);
        curSpeed = curSpeed.yRot((float) -speedInit.get(Direction.Axis.Y));
        curSpeed = curSpeed.xRot((float) -speedInit.get(Direction.Axis.X));

        this.xd = curSpeed.x ;
        this.yd = curSpeed.y ;
        this.zd = curSpeed.z ;
         **/


        Color from = Color.CYAN;
        Color to = Color.blue;
        //this.setColor(0,0,from.getBlue()*1.0f/255);

        this.setColor(
                Math.lerp((float)from.getRed(), (float) to.getRed(), (float) this.age /this.lifetime),
                Math.lerp((float)from.getRed(),(float)to.getRed(), (float) this.age /this.lifetime),
                Math.lerp((float)from.getRed(),(float)to.getRed(), (float) this.age /this.lifetime)
        );

        //this.alpha = Math.lerp((float)from.getRed()/255,(float)to.getRed()/255, (float) this.age /this.lifetime);


        this.setSpriteFromAge(this.sprites);
//        if (age > lifetime * .5f && this.random.nextFloat() <= .35f) {
//            this.level.addParticle(ParticleTypes.SMOKE, this.x, this.y, this.z, this.xd, this.yd, this.zd);
//        }
    }
/*
    @Override
    protected float getU0() {
        return mirrored ? super.getU1() : super.getU0();
    }

    @Override
    protected float getU1() {
        return mirrored ? super.getU0() : super.getU1();
    }
*/
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new DragonFireParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

    @Override
    public int getLightColor(float p_107564_) {
        return 240;
    }
//
//    @Override
//    public float getQuadSize(float p_107567_) {
//        float f = ((float) this.age + p_107567_) / (float) this.lifetime;
//        f = 1.0F - f;
//        f *= f;
//        f = 1.0F - f;
//        return this.quadSize * f;
//    }
}
