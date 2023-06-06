package com.github.alexmodguy.alexscaves.server.misc;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ACDamageTypes {

    public static final ResourceKey<DamageType> ACID = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AlexsCaves.MODID, "acid"));
    public static final ResourceKey<DamageType> NUKE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AlexsCaves.MODID, "nuke"));
    public static final ResourceKey<DamageType> RADIATION = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AlexsCaves.MODID, "radiation"));

    public static final ResourceKey<DamageType> FORSAKEN_SONIC_BOOM = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AlexsCaves.MODID, "forsaken_sonic_boom"));

    public static DamageSource causeAcidDamage(RegistryAccess registryAccess){
        return new DamageSourceRandomMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(ACID), 1);
    }

    public static DamageSource causeRadiationDamage(RegistryAccess registryAccess){
        return new DamageSourceRandomMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(RADIATION), 2);
    }

    public static DamageSource causeNukeDamage(RegistryAccess registryAccess){
        return new DamageSourceRandomMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(NUKE), 3);
    }

    public static DamageSource causeForsakenSonicBoomDamage(RegistryAccess registryAccess, Entity source){
        return new DamageSourceRandomMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(FORSAKEN_SONIC_BOOM), source, 2);
    }

    private static class DamageSourceRandomMessages extends DamageSource {

        private int messageCount;

        public DamageSourceRandomMessages(Holder.Reference<DamageType> message, int messageCount) {
            super(message);
            this.messageCount = messageCount;
        }

        public DamageSourceRandomMessages(Holder.Reference<DamageType> message, Entity source, int messageCount) {
            super(message, source);
            this.messageCount = messageCount;
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity attacked) {
            int type = attacked.getRandom().nextInt(this.messageCount);
            String s = "death.attack." + this.getMsgId() + "_" + type;
            return Component.translatable(s, attacked.getDisplayName());
        }
    }
}
