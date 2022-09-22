package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class TemplateTest {

    private final Map<String, String> args = new HashMap<>();
    private final String template = "I am a ${name}, Who are ${subject}";

    @Test
    void whenKeyExist() {
        args.put("name", "Aleksey");
        args.put("subject", "you");
        Template result = new Template();
        assertThat(result.produce(template, args)).isEqualTo("I am a Aleksey, Who are you");
    }

    @Test
    void whenKeyNotExist() {
        args.put("name", "Aleksey");
        Template result = new Template();
        assertThatThrownBy(() -> result.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("в шаблоне есть ключи, которых нет в карте");
    }

    @Test
    void whenWrongKeyExist() {
        args.put("name", "Aleksey");
        args.put("city", "you");
        Template result = new Template();
        assertThatThrownBy(() -> result.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("лишний ключ");
    }
}