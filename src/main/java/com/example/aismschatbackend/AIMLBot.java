package com.example.aismschatbackend;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import java.util.Scanner;

public class AIMLBot {
    public static void main(String[] args) {
        Bot bot = new Bot(BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build());

        Chat chatSession = new Chat(bot);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String userStr = sc.nextLine();
            if(userStr.equals("exit")) break;
            String botStr = chatSession.multisentenceRespond(userStr);
            System.out.println("Bot: " + botStr);
        }
    }
}
