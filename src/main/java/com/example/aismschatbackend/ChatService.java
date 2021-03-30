package com.example.aismschatbackend;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.stereotype.Service;
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
import java.io.File;

@Service
public class ChatService {
    private Element chat;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private Bot bot;
    public Chat chatSession;

    public ChatService() throws ParserConfigurationException {

        bot = new Bot(BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build());
        chatSession = new Chat(bot);

        //create the directory
        File theDir = new File("C:\\Users\\shuvo\\Desktop\\NIJ Project\\5 Keys Project\\AISMSChatBackend\\chatlog");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.newDocument();

        // root element
        Element rootElement = doc.createElement("activity1");
        doc.appendChild(rootElement);

        // chat element
        chat = doc.createElement("chat");
        rootElement.appendChild(chat);

        // setting attribute to element
        Attr attr = doc.createAttribute("session");
        attr.setValue("user_id");
        chat.setAttributeNode(attr);
    }

    public void insertParticipantStr(String msg) {
        // user_input element
        Element user = doc.createElement("user");
        Attr attrType = doc.createAttribute("string");
        attrType.setValue("input");
        user.setAttributeNode(attrType);
        user.appendChild(doc.createTextNode(msg));
        chat.appendChild(user);
    }

    public void insertBotStr(String msg) {
        Element chatBot = doc.createElement("chatBot");
        Attr attrType1 = doc.createAttribute("string");
        attrType1.setValue("input");
        chatBot.setAttributeNode(attrType1);
        chatBot.appendChild(doc.createTextNode(msg));
        chat.appendChild(chatBot);
    }

    public void dumpChatSession(long start_timestamp, long end_timestamp) throws TransformerException {
        String fileName = start_timestamp + "-" + end_timestamp + "-user_id";
        fileName = fileName.replace(':','-');
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\shuvo\\Desktop\\NIJ Project\\5 Keys Project\\AISMSChatBackend\\chatlog\\"+fileName+".xml"));
        transformer.transform(source, result);
    }
}
