package cn.mmf.blessedsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.BlessedSmith;
import cn.mmf.blessedsmith.event.RegisterSlashBladeEvent;
import cn.mmf.blessedsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.blessedsmith.item.ItemSlashBladeNamedTBS;
import cn.mmf.blessedsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.blessedsmith.se.SELoader;
import cn.mmf.blessedsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeSagequoia {
	private static final String name = "slashblade.named.sagequoia";

	@SubscribeEvent
	public static void init(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade.setTagCompound(tag);
		ItemSlashBladeNamed.CurrentItemName.set(tag, name);
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(113));
		ItemSlashBlade.AttackAmplifier.set(tag, 2F);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 15.0F);
		ItemSlashBlade.TextureName.set(tag, "named/sagequoia/texture");
		ItemSlashBlade.ModelName.set(tag, "named/sagequoia/model");
		ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(5));
		ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
		BladeLoader.getInstance().registerCustomItemStack(name, customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add(name);
		customblade.addEnchantment(Enchantments.UNBREAKING, 10);
		customblade.addEnchantment(Enchantments.SHARPNESS, 7);
		SpecialEffects.addEffect(customblade, SELoader.EXTREME_SHARPNESS);
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack custombladeReqired = SlashBlade.findItemStack("slashblade", "slashblade.named.tagayasan", 1);
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(2300));
		ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("slashblade", "sphere_bladesoul", 1);

		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,name, new RecipeAwakeBladeTLS(new ResourceLocation(BlessedSmith.MOD_ID, name),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade(name),custombladeReqired,new Object[] {
				"ADA",
				"CBC",
				"ADA",
				'A', itemSphereBladeSoul,
				'B', custombladeReqired,
				'C', Items.ENDER_EYE,
				'D', "enderpearl"
		}));
	}
}
