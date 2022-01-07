package ru.bloking.api.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.bloking.api.permission.Permission;

import javax.persistence.*;

@Table(name = "UraniumUser")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class UraniumUser {
    @Id
    @Getter
    @NonNull
    Integer id;

    @Getter
    @Column(name = "fullname")
    @NonNull
    @NonFinal
    String fullName;

    @Column(name = "nickname")
    @Getter
    @Setter
    @NonNull
    @NonFinal
    String nickName;

    @Column(name = "permission")
    @Getter
    @Setter
    @NonNull
    @NonFinal
    int permission;

    public UraniumUser() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj  == this) return true;
        if (obj == null) return false;
        if (getClass()  != obj.getClass()) return false;

        return ((UraniumUser) obj).id.equals(this.id);
    }
}

