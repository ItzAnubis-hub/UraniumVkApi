package ru.bloking.yml;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;

@FieldDefaults(makeFinal = true)
public final class YamlConfiguration {
    @NonFinal
    Yaml yaml;

    @NonFinal
    InputStream input;

    @Getter
    @Setter
    @NonFinal
    LinkedHashMap result;

    @SneakyThrows
    public YamlConfiguration loadConfig(final String path) {
        yaml  = new Yaml();
        input = new FileInputStream(path);

        System.out.println(String.format("loaded a new configuration by path: %s", path));

        setResult(yaml.load(input));

        return this;
    }

    public Object getProperty(final String name) {
        return result.get(name);
    }
}
