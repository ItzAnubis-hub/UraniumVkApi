package ru.bloking.api.command;

import api.longpoll.bots.model.objects.basic.Message;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import ru.bloking.api.event.UraniumCallEvent;
import ru.bloking.api.event.impl.UraniumCommandEvent;
import ru.bloking.api.user.UraniumUserMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandManager {
    final static Map<String, ICommand> I_COMMAND_LINKED_LIST = new LinkedHashMap<>();

    public void use(final Message message) {
        val text = message.getText();
        val command = I_COMMAND_LINKED_LIST.get(text);
        val uraniumCommandEvent = new UraniumCommandEvent();

        if (!UraniumUserMapper.isExist(message.getFromId())) {
            UraniumUserMapper.create(message);
            uraniumCommandEvent.setCancelled(true);
        }

        UraniumCallEvent.callEvent(uraniumCommandEvent);

        execute(message, command);
    }

    public static void create(final String execute,
                              final ICommand iCommand) {
        I_COMMAND_LINKED_LIST.put(execute, iCommand);
    }

    private void execute(final Message message,
                         final ICommand command) {
        CompletableFuture.runAsync(() -> command.execute(message));
    }
}
