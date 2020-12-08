package com.github.ElficTitious.finalreality.model.character.player.playercharacters;

import com.github.ElficTitious.finalreality.model.character.ICharacter;
import com.github.ElficTitious.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ElficTitious.finalreality.model.weapon.weapons.Axe;
import com.github.ElficTitious.finalreality.model.weapon.weapons.Bow;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the information and behaviour of all Engineers in the game.
 *
 * @author Ismael Correa Arellano.
 */
public class Engineer extends AbstractPlayerCharacter {

    /**
     * Creates a new Engineer with a name, a given amount of health points, a given amount
     * of defense and the queue with the characters ready to play.
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name,
                    int healthPoints, int defense) {
        super(turnsQueue, name, healthPoints, defense);
    }

    @Override
    public void equipAxe(Axe axe) {
        if (this.isAlive()) { //if this engineer is alive
            super.setEquippedWeapon(axe); //We set the equipped weapon to the given axe.
        }
        else {
            throw new AssertionError("Dead Character");
        }
    }

    @Override
    public void equipBow(Bow bow) {
        if (this.isAlive()) { //if this engineer is alive
            super.setEquippedWeapon(bow); //We set the equipped weapon to the given bow.
        }
        else {
            throw new AssertionError();
        }
    }
}
