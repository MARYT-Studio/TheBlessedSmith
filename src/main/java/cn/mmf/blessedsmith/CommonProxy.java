package cn.mmf.blessedsmith;

import cn.mcmod_mmf.mmlib.compat.PatchouliCompat;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.advancement.HasAdvancementTrigger;
import cn.mmf.blessedsmith.advancement.SlashBladeItemPredicate;
import cn.mmf.blessedsmith.blades.BladeLoader;
import cn.mmf.blessedsmith.block.BlockLoader;
import cn.mmf.blessedsmith.event.RegisterSlashBladeEvent;
import cn.mmf.blessedsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.blessedsmith.item.ItemLoader;
import cn.mmf.blessedsmith.packet.PacketKeyMessage;
import cn.mmf.blessedsmith.packet.PacketKeyMessageHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.advancements.critereon.ItemPredicates;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;

@EventBusSubscriber
public class CommonProxy {
	public static final CreativeTabs tab = new BlessedSmithCreativeTabs();
	public static final HasAdvancementTrigger ADVANCEMENT_UNLOCKED = CriteriaTriggers.register(new HasAdvancementTrigger());
    private static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(BlessedSmith.MOD_ID);
    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }
	public void preInit(FMLPreInitializationEvent event) {
		BlockLoader.getInstance().register();
		ItemLoader.getInstance().register();
		BladeLoader.getInstance().register();
	}
	
    public void init(FMLInitializationEvent event) { 
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,9), new ItemStack(ItemLoader.BLADE_HEATTED,1,0), 1F);
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,10), new ItemStack(ItemLoader.BLADE_HEATTED,1,1), 1F);
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,11), new ItemStack(ItemLoader.BLADE_HEATTED,1,2), 1F);
    	
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,29), new ItemStack(ItemLoader.BLADE_HEATTED,1,5), 1F);
    	GameRegistry.addSmelting(new ItemStack(ItemLoader.BLADE,1,30), new ItemStack(ItemLoader.BLADE_HEATTED,1,6), 1F);
    	
    	RecipesUtil.getInstance().addOreDictionarySmelting("blockSakura", new ItemStack(ItemLoader.MATERIALS,1,6));
    	ItemPredicates.register(new ResourceLocation(BlessedSmith.MOD_ID, "slashblade"), SlashBladeItemPredicate::new);
    	MinecraftForge.EVENT_BUS.post(new RegisterSlashBladeEvent(event));
    	network.registerMessage(new PacketKeyMessageHandler(),PacketKeyMessage.class,0,Side.SERVER);
    	if(Loader.isModLoaded(Thaumcraft.MODID)){
      		 ThaumcraftApi.registerResearchLocation(new ResourceLocation(BlessedSmith.MOD_ID,"research/research.json"));
       	}
    }

    public void postInit(FMLPostInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.post(new RegisterSlashBladeRecipeEvent(event));
    	if(BlessedSmithConfig.spawn_first_with_book)
    		PatchouliCompat.addBook("smith_guide", BlessedSmith.MOD_ID);
    }
	public boolean doesPlayerHaveAdvancement(EntityPlayer player, ResourceLocation advId) {
		if (player instanceof EntityPlayerMP) {
			Advancement adv = ((EntityPlayerMP) player).getServerWorld().getAdvancementManager().getAdvancement(advId);
			if(adv == null){
				return false;
			}
			return ((EntityPlayerMP) player).getAdvancements().getProgress(adv).isDone();
		}
		return false;
	}
	// Advancement Trigger
	@SubscribeEvent
	public static void onAdvancementGet(AdvancementEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		if (player instanceof EntityPlayerMP) {
			ADVANCEMENT_UNLOCKED.trigger((EntityPlayerMP) player, event.getAdvancement());
		}
	}
}