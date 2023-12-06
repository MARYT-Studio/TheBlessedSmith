package cn.mmf.blessedsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.BlessedSmith;
import cn.mmf.blessedsmith.event.RegisterSlashBladeEvent;
import cn.mmf.blessedsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.blessedsmith.item.ItemSlashBladeNamedTBS;
import cn.mmf.blessedsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.blessedsmith.util.BladeUtil;
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
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "slashblade.named.nagasada");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 6.0F);

		ItemSlashBlade.TextureName.set(tag1, "named/namedblade/texture_nagasada");
		ItemSlashBlade.ModelName.set(tag1, "named/namedblade/model_stright");

		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.nagasada", customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.nagasada");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.nagasada", new RecipeAwakeBladeTLS(
			new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.nagasada"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("slashblade.named.nagasada"), request,
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
