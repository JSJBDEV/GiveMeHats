package gd.rf.acro.givemehats;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import gd.rf.acro.givemehats.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;

import java.util.Map;

public class GiveMeHats implements ModInitializer {
	private static final Identifier DUNGEON_LOOT = new Identifier("minecraft","chests/simple_dungeon");
	private static final Identifier ZOMBIE = new Identifier("minecraft","entities/zombie");
	public static final ItemGroup TAB = FabricItemGroupBuilder.build(
			new Identifier("givemehats", "hats_tab"),
			() -> new ItemStack(GiveMeHats.BOWLER_HAT_ITEM));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ConfigUtils.checkConfigs();
		registerItems();
		Map<String,String> c = ConfigUtils.config;
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (DUNGEON_LOOT.equals(id)) {
				LootPool poolBuilder = FabricLootPoolBuilder.builder()
						.withEntry(ItemEntry.builder(GiveMeHats.BOWLER_HAT_ITEM).weight(Integer.parseInt(c.get("bowlerhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.BUNNY_EARS_ITEM).weight(Integer.parseInt(c.get("bunnyears"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.SAILOR_HAT_ITEM).weight(Integer.parseInt(c.get("sailorhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.SANTAR_HAT_ITEM).weight(Integer.parseInt(c.get("santahat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.TOP_HAT_ITEM).weight(Integer.parseInt(c.get("tophat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.TOPEST_HAT_ITEM).weight(Integer.parseInt(c.get("topesthat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.COWBOY_HAT_ITEM).weight(Integer.parseInt(c.get("cowboyhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.SLIME_HAT_ITEM).weight(Integer.parseInt(c.get("slimehat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.FEZ_HAT_ITEM).weight(Integer.parseInt(c.get("fez"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.CAT_EARS_HAT_ITEM).weight(Integer.parseInt(c.get("catears"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.FOX_EARS_HAT_ITEM).weight(Integer.parseInt(c.get("foxears"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.ELECTRIC_MOUSE_EARS_HAT_ITEM).weight(Integer.parseInt(c.get("electricmouseears"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.RUSSIAN_HAT_ITEM).weight(Integer.parseInt(c.get("russianhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.CROWN_ITEM).weight(Integer.parseInt(c.get("crown"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.STRIDER_HAT_ITEM).weight(Integer.parseInt(c.get("striderhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.WOOLRUS_HAT_ITEM).weight(Integer.parseInt(c.get("woolrushat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.LIL_TATER_HAT_ITEM).weight(Integer.parseInt(c.get("taterhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.BUNNY_SPACE_HELMET).weight(Integer.parseInt(c.get("spacehelmet"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.IRISH_HAT_ITEM).weight(Integer.parseInt(c.get("irishhat"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.WITCH_HAT_ITEM).weight(Integer.parseInt(c.getOrDefault("witchhat","1"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.WOLF_EARS_ITEM).weight(Integer.parseInt(c.getOrDefault("wolfears","1"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.JOJO_HAT_ITEM).weight(Integer.parseInt(c.getOrDefault("jojohat","1"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.GOLEM_BUCKET_ITEM).weight(Integer.parseInt(c.getOrDefault("golembucket","1"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.VIKING_HAT_ITEM).weight(Integer.parseInt(c.getOrDefault("vikinghat","1"))).build())
						.withEntry(ItemEntry.builder(GiveMeHats.DWARVEN_HAT_ITEM).weight(Integer.parseInt(c.getOrDefault("dwarvenhat","1"))).build())
						.withEntry(ItemEntry.builder(Items.AIR).weight(Integer.parseInt(c.get("air"))).build()).build();


				supplier.withPool(poolBuilder);
			}
		});

		System.out.println("May you find fine hats!");


	}
	public static final BowlerHatItem BOWLER_HAT_ITEM = new BowlerHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final BunnyEarsHatItem BUNNY_EARS_ITEM = new BunnyEarsHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SantarHatItem SANTAR_HAT_ITEM = new SantarHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SlimeHatItem SLIME_HAT_ITEM = new SlimeHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CowboyHatItem COWBOY_HAT_ITEM = new CowboyHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SailorHatItem SAILOR_HAT_ITEM = new SailorHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FloatingHatItem FLOATING_HAT_ITEM = new FloatingHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final TopHatItem TOP_HAT_ITEM = new TopHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final TopestHatItem TOPEST_HAT_ITEM = new TopestHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FezHatItem FEZ_HAT_ITEM = new FezHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final DeerStalkerHatItem DEER_STALKER_HAT_ITEM = new DeerStalkerHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CatEarsHatItem CAT_EARS_HAT_ITEM = new CatEarsHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FoxEarsHatItem FOX_EARS_HAT_ITEM = new FoxEarsHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final ElectricMouseEarsHatItem ELECTRIC_MOUSE_EARS_HAT_ITEM = new ElectricMouseEarsHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CrownItem CROWN_ITEM = new CrownItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final RussianHatItem RUSSIAN_HAT_ITEM = new RussianHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final StriderHatItem STRIDER_HAT_ITEM = new StriderHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WoolrusHatItem WOOLRUS_HAT_ITEM = new WoolrusHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final LilTaterHatItem LIL_TATER_HAT_ITEM = new LilTaterHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final BunnySpaceHelmet BUNNY_SPACE_HELMET = new BunnySpaceHelmet(new Item.Settings().group(GiveMeHats.TAB));
	public static final IrishHatItem IRISH_HAT_ITEM = new IrishHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final GolemBucketItem GOLEM_BUCKET_ITEM = new GolemBucketItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final JojoHatItem JOJO_HAT_ITEM = new JojoHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WolfEarsItem WOLF_EARS_ITEM = new WolfEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WitchHatItem WITCH_HAT_ITEM = new WitchHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final VikingHatItem VIKING_HAT_ITEM = new VikingHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final DwarvenHatItem DWARVEN_HAT_ITEM = new DwarvenHatItem(new Item.Settings().group(GiveMeHats.TAB));
	private void registerItems()
	{
		if(ConfigUtils.config.getOrDefault("enable_bowlerhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","bowler_hat"),BOWLER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyears","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","bunny_ears"),BUNNY_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyspacehelmet","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","bunny_space_helmet"),BUNNY_SPACE_HELMET);}
		if(ConfigUtils.config.getOrDefault("enable_catears","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","cat_ears"),CAT_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_cowboyhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","cowboy_hat"),COWBOY_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_crown","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","crown"),CROWN_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_deerstalkerhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","deerstalker_hat"),DEER_STALKER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_electricmouseears","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","electric_mouse_ears"),ELECTRIC_MOUSE_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_fez","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","fez_hat"),FEZ_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_floatinghat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","floating_hat"),FLOATING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_foxears","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","fox_ears"),FOX_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_golembucket","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","golem_bucket"),GOLEM_BUCKET_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_irishhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","irish_hat"),IRISH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_jojohat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","jojo_hat"),JOJO_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_russianhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","russian_hat"),RUSSIAN_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_sailorhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","sailor_hat"),SAILOR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_santahat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","santa_hat"),SANTAR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_slimehat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","slime_hat"),SLIME_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_striderhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","strider_hat"),STRIDER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_taterhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","tater_hat"),LIL_TATER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_tophat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","top_hat"),TOP_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_topesthat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","topest_hat"),TOPEST_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_witchhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","witch_hat"),WITCH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_wolfears","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","wolf_ears"),WOLF_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_woolrushat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","woolrus_hat"),WOOLRUS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_vikinghat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","viking_hat"),VIKING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_dwarvenhat","1").equals("1"))
		{Registry.register(Registry.ITEM,new Identifier("givemehats","dwarven_hat"),DWARVEN_HAT_ITEM);}

		if(ConfigUtils.config.getOrDefault("enable_bowlerhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(BOWLER_HAT_ITEM,BOWLER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyears","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(BUNNY_EARS_ITEM,BUNNY_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyspacehelmet","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(BUNNY_SPACE_HELMET,BUNNY_SPACE_HELMET);}
		if(ConfigUtils.config.getOrDefault("enable_catears","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(CAT_EARS_HAT_ITEM,CAT_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_cowboyhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(COWBOY_HAT_ITEM,COWBOY_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_crown","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(CROWN_ITEM,CROWN_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_deerstalkerhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(DEER_STALKER_HAT_ITEM,DEER_STALKER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_electricmouseears","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(ELECTRIC_MOUSE_EARS_HAT_ITEM,ELECTRIC_MOUSE_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_fez","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(FEZ_HAT_ITEM,FEZ_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_floatinghat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(FLOATING_HAT_ITEM,FLOATING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_foxears","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(FOX_EARS_HAT_ITEM,FOX_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_golembucket","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(GOLEM_BUCKET_ITEM,GOLEM_BUCKET_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_irishhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(IRISH_HAT_ITEM,IRISH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_jojohat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(JOJO_HAT_ITEM,JOJO_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_russianhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(LIL_TATER_HAT_ITEM,LIL_TATER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_sailorhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(RUSSIAN_HAT_ITEM,RUSSIAN_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_santahat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(SAILOR_HAT_ITEM,SAILOR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_slimehat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(SANTAR_HAT_ITEM,SANTAR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_striderhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(SLIME_HAT_ITEM,SLIME_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_taterhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(STRIDER_HAT_ITEM,STRIDER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_tophat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(TOP_HAT_ITEM,TOP_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_topesthat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(TOPEST_HAT_ITEM,TOPEST_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_witchhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(WITCH_HAT_ITEM,WITCH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_wolfears","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(WOLF_EARS_ITEM,WOLF_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_woolrushat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(WOOLRUS_HAT_ITEM,WOOLRUS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_vikinghat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(VIKING_HAT_ITEM,VIKING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_dwarvenhat","1").equals("1"))
		{TrinketRendererRegistry.registerRenderer(DWARVEN_HAT_ITEM,DWARVEN_HAT_ITEM);}

	}

	public static void translateToFace(MatrixStack matrices, EntityModel<? extends LivingEntity> model,
									   LivingEntity entity, float headYaw, float headPitch) {

		if (entity.isInSwimmingPose() || entity.isFallFlying()) {
			if(model instanceof PlayerEntityModel)
			{
				PlayerEntityModel<AbstractClientPlayerEntity> ctx = (PlayerEntityModel<AbstractClientPlayerEntity>) model;
				matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(ctx.head.roll));
			}
			matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
			matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-45.0F));
		} else {

			if (entity.isInSneakingPose() && !model.riding) {
				matrices.translate(0.0F, 0.25F, 0.0F);
			}
			matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
			matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(headPitch));
		}
		matrices.translate(0.0F, -0.25F, -0.3F);
	}
}
