package net.canarymod.commandsys.commands;

import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class SetSpawn implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("setspawn console"));
    }

    private void player(Player player) {
        player.getWorld().setSpawnLocation(player.getLocation());
        player.message(Colors.YELLOW + Translator.translate("setspawn success"));
    }

}
