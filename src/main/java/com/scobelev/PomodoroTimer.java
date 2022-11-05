package com.scobelev;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите время работы и отдыха");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");
        int workTime =1;
        int breakTime = 1;
        int iterCount = 1;
        int mult = 1;
        boolean isHelp = false;
        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]){
                case "-w"-> workTime = Integer.parseInt(cmd[++i]);
                case "-b"-> breakTime = Integer.parseInt(cmd[++i]);
                case "-count"-> iterCount = Integer.parseInt(cmd[++i]);
                case "-m"-> mult = Integer.parseInt(cmd[++i]);
                case "--help" -> {
                    System.out.println("""
                            \nPomodoro - приложение для лучшей работы
                            -w - Сколько работать
                            -b - Сколько отдыхать
                            -count - Количество итераций
                            -m - множитель
                            -help - Помощь
                            """);
                    isHelp = true;
                }
            }


        }
        if(isHelp) return;

        long startTime = System.currentTimeMillis();
        for (int i=1; i <= iterCount; i++ ) {
            timer(workTime, breakTime);
            System.out.println("Итерация №"+ i + " Время работы " + workTime);
            workTime *= mult;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Таймер работал "+ (endTime-startTime)/(1000*60) + " min");

    }
    public static void timer(int workTime, int breakTime) throws InterruptedException{
        printProgress("Work progress ::", workTime, 30);
        printProgress("Break progress ::", breakTime, 30);
    }
    public static void printProgress(String process, int time, int size) throws InterruptedException{
        int length;
        int rep;
        length = 60*time / size;
        rep = 60*time / length;
        int stretchb = size / (time*3);
        for (int i = 0; i <= rep; i++) {
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x/w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + " %" + (" ").repeat(5 - String.valueOf(percent).length()) +
                    "[" + ("#").repeat(i) + ("-").repeat(rep -i) + "]   ( " + x + "min / " + time + "min )" + "\r");
            if(true){
                TimeUnit.SECONDS.sleep(length);
            }
        }
    }
}
