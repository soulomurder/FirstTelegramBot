package tg.bot.tgbot.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    public static String botName = "Reverse Bot";
    public static String token = "";
}
