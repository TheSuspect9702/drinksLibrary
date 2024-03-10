package pawlowski.dawid.drinkslibrary.Components;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToUuidConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(String source) {
        return  UUID.fromString(source);
    }
}
