package cn.mmf.blessedsmith.blades.sweapon;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.BlessedSmith;
import cn.mmf.blessedsmith.blades.BladeLoader;
import cn.mmf.blessedsmith.event.RegisterSlashBladeEvent;
import cn.mmf.blessedsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.blessedsmith.item.ItemSlashBladeNamedTBS;
import cn.mmf.blessedsmith.recipe.RecipeAwakeBladeTLS;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeEievui {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "slashblade.named.eievui");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 113);
		ItemSlashBlade.setBaseAttackModifier(tag1, 6.0F);
		ItemSlashBlade.AttackAmplifier.set(tag1, 2F);
		ItemSlashBlade.SpecialAttackType.set(tag1,264);
	    ItemSlashBlade.StandbyRenderType.set(tag1, 3);
	    ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		ItemSlashBlade.TextureName.set(tag1, "named/eievui/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/eievui/model");

		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.eievui", customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.eievui");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.eievui", new RecipeAwakeBladeTLS(
			new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.eievui"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("slashblade.named.eievui"), request,
			new Object[] {
				"SPS",
				"PBP",
				"SPS",
				'P', "leafSakura",
				'S', new ItemStack(Blocks.YELLOW_FLOWER),
				'B', request
		}));

	}
}