package ru.bloking.api.command;

import api.longpoll.bots.model.objects.basic.Message;

public interface ICommand {
    boolean execute(final Message message);
}
