package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeKusabimaru {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.slashblade.named.kusabimaru");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 70);
		ItemSlashBlade.setBaseAttackModifier(tag1, 5.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/sekiro/kusabimaru");
		ItemSlashBlade.ModelName.set(tag1, "named/sekiro/kusabimaru");

		BladeLoader.getInstance().registerCustomItemStack("lastsmith.slashblade.named.kusabimaru", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("lastsmith.slashblade.named.kusabimaru");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		RecipesUtil.getInstance().addRecipe(TLSMain.MOD_ID,"lastsmith.slashblade.named.kusabimaru", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MOD_ID, "lastsmith.slashblade.named.kusabimaru"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("lastsmith.slashblade.named.kusabimaru"), request, 
			new Object[] {
				"DPS",
				"PSP",
				"BPD",
				'P', "leafSakura",
				'D', "dyeBlue",
				'S', SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr),
				'B', request
		}));

	}
}
