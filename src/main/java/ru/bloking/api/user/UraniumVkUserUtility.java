package ru.bloking.api.user;

import api.longpoll.bots.model.objects.basic.User;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.bloking.VkUranium;

@UtilityClass
public final class UraniumVkUserUtility {
    @SneakyThrows
    public User getUserData(final Integer id) {
        val usersGet = VkUranium.getMETHODS()
                .users
                .get()
                .setUserIds(id)
                .execute()
                .getResponseObject();

        return usersGet.get(0);
    }

}
