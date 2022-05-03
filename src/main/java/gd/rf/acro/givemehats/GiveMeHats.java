package gd.rf.acro.givemehats;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gd.rf.acro.givemehats.items.*;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixins;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
@Mod("givemehats")
public class GiveMeHats {


	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,"givemehats");
	public static final CreativeModeTab TAB = new CreativeModeTab("givemehats_tab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(GiveMeHats.BOWLER_HAT_ITEM.get());
		}
	};
	public GiveMeHats()
	{

		ConfigUtils.checkConfigs();
		genHatsList();

		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		MinecraftForge.EVENT_BUS.register(this);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		System.out.println("May you find fine hats!");
		Mixins.addConfiguration("gmh.mixins.json");
	}
	private void enqueueIMC(final InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("head").size(1).build());
	}

	public static final RegistryObject<BowlerHatItem> BOWLER_HAT_ITEM = ITEMS.register("bowler_hat", BowlerHatItem::new);
	public static final RegistryObject<BunnyEarsItem> BUNNY_EARS_ITEM = ITEMS.register("bunny_ears", BunnyEarsItem::new);
	public static final RegistryObject<SantaHatItem> SANTAR_HAT_ITEM = ITEMS.register("santa_hat", SantaHatItem::new);
	public static final RegistryObject<SlimeHatItem> SLIME_HAT_ITEM = ITEMS.register("slime_hat", SlimeHatItem::new);
	public static final RegistryObject<CowboyHatItem> COWBOY_HAT_ITEM = ITEMS.register("cowboy_hat", CowboyHatItem::new);
	public static final RegistryObject<SailorHatItem> SAILOR_HAT_ITEM = ITEMS.register("sailor_hat", SailorHatItem::new);
	public static final RegistryObject<FloatingHatItem> FLOATING_HAT_ITEM = ITEMS.register("floating_hat", FloatingHatItem::new);
	public static final RegistryObject<TopHatItem> TOP_HAT_ITEM = ITEMS.register("top_hat", TopHatItem::new);
	public static final RegistryObject<TopestHatItem> TOPEST_HAT_ITEM = ITEMS.register("topest_hat", TopestHatItem::new);
	public static final RegistryObject<FezHatItem> FEZ_HAT_ITEM = ITEMS.register("fez_hat", FezHatItem::new);
	public static final RegistryObject<DeerStalkerHatItem> DEER_STALKER_HAT_ITEM = ITEMS.register("deer_stalker_hat", DeerStalkerHatItem::new);
	public static final RegistryObject<CatEarsItem> CAT_EARS_HAT_ITEM = ITEMS.register("cat_ears", CatEarsItem::new);
	public static final RegistryObject<FoxEarsItem> FOX_EARS_HAT_ITEM = ITEMS.register("fox_ears", FoxEarsItem::new);
	public static final RegistryObject<ElectricMouseEarsItem> ELECTRIC_MOUSE_EARS_HAT_ITEM = ITEMS.register("electric_mouse_ears", ElectricMouseEarsItem::new);
	public static final RegistryObject<CrownItem> CROWN_ITEM = ITEMS.register("crown", CrownItem::new);
	public static final RegistryObject<RussianHatItem> RUSSIAN_HAT_ITEM = ITEMS.register("russian_hat", RussianHatItem::new);
	public static final RegistryObject<StriderHatItem> STRIDER_HAT_ITEM = ITEMS.register("strider_hat", StriderHatItem::new);
	public static final RegistryObject<WoolrusHatItem> WOOLRUS_HAT_ITEM = ITEMS.register("woolrus_hat", WoolrusHatItem::new);
	public static final RegistryObject<TaterHatItem> LIL_TATER_HAT_ITEM = ITEMS.register("tater_hat", TaterHatItem::new);
	public static final RegistryObject<BunnySpaceHelmet> BUNNY_SPACE_HELMET = ITEMS.register("bunny_space_helmet", BunnySpaceHelmet::new);
	public static final RegistryObject<IrishHatItem> IRISH_HAT_ITEM = ITEMS.register("irish_hat", IrishHatItem::new);
	public static final RegistryObject<GolemBucketItem> GOLEM_BUCKET_ITEM = ITEMS.register("golem_bucket", GolemBucketItem::new);
	public static final RegistryObject<JojoHatItem> JOJO_HAT_ITEM = ITEMS.register("jojo_hat", JojoHatItem::new);
	public static final RegistryObject<WolfEarsItem> WOLF_EARS_ITEM = ITEMS.register("wolf_ears", WolfEarsItem::new);
	public static final RegistryObject<WitchHatItem> WITCH_HAT_ITEM = ITEMS.register("witch_hat", WitchHatItem::new);
	public static final RegistryObject<VikingHatItem> VIKING_HAT_ITEM = ITEMS.register("viking_hat", VikingHatItem::new);
	public static final RegistryObject<DwarvenHatItem> DWARVEN_HAT_ITEM = ITEMS.register("dwarven_hat", DwarvenHatItem::new);
	public static final RegistryObject<HippieVibesItem> HIPPIE_VIBES_ITEM = ITEMS.register("hippie_vibes", HippieVibesItem::new);
	public static final RegistryObject<HaloItem> HALO_ITEM = ITEMS.register("halo", HaloItem::new);

	public static List<RegistryObject> LOADED_HATS;
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



	private void clientSetup(final FMLClientSetupEvent clientSetupEvent)
	{
		for(RegistryObject hat: LOADED_HATS)
		{
			CuriosRendererRegistry.register((Item) hat.get(), (Supplier<ICurioRenderer>) hat.get());
		}

	}

	public static void translateToFace(PoseStack matrices, EntityModel<? extends LivingEntity> model,
									   LivingEntity entity, float headYaw, float headPitch) {

		if (entity.isVisuallySwimming() || entity.isFallFlying()) {
			if(model instanceof PlayerModel)
			{
				PlayerModel ctx = (PlayerModel) model;

				matrices.mulPose(Vector3f.ZP.rotationDegrees(ctx.head.yRot));
			}


			matrices.mulPose(Vector3f.XP.rotationDegrees(-45.0F));
		} else {

			if (entity.isShiftKeyDown() && !model.riding) {
				matrices.translate(0.0F, 0.25F, 0.0F);
			}
			matrices.mulPose(Vector3f.YP.rotationDegrees(headYaw));
			matrices.mulPose(Vector3f.XP.rotationDegrees(headPitch));
		}
		matrices.translate(0.0F, -0.25F, -0.3F);
	}
	
}
