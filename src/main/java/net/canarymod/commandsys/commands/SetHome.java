package net.canarymod.commandsys.commands;

import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class SetHome extends CanaryCommand {

    public SetHome() {
        super("canary.command.sethome", "Set your home", "Usage: /sethome", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console(caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("Your home has been set to everywhere.");
    }
    
    private void player(Player player, String[] args) {
        player.setHome(player.getLocation());
        player.sendMessage(Colors.YELLOW + "Your home has been set.");
    }

}
