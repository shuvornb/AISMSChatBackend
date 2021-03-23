package com.example.aismschatbackend;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class AIMLBot {
    public static void main(String[] args) throws IOException {
        Bot bot = new Bot(BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build());

        Chat chatSession = new Chat(bot);
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello there! Type Hi to start this session.");
        System.out.println("PLease avoid using '.' in your response");
        FileWriter fw = new FileWriter("my-file.xml");
        while(true) {
            System.out.print("User: ");
            String userStr = sc.nextLine();
            fw.write("USER:"+userStr+"\n");
            if (userStr.equals("END")) break;
            String botStr = chatSession.multisentenceRespond(userStr);
            System.out.println("Bot: " + botStr);
            fw.write("BOT:"+botStr+" \n ");
        }
        fw.close();
    }
}
