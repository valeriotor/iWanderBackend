package com.valeriotor.iWanderBackend.util;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IntRangeTests {

    @Test
    public void testSublist() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) list.add(Integer.valueOf(i));
        IntRange rangeLarge = IntRange.of(0, 10);
        IntRange rangeSmall = IntRange.of(0, 3);
        IntRange rangeShifted = IntRange.of(7, 12);
        List<Number> largeSublist = rangeLarge.getSublist(list);
        List<Number> smallSublist = rangeSmall.getSublist(list);
        List<Number> shiftedSublist = rangeShifted.getSublist(list);

        assert largeSublist.size() == 10;
        assert smallSublist.size() == 3;
        assert smallSublist.size() == list.subList(0, 3).size();
        assert shiftedSublist.size() == 3;
    }

}
