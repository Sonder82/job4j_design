package ru.job4j.io;

import java.io.*;

/**
 * Класс реализует модель анализа доступности сервера
 * @author Aleksey Novoselov
 * @version 1.0
 */
public class Analizy {

    /**
     * Метод должен находить диапазоны, когда сервер не работал.
     * Сервер не работал, если status = 400 или 500. Сервер работал, если status = 200 или 300.
     * Начальное время - это время когда статус 400 или 500.
     * Конечное время - это время когда статус поменяется на 200 или 300
     * @param source файл регистрации событий сервера
     * @param target файл с диапазонами времени, когда сервер не работал.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean serverIsWork = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                    if ((line.startsWith("400") || line.startsWith("500")) && (serverIsWork)) {
                        out.print(line.split(" ")[1] + ";");
                        serverIsWork = false;
                    } else if ((line.startsWith("200") || line.startsWith("300")) && (!serverIsWork)) {
                        out.println(line.split(" ")[1]);
                        serverIsWork = true;
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
        analizy.unavailable("./data/server.log", "./data/unavailableMod.csv");
    }
}
