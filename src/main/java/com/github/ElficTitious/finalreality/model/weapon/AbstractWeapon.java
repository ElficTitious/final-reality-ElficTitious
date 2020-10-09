package com.github.ElficTitious.finalreality.model.weapon;

public abstract class AbstractWeapon implements IWeapon{

    private final String name;
    private final int damage;
    private final int weight;

    /**
     * Creates a weapon with a name, a base damage and weight.
     */
    public AbstractWeapon(final String name, final int damage, final int weight) {
        this.name = name;
        this.damage = damage;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
