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
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeHakurouken {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "slashblade.named.hakurou_fake");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 70);
		ItemSlashBlade.setBaseAttackModifier(tag1, 4.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/hakurou/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/hakurou/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.hakurou_fake", customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.hakurou_fake");
		
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag3, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag3, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "slashblade.named.hakurou");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 40);
		ItemSlashBlade.setBaseAttackModifier(tag3, 6.0F);
		customblade3.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade3.addEnchantment(Enchantments.KNOCKBACK, 1);
		customblade3.addEnchantment(Enchantments.SMITE, 3);
		ItemSlashBlade.SpecialAttackType.set(tag3, 4);
		ItemSlashBlade.TextureName.set(tag3, "named/hakurou/texture");
		ItemSlashBlade.ModelName.set(tag3, "named/hakurou/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.hakurou", customblade3);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.hakurou");
		
		ItemStack customblade4 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag4 = new NBTTagCompound();
		customblade4.setTagCompound(tag4);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag4, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag4, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "slashblade.named.hakurou_nether");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag4, 40);
		ItemSlashBlade.setBaseAttackModifier(tag4, 7.0F);
		customblade4.addEnchantment(Enchantments.UNBREAKING, 3);
		customblade4.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 2);
		customblade4.addEnchantment(Enchantments.SMITE, 5);
		customblade4.addEnchantment(Enchantments.FIRE_ASPECT, 1);
		ItemSlashBlade.SpecialAttackType.set(tag4, 4);
		ItemSlashBlade.TextureName.set(tag4, "named/hakurou/texture_tx");
		ItemSlashBlade.ModelName.set(tag4, "named/hakurou/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.hakurou_nether", customblade4);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.hakurou_nether");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag = new NBTTagCompound();
		ItemSlashBlade.RepairCount.set(tag, 2);
		request.setTagCompound(tag);
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.hakurou_fake", new RecipeAwakeBladeTLS(
			new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.hakurou_fake"),
			"sakura_blade", BladeLoader.getInstance().getCustomBlade("slashblade.named.hakurou_fake"), request,
			new Object[] {
				"ADA",
				"PBP",
				"SPS",
				'A', "gemDiamond",
				'D', "enderpearl",
				'P', "fullSakura",
				'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
				'B', request
		}));
		ItemStack request_3 = BladeLoader.getInstance().getCustomBlade("slashblade.named.hakurou_fake");
		NBTTagCompound tag3 = ItemSlashBlade.getItemTagCompound(request_3);
		ItemSlashBlade.KillCount.set(tag3, 500);
		ItemSlashBlade.ProudSoul.set(tag3, 5000);
		ItemSlashBlade.RepairCount.set(tag3, 1);
		request_3.addEnchantment(Enchantments.SMITE, 1);
		request_3.setTagCompound(tag3);
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.hakurou", new RecipeAwakeBladeTLS(
			new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.hakurou"),
			"sakura_blade", BladeLoader.getInstance().getCustomBlade("slashblade.named.hakurou"), request_3,
			new Object[] {
				"DPS",
				"PBP",
				"SPD",
				'D', "blockSakura",
				'P', "blockEmerald",
				'S', "blockQuartz",
				'B', request_3
		}));
		ItemStack request_4 = BladeLoader.getInstance().getCustomBlade("slashblade.named.hakurou");
		NBTTagCompound tag4 = ItemSlashBlade.getItemTagCompound(request_4);
		ItemSlashBlade.KillCount.set(tag4, 1000);
		ItemSlashBlade.ProudSoul.set(tag4, 25000);
		ItemSlashBlade.RepairCount.set(tag4, 5);
		request_4.addEnchantment(Enchantments.FIRE_ASPECT, 1);
		request_4.setTagCompound(tag4);
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.hakurou_nether", new RecipeAwakeBladeTLS(
			new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.hakurou_nether"),
			"sharpness", BladeLoader.getInstance().getCustomBlade("slashblade.named.hakurou_nether"), request_4,
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'D', "sphereSakura",
				'P', "obsidian",
				'S', "netherStar",
				'B', request_4
		}));
	}
}
