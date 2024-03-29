package com.github.ElficTitious.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.ElficTitious.finalreality.controller.handlers.IEventHandler;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds the information and behaviour of all enemies in the game.
 *
 * @author Ismael Correa Arellano.
 */
public class Enemy implements ICharacter{

    protected final BlockingQueue<ICharacter> turnsQueue;
    private ScheduledExecutorService scheduledExecutor;
    private final String name;
    private int healthPoints;
    private final int defense;
    private final int weight;
    private final int attackPower;

    private final PropertyChangeSupport enemyDeathEvent =
            new PropertyChangeSupport(this);
    private final PropertyChangeSupport enemyTurnEvent =
            new PropertyChangeSupport(this);
    private final PropertyChangeSupport nonEmptyQueueEvent =
            new PropertyChangeSupport(this);

    /**
     * Creates a new enemy with a name, a weight and the queue with the characters ready to
     * play.
     */
    public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                 @NotNull final String name, final int healthPoints, final int defense,
                 final int weight, final int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.defense = defense;
        this.weight = weight;
        this.attackPower = attackPower;
        this.turnsQueue = turnsQueue;
    }

    @Override
    public void attack(ICharacter character) {
        character.beingAttacked(this);
    }

    @Override
    public void beingAttacked(ICharacter character) {
        int currentHP = this.getHealthPoints();
        int damage = Math.max(0, character.getAttackPower() - this.getDefense());
        /*In order to not diminish the HP below zero, we define health points after
          being attacked as follows*/
        int afterAttackHP = Math.max(0, currentHP - damage);
        this.setHealthPoints(afterAttackHP);
        if (!this.isAlive()) {
            enemyDeathEvent.firePropertyChange("Dead enemy",
                    null, this);
        }
    }

    @Override
    public void turn() {
        // This enemy is sent as newValue.
        enemyTurnEvent.firePropertyChange("Enemy Turn", null, this);
    }

    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        var enemy = (Enemy) this;
        scheduledExecutor
                .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }

    /**
     * Adds this enemy to the turns queue if its alive. If a enemy is added to the
     * turns queue, it triggers a non empty queue event.
     */
    private void addToQueue() {
        if (this.isAlive()) {
            turnsQueue.add(this);
            nonEmptyQueueEvent.firePropertyChange("Non Empty Queue",
                    null, null);
        }
        scheduledExecutor.shutdown();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Checks if this enemy is alive.
     */
    public boolean isAlive() {
        return this.getHealthPoints() > 0;
    }

    @Override
    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Returns this enemy's weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns this enemy's attack power.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Sets this enemy's health points to the given integer.
     */
    public void setHealthPoints(int newHP) {
        this.healthPoints = newHP;
    }

    /**
     * Method that compares two enemies, returning if they are equal or not.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) { //If the Object is exactly the enemy, then they are equal.
            return true;
        }
        if (!(obj instanceof Enemy)) {
            return false;
        }
        /* An enemy is defined equal to another one if they have the
        same name.
        */
        final var enemy = (Enemy) obj;
        return getName().equals(enemy.getName());
    }

    /**
     * Returns this enemy's hashCode (according to the definition of the equals method).
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Adds the enemy death, turn and non empty queue handlers to the enemy death,
     * non empty queue and turn events.
     */
    public void addListeners(IEventHandler deathHandler, IEventHandler turnHandler,
                             IEventHandler nonEmptyQueueHandler) {
        enemyDeathEvent.addPropertyChangeListener(deathHandler);
        enemyTurnEvent.addPropertyChangeListener(turnHandler);
        nonEmptyQueueEvent.addPropertyChangeListener(nonEmptyQueueHandler);
    }
}
