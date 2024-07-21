package fr.benjamindanlos.laptimes.F1.F12023.Models.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Visual tyre compound
 */
public enum VisualTyreCompound {
    UNKNOWN(0),
    F1_SOFT(16),
    F1_MEDIUM(17),
    F1_HARD(18),
    F1_INTER(7),
    F1_WET(8),
    F1_CLASSIC_DRY(9),
    F1_CLASSIC_WET(10),
    F2_SUPER_SOFT(19),
    F2_SOFT(20),
    F2_MEDIUM(21),
    F2_HARD(22),
    F2_WET(15);

    private static Map<Integer, VisualTyreCompound> map = new HashMap<>();

    static {
        for (VisualTyreCompound visualTyreCompound : VisualTyreCompound.values()) {
            map.put(visualTyreCompound.value, visualTyreCompound);
        }
    }

    private int value;

    VisualTyreCompound(int value) {
        this.value = value;
    }

    public static VisualTyreCompound valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
