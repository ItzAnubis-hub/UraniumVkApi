package ru.bloking.api.message;

import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.bloking.VkUranium;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class UraniumMessage {
    @Getter @NonNull String text;

    @Getter @NonNull boolean async;

    @Getter @NonNull Message message;

    final static VkBotsMethods methods = VkUranium.getMETHODS();

    public void send() {
        if (async)
            sendAsync();
        else
            sendSync();
    }



    @SneakyThrows
    private void sendSync() {
        methods.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(getText())
                .execute();
    }

    private void sendAsync() {
        methods.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(getText())
                .executeAsync();
    }
}
