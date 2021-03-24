package com.example.aismschatbackend;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class AIMLBot {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        Bot bot = new Bot(BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build());

        Chat chatSession = new Chat(bot);
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello there! Type Hi to start this session.");
        System.out.println("PLease avoid using '.' in your response");
        //create the directory
        File theDir = new File("C:\\Users\\trina\\IdeaProjects\\AISMSChatBackend\\chatlog");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        long start_timestamp = System.currentTimeMillis();

        //XML FIle formatting
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // root element
        Element rootElement = doc.createElement("activity1");
        doc.appendChild(rootElement);

        // chat element
        Element chat = doc.createElement("chat");
        rootElement.appendChild(chat);

        // setting attribute to element
        Attr attr = doc.createAttribute("session");
        attr.setValue("user_id");
        chat.setAttributeNode(attr);
        while(true) {
            System.out.print("User: ");
            String userStr = sc.nextLine();
            // user_input element
            Element user = doc.createElement("user");
            Attr attrType = doc.createAttribute("string");
            attrType.setValue("input");
            user.setAttributeNode(attrType);
            user.appendChild(doc.createTextNode(userStr));
            chat.appendChild(user);

            if (userStr.equals("END")) break;
            String botStr = chatSession.multisentenceRespond(userStr);
            System.out.println("Bot: " + botStr);
            // bot_input element
            Element chatBot = doc.createElement("chatBot");
            Attr attrType1 = doc.createAttribute("string");
            attrType1.setValue("input");
            chatBot.setAttributeNode(attrType1);
            chatBot.appendChild(doc.createTextNode(botStr));
            chat.appendChild(chatBot);

        }
        long end_timestamp = System.currentTimeMillis();

        String fileName = start_timestamp + "-" + end_timestamp + "-user_id";
        fileName = fileName.replace(':','-');
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\trina\\IdeaProjects\\AISMSChatBackend\\chatlog\\"+fileName+".xml"));
        transformer.transform(source, result);
    }
}
