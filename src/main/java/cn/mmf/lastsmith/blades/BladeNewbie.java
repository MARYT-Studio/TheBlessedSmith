package cn.mmf.lastsmith.blades;


import cn.mmf.lastsmith.item.ItemSlashBladeDetuneTLS;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.EnumSet;
import java.util.List;

public class BladeNewbie extends ItemSlashBladeDetuneTLS {

    public BladeNewbie(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
        super(par2EnumToolMaterial, baseAttackModifiers);
    }

    /**
     * So that we can add 2 more attributes to newbie blade
     * @author RisingInIris2017
     */
    @Override
    public void updateAttackAmplifier(EnumSet<SwordType> swordType, NBTTagCompound tag, EntityLivingBase el, ItemStack sitem){
        float tagAttackAmplifier = this.AttackAmplifier.get(tag);


        float baseModif = getBaseAttackModifiers(tag);
        float attackAmplifier = 0;

        int rank = StylishRankManager.getStylishRank(el);

        if(rank < 3 || swordType.contains(SwordType.Broken) || swordType.contains(SwordType.Sealed)){
            attackAmplifier = 2 - baseModif;
        }else if( rank == 7 || 5 <= rank && swordType.contains(SwordType.FiercerEdge)){
            float level;
            if(el instanceof EntityPlayer)
                level = ((EntityPlayer)el).experienceLevel;
            else
                level = el.getHealth();

            float max = RefineBase + RepairCount.get(tag);

            attackAmplifier = Math.min(level, max);
        }

        if(tagAttackAmplifier != attackAmplifier)
        {
            this.AttackAmplifier.set(tag, attackAmplifier);

            NBTTagList attrTag = null;

            attrTag = new NBTTagList();
            tag.setTag("AttributeModifiers",attrTag);

            attrTag.appendTag(
                    getAttrTag(
                            SharedMonsterAttributes.ATTACK_DAMAGE.getName()
                            , new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)(attackAmplifier + baseModif), 0)
                            , EntityEquipmentSlot.MAINHAND)
            );
            attrTag.appendTag(
                    getAttrTag(SharedMonsterAttributes.ATTACK_SPEED.getName()
                            , new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0)
                            , EntityEquipmentSlot.MAINHAND)
            );
            attrTag.appendTag(
                    getAttrTag(
                            SharedMonsterAttributes.ARMOR.getName(),
                            new AttributeModifier("Armor modifier", 4.0, 0),
                            EntityEquipmentSlot.MAINHAND)
            );
            attrTag.appendTag(
                    getAttrTag(
                            SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(),
                            new AttributeModifier("Armor toughness", 2.0, 0),
                            EntityEquipmentSlot.MAINHAND)
            );
            el.getAttributeMap().removeAttributeModifiers(sitem.getAttributeModifiers(EntityEquipmentSlot.MAINHAND));
            el.getAttributeMap().applyAttributeModifiers(sitem.getAttributeModifiers(EntityEquipmentSlot.MAINHAND));
        }
    }

    /**
     * Add dummy blade smith name
     * @author RisingInIris2017
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltipList, ITooltipFlag tooltipFlag) {
        tooltipList.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":" + I18n.format("blades.crafter"));
        if (StringUtil.getInstance().isAltKeyDown()) {
            super.addInformation(stack, world, tooltipList, tooltipFlag);
        } else {
            tooltipList.add(I18n.format("lastsmith.info.hold_alt_for_details"));
        }
    }
}
