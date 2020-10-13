package com.github.ElficTitious.finalreality.model.character.player.playercharacters;

import com.github.ElficTitious.finalreality.model.character.ICharacter;
import com.github.ElficTitious.finalreality.model.character.player.AbstractMageCharacter;
import com.github.ElficTitious.finalreality.model.weapon.weapons.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class WhiteWizard extends AbstractMageCharacter {

    public WhiteWizard(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name,
                       int healthPoints, int defense, int mana) {
        super(turnsQueue, name, healthPoints, defense, mana);
    }
}
