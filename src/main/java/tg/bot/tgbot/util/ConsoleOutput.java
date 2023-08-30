package tg.bot.tgbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleOutput {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void welcome() {
        String msg =
                "[START] " + DTF.format(LocalDateTime.now()) +
                " | Reverse Bot has successfully started";
        System.out.println(msg);
    }

    public static void success(String username, String chatID) {
        String msg =
                "[ACTION] " + DTF.format(LocalDateTime.now()) +
                " | Successfully responded in chat with t.me/" + username + " (" + chatID + ")";
        System.out.println(msg);
    }

    public static void fail(String username, String chatID) {
        String msg =
                "[ERROR] " + DTF.format(LocalDateTime.now()) +
                " | EXCEPTION HAS OCCURRED IN CHAT WITH t.me/" + username + " (" + chatID + ")";
        System.out.println(msg);
    }
}
