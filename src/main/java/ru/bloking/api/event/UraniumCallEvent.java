package ru.bloking.api.event;

import com.sun.istack.NotNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class UraniumCallEvent {
    public void callEvent(@NotNull final UraniumEvent event) {
        if (!event.isCancelled()) event.handle();
    }
}
