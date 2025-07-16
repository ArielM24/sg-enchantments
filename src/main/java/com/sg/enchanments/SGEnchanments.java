package com.sg.enchanments;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SGEnchanments implements ModInitializer {
	public static final String MOD_ID = "sg-enchanments";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<RegistryKey<Enchantment>> tridentEnchantments = List.of(Enchantments.FIRE_ASPECT, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.LOOTING, Enchantments.BREACH, Enchantments.KNOCKBACK);
	public static final List<RegistryKey<Enchantment>> maceEnchantments = List.of(Enchantments.FIRE_ASPECT, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.LOOTING, Enchantments.IMPALING, Enchantments.KNOCKBACK);
	public static final List<RegistryKey<Enchantment>> swordEnchantments = List.of(Enchantments.BREACH, Enchantments.IMPALING);
	public static final List<RegistryKey<Enchantment>> axeEnchantments = List.of(Enchantments.FIRE_ASPECT, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.LOOTING, Enchantments.BREACH, Enchantments.KNOCKBACK);
	public static final List<RegistryKey<Enchantment>> crossbowEnchantments = List.of(Enchantments.FLAME, Enchantments.POWER, Enchantments.PUNCH, Enchantments.INFINITY);
	public static final List<RegistryKey<Enchantment>> bowEnchantments = List.of(Enchantments.PIERCING, Enchantments.MULTISHOT);
	public static final List<RegistryKey<Enchantment>> protectionEnchantments = List.of(Enchantments.PROTECTION, Enchantments.PROJECTILE_PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION);
	public static final List<RegistryKey<Enchantment>> shieldEnchantments = List.of(Enchantments.THORNS);
	private static DynamicRegistryManager dynamicRegistryManager;

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(server->{
			dynamicRegistryManager = server.getRegistryManager();
		});
	}

	public static boolean isSameEnchanment(Enchantment enchantment,RegistryKey<Enchantment>  enchanmentRegistry){
		boolean res=  dynamicRegistryManager.getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(enchantment).matchesKey(enchanmentRegistry);
		LOGGER.info("enchantment: "+enchantment.toString()+" key: " +enchanmentRegistry.getValue().toString()+" res: " + res);

		return res;
	}
	public static boolean canCombineEnchantments(RegistryKey<Enchantment>  first,RegistryKey<Enchantment>  second, Set<RegistryKey<Enchantment>> enchanments){
		//return enchanments.stream().anyMatch(it -> first.(it.getRegistry()));
		return true;
	}
	public static boolean containsEnchanment(Enchantment enchantment, List<RegistryKey<Enchantment>> enchanments){
		for(RegistryKey<Enchantment> e : enchanments){
			if(SGEnchanments.isSameEnchanment(enchantment, e)){
				return true;
			}
		}
		return false;
	}
}