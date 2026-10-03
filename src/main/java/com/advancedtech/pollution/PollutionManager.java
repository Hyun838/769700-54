package com.advancedtech.pollution;

import com.advancedtech.config.ATConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;

public class PollutionManager {

    public static PollutionData data(World w) {
        MapStorage st = w.getPerWorldStorage();
        PollutionData d = (PollutionData) st.getOrLoadData(PollutionData.class, PollutionData.ID);
        if (d == null) {
            d = new PollutionData();
            st.setData(PollutionData.ID, d);
        }
        return d;
    }

    public static void add(World w, BlockPos pos, float amount) {
        if (!ATConfig.enablePollution || w.isRemote || amount <= 0) return;
        PollutionData d = data(w);
        d.map.merge(ChunkPos.asLong(pos.getX() >> 4, pos.getZ() >> 4), amount, Float::sum);
        d.markDirty();
    }

    /** Забрать до {@code max} единиц загрязнения из чанка. Возвращает, сколько реально забрали. */
    public static float drain(World w, BlockPos pos, float max) {
        if (w.isRemote || max <= 0) return 0;
        PollutionData d = data(w);
        long key = ChunkPos.asLong(pos.getX() >> 4, pos.getZ() >> 4);
        Float v = d.map.get(key);
        if (v == null || v <= 0) return 0;
        float got = Math.min(v, max);
        d.map.put(key, v - got);
        d.markDirty();
        return got;
    }

    public static float get(World w, BlockPos pos) {
        if (w.isRemote) return 0;
        Float v = data(w).map.get(ChunkPos.asLong(pos.getX() >> 4, pos.getZ() >> 4));
        return v == null ? 0 : v;
    }
}
