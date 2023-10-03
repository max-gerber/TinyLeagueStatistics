package abracadonut.Objects;

import java.util.*;

public class SuperTypes {
    boolean creature = false;
    boolean instant = false;
    boolean sorcery = false;
    boolean enchantment = false;
    boolean artifact = false;
    boolean land = false;
    boolean planeswalker = false;
    boolean battle = false;

    public SuperTypes(List<String> superTypes) {
        if (superTypes.contains("Creature")) {
            creature = true;
        }
        if (superTypes.contains("Instant")) {
            instant = true;
        }
        if (superTypes.contains("Sorcery")) {
            sorcery = true;
        }
        if (superTypes.contains("Enchantment")) {
            enchantment = true;
        }
        if (superTypes.contains("Artifact")) {
            artifact = true;
        }
        if (superTypes.contains("Land")) {
            land = true;
        }
        if (superTypes.contains("Planeswalker")) {
            planeswalker = true;
        }
        if (superTypes.contains("Battle")) {
            battle = true;
        }
    }
    public boolean isCreature() {
        return creature;
    }
    public boolean isInstant() {
        return instant;
    }
    public boolean isSorcery() {
        return sorcery;
    }
    public boolean isEnchantment() {
        return enchantment;
    }
    public boolean isArtifact() {
        return artifact;
    }
    public boolean isLand() {
        return land;
    }
    public boolean isPlaneswalker() {
        return planeswalker;
    }
    public boolean isBattle() {
        return battle;
    }
}
