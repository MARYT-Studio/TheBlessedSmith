package cn.mmf.lastsmith.blades.sweapon;

import cn.mcmod_mmf.mmlib.util.OreWildcardIngredient;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeSweapon {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "lastsmith.slashblade.named.sweapon");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 5.0F);
		ItemSlashBlade.SpecialAttackType.set(tag1, 264);
		ItemSlashBlade.TextureName.set(tag1, "named/sweapon/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/sweapon/model");

		BladeLoader.getInstance().registerCustomItemStack("lastsmith.slashblade.named.sweapon", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("lastsmith.slashblade.named.sweapon");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		RecipesUtil.getInstance().addRecipe(TLSMain.MOD_ID,"lastsmith.slashblade.named.sweapon", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MOD_ID, "lastsmith.slashblade.named.sweapon"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("lastsmith.slashblade.named.sweapon"), request, 
			new Object[] {
				" PS",
				" PS",
				" BD",
				'P', "ingotIron",
				'S', "dyeBlue",
				'D', new OreWildcardIngredient("toolForginghammer"),
				'B', request
		}));

	}
}
