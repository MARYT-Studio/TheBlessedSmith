package cn.mmf.blessedsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.BlessedSmith;
import cn.mmf.blessedsmith.event.DropEvent;
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
public class BladeRequiem {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade.setTagCompound(tag);
		BladeUtil.getInstance().IsFakeBlade.set(tag, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "slashblade.named.scorn_rust");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 20);
		ItemSlashBlade.setBaseAttackModifier(tag, 4.0F);
		ItemSlashBlade.TextureName.set(tag, "named/scorn/texture_1");
		ItemSlashBlade.ModelName.set(tag, "named/scorn/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.scorn_rust", customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.scorn_rust");

		ItemStack customblade1 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade1.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "slashblade.named.scorn_sealed");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 60);
		ItemSlashBlade.setBaseAttackModifier(tag1, 6.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/scorn/texture_0");
		ItemSlashBlade.ModelName.set(tag1, "named/scorn/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.scorn_sealed", customblade1);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.scorn_sealed");

		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag2, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag2, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "slashblade.named.scorn");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 60);
		ItemSlashBlade.setBaseAttackModifier(tag2, 9.0F);
	    ItemSlashBlade.SpecialAttackType.set(tag2, Integer.valueOf(5));
	    ItemSlashBlade.StandbyRenderType.set(tag2, Integer.valueOf(3));
	    customblade2.addEnchantment(Enchantments.UNBREAKING,2);
	    customblade2.addEnchantment(Enchantments.SHARPNESS,3);
	    customblade2.addEnchantment(Enchantments.SMITE,2);
	    customblade2.addEnchantment(Enchantments.POWER,1);
		ItemSlashBlade.TextureName.set(tag2, "named/scorn/texture");
		ItemSlashBlade.ModelName.set(tag2, "named/scorn/model");
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.scorn", customblade2);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.scorn");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		
		DropEvent.registerEntityDrop(new ResourceLocation("twilightforest", "lich"),
				new ResourceLocation(BlessedSmith.MOD_ID, "bewitched_blade"), 1F,
				BladeLoader.getInstance().getCustomBlade("slashblade.named.scorn_rust"), true);
		ItemStack custombladeReqired = BladeLoader.getInstance().getCustomBlade("slashblade.named.scorn_rust");
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(100));
		ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(1000));
		ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("slashblade", "sphere_bladesoul", 1);

		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.scorn_sealed",new RecipeAwakeBladeTLS(new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.scorn_sealed"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("slashblade.named.scorn_sealed"), custombladeReqired,
			new Object[] {
				"ADA",
				"DBD",
				"ADA", 
				'A', itemSphereBladeSoul,
				'B', custombladeReqired,
				'D', "fullSakura"
		}));
		ItemStack custombladeReqired1 = BladeLoader.getInstance().getCustomBlade("slashblade.named.scorn_sealed");
		NBTTagCompound reqTag1 = ItemSlashBlade.getItemTagCompound(custombladeReqired1);
		ItemSlashBlade.KillCount.set(reqTag1, Integer.valueOf(500));
		ItemSlashBlade.ProudSoul.set(reqTag1, Integer.valueOf(5000));
		ItemSlashBlade.RepairCount.set(reqTag1, Integer.valueOf(1));
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.scorn",new RecipeAwakeBladeTLS(new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.scorn"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("slashblade.named.scorn"), custombladeReqired1,
			new Object[] {
				"DAD",
				"ABA",
				"DAD", 
				'A', itemSphereBladeSoul,
				'B', custombladeReqired1,
				'D', "blockSakura"
		}));
	}
}
