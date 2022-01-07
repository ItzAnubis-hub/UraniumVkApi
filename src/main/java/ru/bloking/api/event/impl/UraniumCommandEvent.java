package ru.bloking.api.event.impl;

import ru.bloking.api.event.UraniumEvent;
import ru.bloking.api.user.UraniumUserMapper;

public final class UraniumCommandEvent extends UraniumEvent {
    @Override
    public void handle() {
        System.out.println("Called event: uranium command event");
        UraniumUserMapper.updateUsers();
    }
}
