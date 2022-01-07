package ru.bloking;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.events.messages.MessageNew;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.bloking.api.command.CommandManager;
import ru.bloking.yml.YamlConfiguration;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class VkUranium extends LongPollBot {
    final static YamlConfiguration YAML_CONFIGURATION
            = new YamlConfiguration().loadConfig("src/main/resources/config.yml");

    @Getter
    final static VkUranium URANIUM = new VkUranium();

    @Getter
    final static VkBotsMethods METHODS = URANIUM.vk;

    final static CommandManager MANAGER = new CommandManager();

    @NonFinal @Getter @Setter
    String token;

    @Override
    public void onMessageNew(final MessageNew messageNew) {
        MANAGER.use(messageNew.getMessage());
    }

    @SneakyThrows
    public void start() {
        URANIUM.startPolling();
    }

    @Override
    public String getAccessToken() {
        if (getToken() == null) setToken(YAML_CONFIGURATION.getProperty("token").toString());
        return getToken();
    }
}
