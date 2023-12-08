package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeNagasada {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.slashblade.named.nagasada");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 6.0F);

		ItemSlashBlade.TextureName.set(tag1, "named/namedblade/texture_nagasada");
		ItemSlashBlade.ModelName.set(tag1, "named/namedblade/model_stright");

		BladeLoader.getInstance().registerCustomItemStack("lastsmith.slashblade.named.nagasada", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("lastsmith.slashblade.named.nagasada");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		RecipesUtil.getInstance().addRecipe(TLSMain.MOD_ID,"lastsmith.slashblade.named.nagasada", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MOD_ID, "lastsmith.slashblade.named.nagasada"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("lastsmith.slashblade.named.nagasada"), request, 
			new Object[] {
				"SDS",
				"PBP",
				"SDS",
				'P', "leafSakura",
				'D', "dyeBlue",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'B', request
		}));
	}
}
