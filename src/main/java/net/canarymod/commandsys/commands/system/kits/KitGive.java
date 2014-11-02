package net.canarymod.commandsys.commands.system.kits;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.TextFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.kit.Kit;
import net.canarymod.kit.KitProvider;

import static net.canarymod.commandsys.CanaryCommandPermissions.KIT$OTHER;

/**
 * /kit give
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class KitGive implements NativeCommand {
    private static final KitProvider kits = Canary.kits();

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            asPlayer((Player)caller, parameters);
        }
        else {
            asServer(caller, parameters);
        }
    }

    protected void asPlayer(Player player, String[] args) {
        // Give kit to player
        if (args.length == 1) {
            Kit kit = kits.getKit(args[0]);

            if (kit != null) {
                if (kit.giveKit(player, false)) {
                    player.message(TextFormat.YELLOW + Translator.translate("kit given"));
                }
                else {
                    player.notice(Translator.translate("kit unavailable"));
                }
            }
            else {
                player.notice(Translator.translateAndFormat("kit invalid", args[1]));
            }
        }
        // Give kit to a subject
        else if (args.length > 1) {
            if (!player.hasPermission(KIT$OTHER)) {
                return;
            }
            Player recipient = Canary.getServer().matchPlayer(args[1]);

            if (recipient != null) {
                Kit kit = kits.getKit(args[0]);

                if (kit != null) {
                    if (kit.giveKit(recipient, false)) {
                        recipient.message(TextFormat.YELLOW + Translator.translateAndFormat("kit given other", player.getName()));
                    }
                    else {
                        player.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                    }
                }
                else {
                    player.notice(Translator.translateAndFormat("kit invalid", args[0]));
                }
            }
            else {
                player.notice(Translator.translateAndFormat("unknown player", args[1]));
            }
        }
    }

    protected void asServer(MessageReceiver caller, String[] args) {
        // Give kit to a subject
        if (args.length >= 2) {
            boolean override = args.length > 2 && args[2].toLowerCase().equals("override");
            Player recipient = Canary.getServer().matchPlayer(args[1]);

            if (recipient != null) {
                Kit kit = kits.getKit(args[0]);

                if (kit != null) {
                    if (kit.giveKit(recipient, override)) {
                        recipient.message(TextFormat.YELLOW + Translator.translateAndFormat("kit given other", caller.getName()));
                    }
                    else {
                        caller.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                    }
                }
                else {
                    caller.notice(Translator.translateAndFormat("kit invalid", args[0]));
                }
            }
            else {
                caller.notice(Translator.translateAndFormat("unknown player", args[1]));
            }
        }
        else {
            caller.notice(Translator.translateAndFormat("usage", "/kit give <name> <player> [override]"));
        }
    }
}
