package cn.mmf.blessedsmith.blades.compat;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.blessedsmith.BlessedSmith;
import cn.mmf.blessedsmith.blades.BladeLoader;
import cn.mmf.blessedsmith.event.RegisterSlashBladeEvent;
import cn.mmf.blessedsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.blessedsmith.item.ItemSlashBladeNamedTBS;
import cn.mmf.blessedsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.blessedsmith.util.BladeUtil;
import ic2.api.item.IC2Items;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeIC2 {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		if (!Loader.isModLoaded("ic2"))
			return;
		ItemStack customblade = new ItemStack(BladeLoader.euBlade, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "slashblade.named.nanosaber");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, -1);
		ItemSlashBlade.setBaseAttackModifier(tag1, 11.0f);
		ItemSlashBlade.TextureName.set(tag1, "named/nanosaber/texture");
		BladeUtil.getInstance().TextureOnName.set(tag1, "named/nanosaber/texture_on");
		ItemSlashBlade.ModelName.set(tag1, "named/nanosaber/model");
		BladeUtil.getInstance().ModelOnName.set(tag1, "named/nanosaber/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 1);
		ItemSlashBlade.StandbyRenderType.set(tag1, 2);
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.nanosaber", customblade);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.nanosaber");

		ItemStack customblade1 = new ItemStack(BladeLoader.euBlade, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade1.setTagCompound(tag11);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "slashblade.named.quantumsaber");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, -1);
		ItemSlashBlade.setBaseAttackModifier(tag11, 21.0f);
		ItemSlashBlade.TextureName.set(tag11, "named/nanosaber/texture_1");
		BladeUtil.getInstance().TextureOnName.set(tag11, "named/nanosaber/texture_1_on");
		ItemSlashBlade.ModelName.set(tag11, "named/nanosaber/model");
		BladeUtil.getInstance().ModelOnName.set(tag11, "named/nanosaber/model");
		ItemSlashBlade.SpecialAttackType.set(tag11, 1);
		ItemSlashBlade.StandbyRenderType.set(tag11, 2);
		BladeLoader.getInstance().registerCustomItemStack("slashblade.named.quantumsaber", customblade1);
		ItemSlashBladeNamedTBS.NamedBlades.add("slashblade.named.quantumsaber");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("ic2"))
			return;
		ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
		ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
		ItemStack blade = BladeLoader.getInstance().getCustomBlade("slashblade.named.nanosaber");
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.nanosaber",
			new RecipeAwakeBladeTLS(new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.nanosaber"),
				"bewitched_blade", blade, custombladeReqired,
				new Object[] {
						"IAI",
						"CBC",
						"IDI",
						'A', "circuitBasic",
						'B', custombladeReqired,
						'C', "plateDenseIron",
						'D', IC2Items.getItem("energy_crystal"),
						'I', IC2Items.getItem("crafting", "alloy")
		}));
		NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(blade);
		ItemSlashBlade.RepairCount.set(reqTag2, Integer.valueOf(10));
		RecipesUtil.getInstance().addRecipe(BlessedSmith.MOD_ID,"slashblade.named.quantumsaber",
			new RecipeAwakeBladeTLS(new ResourceLocation(BlessedSmith.MOD_ID, "slashblade.named.quantumsaber"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("slashblade.named.quantumsaber"), blade,
				new Object[] {
						"IAI",
						"CBC",
						"IDI",
						'A', "plateDenseSteel",
						'B', blade,
						'C', "circuitAdvanced",
						'D', IC2Items.getItem("lapotron_crystal"),
						'I', IC2Items.getItem("crafting", "iridium")
		}));
	}
}
