package gd.rf.acro.givemehats;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import gd.rf.acro.givemehats.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixins;

import java.util.ArrayList;
import java.util.List;

public class GiveMeHats implements ModInitializer {

	public static final ItemGroup TAB = FabricItemGroupBuilder.build(
			new Identifier("givemehats", "hats_tab"),
			() -> new ItemStack(GiveMeHats.BOWLER_HAT_ITEM));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ConfigUtils.checkConfigs();
		genHatsList();
		registerItems();



		System.out.println("May you find fine hats!");


	}
	public static final BowlerHatItem BOWLER_HAT_ITEM = new BowlerHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final BunnyEarsItem BUNNY_EARS_ITEM = new BunnyEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SantaHatItem SANTAR_HAT_ITEM = new SantaHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SlimeHatItem SLIME_HAT_ITEM = new SlimeHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CowboyHatItem COWBOY_HAT_ITEM = new CowboyHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final SailorHatItem SAILOR_HAT_ITEM = new SailorHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FloatingHatItem FLOATING_HAT_ITEM = new FloatingHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final TopHatItem TOP_HAT_ITEM = new TopHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final TopestHatItem TOPEST_HAT_ITEM = new TopestHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FezHatItem FEZ_HAT_ITEM = new FezHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final DeerStalkerHatItem DEER_STALKER_HAT_ITEM = new DeerStalkerHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CatEarsItem CAT_EARS_HAT_ITEM = new CatEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final FoxEarsItem FOX_EARS_HAT_ITEM = new FoxEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final ElectricMouseEarsItem ELECTRIC_MOUSE_EARS_HAT_ITEM = new ElectricMouseEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final CrownItem CROWN_ITEM = new CrownItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final RussianHatItem RUSSIAN_HAT_ITEM = new RussianHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final StriderHatItem STRIDER_HAT_ITEM = new StriderHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WoolrusHatItem WOOLRUS_HAT_ITEM = new WoolrusHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final TaterHatItem LIL_TATER_HAT_ITEM = new TaterHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final BunnySpaceHelmet BUNNY_SPACE_HELMET = new BunnySpaceHelmet(new Item.Settings().group(GiveMeHats.TAB));
	public static final IrishHatItem IRISH_HAT_ITEM = new IrishHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final GolemBucketItem GOLEM_BUCKET_ITEM = new GolemBucketItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final JojoHatItem JOJO_HAT_ITEM = new JojoHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WolfEarsItem WOLF_EARS_ITEM = new WolfEarsItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final WitchHatItem WITCH_HAT_ITEM = new WitchHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final VikingHatItem VIKING_HAT_ITEM = new VikingHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final DwarvenHatItem DWARVEN_HAT_ITEM = new DwarvenHatItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final HippieVibesItem HIPPIE_VIBES_ITEM = new HippieVibesItem(new Item.Settings().group(GiveMeHats.TAB));
	public static final HaloItem HALO_ITEM = new HaloItem(new Item.Settings().group(GiveMeHats.TAB));

	public static List<Item> LOADED_HATS;
	public static void genHatsList()
	{
		LOADED_HATS = new ArrayList<>();
		if(ConfigUtils.config.getOrDefault("enable_bowlerhat","1").equals("1"))
		{LOADED_HATS.add( BOWLER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyears","1").equals("1"))
		{LOADED_HATS.add( BUNNY_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_bunnyspacehelmet","1").equals("1"))
		{LOADED_HATS.add( BUNNY_SPACE_HELMET);}
		if(ConfigUtils.config.getOrDefault("enable_catears","1").equals("1"))
		{LOADED_HATS.add( CAT_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_cowboyhat","1").equals("1"))
		{LOADED_HATS.add( COWBOY_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_crown","1").equals("1"))
		{LOADED_HATS.add( CROWN_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_deerstalkerhat","1").equals("1"))
		{LOADED_HATS.add( DEER_STALKER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_electricmouseears","1").equals("1"))
		{LOADED_HATS.add( ELECTRIC_MOUSE_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_fez","1").equals("1"))
		{LOADED_HATS.add( FEZ_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_floatinghat","1").equals("1"))
		{LOADED_HATS.add( FLOATING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_foxears","1").equals("1"))
		{LOADED_HATS.add( FOX_EARS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_golembucket","1").equals("1"))
		{LOADED_HATS.add( GOLEM_BUCKET_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_irishhat","1").equals("1"))
		{LOADED_HATS.add( IRISH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_jojohat","1").equals("1"))
		{LOADED_HATS.add( JOJO_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_russianhat","1").equals("1"))
		{LOADED_HATS.add( RUSSIAN_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_sailorhat","1").equals("1"))
		{LOADED_HATS.add( SAILOR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_santahat","1").equals("1"))
		{LOADED_HATS.add( SANTAR_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_slimehat","1").equals("1"))
		{LOADED_HATS.add( SLIME_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_striderhat","1").equals("1"))
		{LOADED_HATS.add( STRIDER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_taterhat","1").equals("1"))
		{LOADED_HATS.add( LIL_TATER_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_tophat","1").equals("1"))
		{LOADED_HATS.add( TOP_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_topesthat","1").equals("1"))
		{LOADED_HATS.add( TOPEST_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_witchhat","1").equals("1"))
		{LOADED_HATS.add( WITCH_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_wolfears","1").equals("1"))
		{LOADED_HATS.add( WOLF_EARS_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_woolrushat","1").equals("1"))
		{LOADED_HATS.add( WOOLRUS_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_vikinghat","1").equals("1"))
		{LOADED_HATS.add( VIKING_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_dwarvenhat","1").equals("1"))
		{LOADED_HATS.add( DWARVEN_HAT_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_hippievibes","1").equals("1"))
		{LOADED_HATS.add( HIPPIE_VIBES_ITEM);}
		if(ConfigUtils.config.getOrDefault("enable_halo","1").equals("1"))
		{LOADED_HATS.add( HALO_ITEM);}

	}

	private String cc2uc(String value)
	{
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";
		return value.replaceAll(regex, replacement).toLowerCase();
	}

	private void registerItems()
	{

		for(Item hat: LOADED_HATS)
		{
			Registry.register(Registry.ITEM, new Identifier("givemehats",cc2uc(hat.getClass().getSimpleName().replace("Item",""))), hat);
			TrinketRendererRegistry.registerRenderer(hat, (TrinketRenderer) hat);

		}

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
