package org.daylight.forcefullbright;

public class BrightnessState {
    private static boolean enabled = true;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void toggleState() {
        enabled = !enabled;
    }
}
