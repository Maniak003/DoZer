package ru.starline.dozer;

import android.app.Application;
import android.os.Process;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DozerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Устанавливаем глобальный перехватчик необработанных исключений
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                try {
                    // Создаем файл во внутренней памяти приложения.
                    // Это НЕ требует разрешений на запись в хранилище.
                    File crashFile = new File(getFilesDir(), "CRASH_LOG.txt");
                    FileWriter fileWriter = new FileWriter(crashFile, false); // false = перезаписывать старый лог
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    // Записываем время и базовую информацию
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    printWriter.println("=== CRASH REPORT ===");
                    printWriter.println("Time: " + sdf.format(new Date()));
                    printWriter.println("Thread: " + thread.getName());
                    printWriter.println("Exception: " + throwable.getClass().getName());
                    printWriter.println("Message: " + throwable.getMessage());
                    printWriter.println("--------------------");

                    // Записываем полный стек-трейс (самое важное!)
                    throwable.printStackTrace(printWriter);

                    printWriter.flush();
                    printWriter.close();

                } catch (Exception e) {
                    // Если запись лога сама по какой-то причине упала, мы хотя бы попытались
                    e.printStackTrace();
                }

                // Принудительно завершаем процесс, чтобы система не показывала стандартное окно "Приложение остановлено"
                Process.killProcess(Process.myPid());
                System.exit(10);
            }
        });
    }
}