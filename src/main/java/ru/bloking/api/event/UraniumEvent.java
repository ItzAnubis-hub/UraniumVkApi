package ru.bloking.api.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class UraniumEvent {
    boolean isCancelled;

    public final void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public abstract void handle();

    public boolean isCancelled() {
        return isCancelled;
    }
}
