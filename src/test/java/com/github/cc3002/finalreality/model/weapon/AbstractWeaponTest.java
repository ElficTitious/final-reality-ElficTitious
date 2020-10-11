package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.github.ElficTitious.finalreality.model.weapon.IWeapon;

public abstract class AbstractWeaponTest {

    public void checkConstruction(IWeapon expectedWeapon, IWeapon testEqualWeapon,
                                  IWeapon sameClassDifferentWeapon, IWeapon differentClassWeapon) {
        assertEquals(expectedWeapon, expectedWeapon);
        assertEquals(expectedWeapon, testEqualWeapon);
        assertNotEquals(expectedWeapon, sameClassDifferentWeapon);
        assertNotEquals(expectedWeapon, differentClassWeapon);
    }
}
