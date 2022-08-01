package com.obcbo.aliwaka;

import com.obcbo.aliwaka.command.*;
import com.obcbo.aliwaka.file.Config;
import com.obcbo.aliwaka.file.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.obcbo.aliwaka.file.Message.help;

public class CommandManage implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1 || "help".equals(args[0])) {
            for (String now : help) {
                sender.sendMessage(now);
            }
            return true;
        }
        switch (args[0]) {
            case ("gc"):
                return gc.core(sender);
            case ("info"):
                return info.core(sender);
            case ("shell"):
                return shell.core(sender, args);
            case ("function"):
                return function.core(sender, args);
            case ("reload"):
                Aliwaka.reload();
                return true;
            default:
                sender.sendMessage(Message.prefix + "错误的命令语法");
                return true;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return TabList.returnList(args, args.length, sender);
    }
}
