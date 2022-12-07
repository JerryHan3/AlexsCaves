package com.github.alexmodguy.alexscaves.client;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.client.model.baked.BakedModelFinalLayerFullbright;
import com.github.alexmodguy.alexscaves.client.particle.*;
import com.github.alexmodguy.alexscaves.client.render.blockentity.AmbersolBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.MagnetBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.*;
import com.github.alexmodguy.alexscaves.server.CommonProxy;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.block.blockentity.ACBlockEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.living.TremorsaurusEntity;
import com.github.alexmodguy.alexscaves.server.entity.util.HeadRotationEntityAccessor;
import com.github.alexmodguy.alexscaves.server.entity.util.MagneticEntityAccessor;
import com.github.alexmodguy.alexscaves.server.level.biome.ACBiomeRegistry;
import com.github.alexthe666.citadel.client.event.EventLivingRenderer;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.*;

@Mod.EventBusSubscriber(modid = AlexsCaves.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy extends CommonProxy {

    private static final List<String> FULLBRIGHTS = ImmutableList.of("alexscaves:ambersol#");
    private int lastTremorTick;
    private float[] randomTremorOffsets = new float[3];

    private List<UUID> blockedEntityRenders = new ArrayList<>();

    public void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::setupParticles);
    }

    public void clientInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::bakeModels);
        BlockEntityRenderers.register(ACBlockEntityRegistry.MAGNET.get(), MagnetBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.AMBERSOL.get(), AmbersolBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MOVING_METAL_BLOCK.get(), MovingMetalBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TELETOR.get(), TeletorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MAGNETIC_WEAPON.get(), MagneticWeaponRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MAGNETRON.get(), MagnetronRenderer::new);
        EntityRenderers.register(ACEntityRegistry.SUBTERRANODON.get(), SubterranodonRenderer::new);
        EntityRenderers.register(ACEntityRegistry.VALLUMRAPTOR.get(), VallumraptorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GROTTOCERATOPS.get(), GrottoceratopsRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TRILOCARIS.get(), TrilocarisRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TREMORSAURUS.get(), TremorsaurusRenderer::new);
        Sheets.addWoodType(ACBlockRegistry.PEWEN_WOOD_TYPE);
    }

    public static void setupParticles(RegisterParticleProvidersEvent registry) {
        AlexsCaves.LOGGER.debug("Registered particle factories");
        registry.register(ACParticleRegistry.SCARLET_MAGNETIC_ORBIT.get(), new MagneticOrbitParticle.ScarletFactory());
        registry.register(ACParticleRegistry.AZURE_MAGNETIC_ORBIT.get(), new MagneticOrbitParticle.AzureFactory());
        registry.register(ACParticleRegistry.SCARLET_MAGNETIC_FLOW.get(), new MagneticFlowParticle.ScarletFactory());
        registry.register(ACParticleRegistry.AZURE_MAGNETIC_FLOW.get(), new MagneticFlowParticle.AzureFactory());
        registry.register(ACParticleRegistry.GALENA_DEBRIS.get(), GalenaDebrisParticle.Factory::new);
        registry.register(ACParticleRegistry.FLY.get(), FlyParticle.Factory::new);
        registry.register(ACParticleRegistry.WATER_TREMOR.get(), WaterTremorParticle.Factory::new);
    }

    @SubscribeEvent
    public void setupEntityRotations(EventLivingRenderer.SetupRotations event) {
        if (event.getEntity() instanceof MagneticEntityAccessor magnetic) {
            float width = event.getEntity().getBbHeight();
            float height = event.getEntity().getBbHeight();
            float progress = magnetic.getAttachmentProgress(event.getPartialTicks());
            float prevProg = 1F - progress;
            float bodyRot = 180.0F - event.getBodyYRot();
            if (magnetic.getMagneticAttachmentFace().getAxis() != Direction.Axis.Y) {
                event.getPoseStack().mulPose(Vector3f.YN.rotationDegrees(1F * bodyRot));
            }
            rotateForAngle(event.getEntity(), event.getPoseStack(), magnetic.getPrevMagneticAttachmentFace(), prevProg, width, height);
            rotateForAngle(event.getEntity(), event.getPoseStack(), magnetic.getMagneticAttachmentFace(), progress, width, height);
        }
    }

    @SubscribeEvent
    public void preRenderLiving(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof HeadRotationEntityAccessor magnetic) {
            magnetic.setMagnetHeadRotation();
        }
        if(blockedEntityRenders.contains(event.getEntity().getUUID())){
            event.setCanceled(true);
            blockedEntityRenders.remove(event.getEntity().getUUID());
            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
        }
    }

    @SubscribeEvent
    public void postRenderLiving(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof HeadRotationEntityAccessor magnetic) {
            magnetic.resetMagnetHeadRotation();
        }
    }

    @SubscribeEvent
    public void postRenderStage(RenderLevelStageEvent event) {
        if(event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS){
            RenderSystem.runAsFancy(() -> AmbersolBlockRenderer.renderEntireBatch(event.getLevelRenderer(), event.getPoseStack(), event.getRenderTick(), event.getCamera(), event.getPartialTick()));
        }
    }


    @SubscribeEvent
    public void computeCameraAngles(ViewportEvent.ComputeCameraAngles event) {
        Entity player = Minecraft.getInstance().getCameraEntity();
        if(player != null && player.isOnGround()){
            float tremorAmount = 0;
            double shakeDistanceScale = 20D;
            double distance = Double.MAX_VALUE;
            float partialTick = Minecraft.getInstance().getPartialTick();
            AABB aabb = player.getBoundingBox().inflate(shakeDistanceScale);
            for(TremorsaurusEntity tremorsaurus : Minecraft.getInstance().level.getEntitiesOfClass(TremorsaurusEntity.class, aabb)){
                tremorAmount = Math.max(tremorsaurus.getScreenShakeAmount(partialTick), 0);
                if(tremorsaurus.distanceTo(player) < distance){
                    distance = tremorsaurus.distanceTo(player);
                }
            }
            if(tremorAmount > 0){
                if(lastTremorTick != player.tickCount){
                    RandomSource rng = player.getLevel().random;
                    randomTremorOffsets[0] = rng.nextFloat();
                    randomTremorOffsets[1] = rng.nextFloat();
                    randomTremorOffsets[2] = rng.nextFloat();
                    lastTremorTick = player.tickCount;
                }
                double intensity = (1D - (distance / shakeDistanceScale)) * tremorAmount * Minecraft.getInstance().options.screenEffectScale().get();
                event.getCamera().move(randomTremorOffsets[0] * 0.2F * intensity, randomTremorOffsets[1] * 0.2F * intensity, randomTremorOffsets[2] * 0.5F * intensity);
            }
        }

    }

    @SubscribeEvent
    public void fogRender(ViewportEvent.RenderFog event){
        if(event.getMode() == FogRenderer.FogMode.FOG_TERRAIN){
            Entity player = Minecraft.getInstance().player;
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float nearness;
            if (i == 0) {
                nearness = ACBiomeRegistry.getBiomeFogNearness(player.level.getBiome(player.blockPosition()));
            } else {
                Vec3 vec31 = CubicSampler.gaussianSampleVec3(player.position(), (x, y, z) -> {
                    return new Vec3(ACBiomeRegistry.getBiomeFogNearness(player.level.getBiomeManager().getNoiseBiomeAtPosition(x, y, z)), 0, 0);
                });
                nearness = (float) vec31.x;
            }
            if(nearness != 1.0F){
                event.setCanceled(true);
                event.setNearPlaneDistance(event.getNearPlaneDistance() * nearness);
            }
        }
    }

    private void rotateForAngle(LivingEntity entity, PoseStack matrixStackIn, Direction rotate, float f, float width, float height) {
        boolean down = entity.zza < 0.0F;
        switch (rotate) {
            case DOWN:
                break;
            case UP:
                matrixStackIn.translate(0.0D, height * f, 0.0D);
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-180.0F * f));
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-180.0F * f));
                break;
            case NORTH:
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * f));
                matrixStackIn.translate(0.0D, -height * 0.2f * f, 0.0D);
                if (down) {
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F * f));
                }
                break;
            case SOUTH:
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180 * f));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * f));
                matrixStackIn.translate(0.0D, -height * 0.2f * f, 0.0D);
                if (down) {
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F * f));
                }
                break;
            case WEST:
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90 * f));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * f));
                matrixStackIn.translate(0.0D, -height * 0.2f * f, 0.0D);
                if (down) {
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F * f));
                }
                break;
            case EAST:
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-90 * f));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F * f));
                matrixStackIn.translate(0.0D, -height * 0.2f * f, 0.0D);
                if (down) {
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F * f));
                }
                break;
        }
    }

    private void bakeModels(final ModelEvent.BakingCompleted e) {
        for (ResourceLocation id : e.getModels().keySet()) {
            if (FULLBRIGHTS.contains(id.toString())) {
                e.getModels().put(id, new BakedModelFinalLayerFullbright(e.getModels().get(id)));
            }
        }
    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }


    public void blockRenderingEntity(UUID id) {
        blockedEntityRenders.add(id);
    }

    public void releaseRenderingEntity(UUID id) {
        blockedEntityRenders.remove(id);
    }

}