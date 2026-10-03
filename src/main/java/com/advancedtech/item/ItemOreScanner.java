package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import com.advancedtech.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Резонатор-сканер: вместо бура - ПКМ отправляет импульс, который "прозванивает" куб 25x25x25
 * и показывает ближайшие жилы каждой руды со смещением X/Y/Z. 96 использований, чинится энергоячейкой.
 */
public class ItemOreScanner extends Item {
    public static final int RADIUS = 12;

    public ItemOreScanner() {
        setRegistryName(AdvancedTech.MODID, "ore_scanner");
        setTranslationKey(AdvancedTech.MODID + ".ore_scanner");
        setCreativeTab(CreativeTabs.TOOLS);
        setMaxStackSize(1);
        setMaxDamage(96);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.get("energy_cell");
    }

    private static class Hit {
        ItemStack icon; int count; double best = Double.MAX_VALUE; BlockPos nearest;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand hand) {
        ItemStack stack = p.getHeldItem(hand);
        if (p.getCooldownTracker().hasCooldown(this)) return new ActionResult<>(EnumActionResult.PASS, stack);
        p.getCooldownTracker().setCooldown(this, 40);
        if (w.isRemote) return new ActionResult<>(EnumActionResult.SUCCESS, stack);

        w.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.BLOCK_NOTE_BELL, SoundCategory.PLAYERS, 1.0F, 1.6F);
        BlockPos c = p.getPosition();
        Map<String, Hit> hits = new LinkedHashMap<>();
        Map<IBlockState, String> cache = new HashMap<>();
        Map<IBlockState, ItemStack> icons = new HashMap<>();

        BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos();
        for (int dx = -RADIUS; dx <= RADIUS; dx++)
            for (int dy = -RADIUS; dy <= RADIUS; dy++)
                for (int dz = -RADIUS; dz <= RADIUS; dz++) {
                    m.setPos(c.getX() + dx, c.getY() + dy, c.getZ() + dz);
                    if (m.getY() < 0 || m.getY() > 255) continue;
                    IBlockState st = w.getBlockState(m);
                    Block b = st.getBlock();
                    if (b == net.minecraft.init.Blocks.AIR || b == net.minecraft.init.Blocks.STONE
                            || b == net.minecraft.init.Blocks.DIRT || b == net.minecraft.init.Blocks.GRASS) continue;
                    String key = cache.get(st);
                    if (key == null) {
                        key = "";
                        Item it = Item.getItemFromBlock(b);
                        if (it != net.minecraft.init.Items.AIR) {
                            ItemStack ic = new ItemStack(it, 1, b.getMetaFromState(st));
                            int[] ids = OreDictionary.getOreIDs(ic);
                            for (int id : ids) {
                                if (OreDictionary.getOreName(id).startsWith("ore")) { key = OreDictionary.getOreName(id); break; }
                            }
                            if (!key.isEmpty()) icons.put(st, ic);
                        }
                        cache.put(st, key);
                    }
                    if (key.isEmpty()) continue;
                    Hit h = hits.get(key);
                    if (h == null) { h = new Hit(); h.icon = icons.get(st); hits.put(key, h); }
                    h.count++;
                    double d = dx * dx + dy * dy + dz * dz;
                    if (d < h.best) { h.best = d; h.nearest = m.toImmutable(); }
                }

        p.sendMessage(new TextComponentTranslation("msg.advancedtech.scan.header", RADIUS));
        if (hits.isEmpty()) {
            p.sendMessage(new TextComponentTranslation("msg.advancedtech.scan.none"));
        } else {
            for (Hit h : hits.values()) {
                BlockPos n = h.nearest;
                String off = String.format("X%+d Y%+d Z%+d", n.getX() - c.getX(), n.getY() - c.getY(), n.getZ() - c.getZ());
                TextComponentTranslation name = new TextComponentTranslation(h.icon.getTranslationKey() + ".name");
                p.sendMessage(new TextComponentTranslation("msg.advancedtech.scan.line", name, h.count, off));
            }
        }
        if (!p.capabilities.isCreativeMode) stack.damageItem(1, p);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
