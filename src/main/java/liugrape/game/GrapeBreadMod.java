package liugrape.game;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrapeBreadMod implements ModInitializer {
	public static final String MOD_ID = "grapebread-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Consumable GRAPE_BREAD_CONSUMABLE = Consumables.defaultFood()
			// The duration is in ticks, 20 ticks = 1 second
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 72000, 254), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 72000, 5), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 72000, 4), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH, 72000, 254), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 72000, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 72000, 1), 1.0f))
			.build();
	public static final FoodProperties GRAPE_BREAD = new FoodProperties.Builder()
			.nutrition(4)
			.saturationModifier(0.3f)
			.alwaysEdible()
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 72000, 254), 1.0f))
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 72000, 5), 1.0f))
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 72000, 4), 1.0f))
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH, 72000, 4), 1.0f))
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 72000, 1), 1.0f))
			//.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 72000, 1), 1.0f))
			.build();
	public static final Item GRAPE_BREAD_ITEM = registerFoodItem("grape_bread", GRAPE_BREAD);

	private static Item registerFoodItem(String name, FoodProperties foodProperties) {
		Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name);
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
		return Registry.register(
				BuiltInRegistries.ITEM,
				id,
				new Item(new Item.Properties()
						.setId(itemKey)
						.food(foodProperties, GRAPE_BREAD_CONSUMABLE)
				)
		);
	}

	@Override
	public void onInitialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
				.register(entries -> entries.accept(GRAPE_BREAD_ITEM));
		LOGGER.info("Initialized {}", MOD_ID);
	}
}
