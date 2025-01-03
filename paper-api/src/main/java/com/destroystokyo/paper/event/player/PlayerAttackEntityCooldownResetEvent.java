package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Called when processing a player's attack on an entity when the player's attack strength cooldown is reset
 */
@NullMarked
public class PlayerAttackEntityCooldownResetEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity attackedEntity;
    private final float cooledAttackStrength;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerAttackEntityCooldownResetEvent(final Player player, final Entity attackedEntity, final float cooledAttackStrength) {
        super(player);
        this.attackedEntity = attackedEntity;
        this.cooledAttackStrength = cooledAttackStrength;
    }

    /**
     * Get the value of the players cooldown attack strength when they initiated the attack
     *
     * @return returns the original player cooldown value
     */
    public float getCooledAttackStrength() {
        return this.cooledAttackStrength;
    }

    /**
     * Returns the entity attacked by the player
     *
     * @return the entity attacked by the player
     */
    public Entity getAttackedEntity() {
        return this.attackedEntity;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If an attack cooldown event is cancelled, the players attack strength will remain at the same value instead of being reset.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Cancelling this event will prevent the target player from having their cooldown reset from attacking this entity
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
