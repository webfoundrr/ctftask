package ru.avitoctf.marketplugins.api;

public interface Plugin {
    double calculateDiscount(Object cart) throws Exception;
}
