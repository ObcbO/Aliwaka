package com.obcbo.aliwaka.command;

import com.obcbo.aliwaka.Config;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class shell implements Runnable {
    public static String text;
    public static CommandSender sender;

    public static boolean core(CommandSender sender, String[] args) {
        if (sender.hasPermission("aliwaka.admin")) {
            String text = String.join(" ", args);
            if (text.length() < 7) {
                sender.sendMessage(Config.prefix + "你似乎还没有输入命令");
                return true;
            }
            shell.text = text.substring(6);
            shell.sender = sender;
            sender.sendMessage(Config.prefix + "开始执行 " + shell.text);
            new Thread(new shell(), "Aliwaka-Shell").start();
        } else {
            sender.sendMessage(Config.prefix + "§c没有权限");
        }
        return true;
    }

    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec(text).getInputStream()))) {
            String line;
            StringBuilder b = new StringBuilder();
            while ((line = br.readLine()) != null) {
                b.append(line).append("\n");
            }
            sender.sendMessage(b.toString());
        } catch (Exception e) {
            sender.sendMessage(Config.prefix + "执行命令遇到问题");
            e.printStackTrace();
        }
    }
}
