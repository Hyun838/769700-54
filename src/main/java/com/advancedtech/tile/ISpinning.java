package com.advancedtech.tile;

/** Тайл с вращающейся частью: клиентский рендер берёт угол отсюда. */
public interface ISpinning {
    float getSpin(float partialTicks);
}
