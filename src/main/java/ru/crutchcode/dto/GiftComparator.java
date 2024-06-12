package ru.crutchcode.dto;

import java.util.Comparator;

public class GiftComparator implements Comparator<Gift> {

    public final String category;

    public GiftComparator(String category) {
        this.category = category;
    }

    @Override
    public int compare(Gift left, Gift right) {
        var relRight = right.getRelativity().stream().filter(p -> p.getCategory().equals(category)).findFirst();
        var relLeft = left.getRelativity().stream().filter(p -> p.getCategory().equals(category)).findFirst();
        return
                relLeft.map(
                        relativity -> relRight.map(
                                giftRelativity -> Double.compare(
                                        relativity.getPercents(), giftRelativity.getPercents())
                        ).orElse(1)
                ).orElse(-1);
    }
}
