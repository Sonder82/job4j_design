package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс реализует простой консольный чат.
 * Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * Программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * При вводе слова «закончить» программа прекращает работу.
 * Запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 * @author Aleksey Novoselov
 * @version 1.0
 */

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата
     */
    public void run()  {
        String bot;
        List<String> phrasesBot = readPhrases();
        List<String> log = new ArrayList<>();
        String dialogConsole = CONTINUE;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!OUT.equals(dialogConsole)) {
                String phrasesUser = br.readLine();
                switch (phrasesUser) {
                    case CONTINUE:
                        dialogConsole = CONTINUE;
                        bot = phrasesBot.get(new Random().nextInt(phrasesBot.size()));
                        log.add(phrasesUser);
                        log.add(bot);
                        System.out.println(bot);
                        break;
                    case STOP:
                        dialogConsole = STOP;
                        log.add(phrasesUser);
                        System.out.println("You can continue write in, but Bot couldn't answer."
                                + "If you want continue chat with Bot, write in продолжить");
                        break;
                    case OUT:
                        dialogConsole = OUT;
                        log.add(phrasesUser);
                        break;
                    default:
                        if (dialogConsole.equals(CONTINUE)) {
                            bot = phrasesBot.get(new Random().nextInt(phrasesBot.size()));
                            log.add(phrasesUser);
                            log.add(bot);
                            System.out.println(bot);
                        } else {
                            log.add(phrasesUser);
                        }
                        break;
                }
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читает фразы из файла
     *
     * @return список фраз из текстового файла
     */
    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines()
                    .forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    /**
     * Сохраняет лог чата в файл.
     *
     * @param log список строк чата
     */
    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/dialog.txt", "./data/answersBot.txt");
        cc.run();
    }
}
