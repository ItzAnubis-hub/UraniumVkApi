package ru.bloking.api.permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Permission {
    final static List<Permission> PERMISSION_LIST = new LinkedList<Permission>();

    @Getter
    int level;

    @Getter
    String name;

    public static Permission create(final int level,
                                    final String name
    ) {
        val permission = new Permission(level, name);

        PERMISSION_LIST.add(permission);

        return permission;
    }

    public static Permission getPermission(final String name) {
        return PERMISSION_LIST.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static Permission getPermission(final int level) {
        return PERMISSION_LIST.stream()
                .filter(i -> i.getLevel() == level)
                .findFirst()
                .orElse(null);
    }
}
