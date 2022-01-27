package tech.justinm.playercommunities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.justinm.playercommunities.commands.*;
import tech.justinm.playercommunities.util.InputChecker;

public class CommandHandler implements CommandExecutor {
    private final PlayerCommunities plugin;

    public CommandHandler(PlayerCommunities plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("playercommunities")) {
            if (sender instanceof Player) {
                if (args.length > 0) {
                    if (InputChecker.noSpecialCharacters(args)) {
                        parseSubCommand(sender, args);
                    } else {
                        if (args[0].equalsIgnoreCase("setdesc")) {
                            parseSubCommand(sender, args);
                        } else {
                        sender.sendMessage("Special characters are not allowed in this command!");
                        }
                    }
                }
            }
        }
        return true;
    }

    private void parseSubCommand(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            if (args.length == 2) {
                new CreateCommunity(plugin, sender, args);
            } else {
                //syntax message
            }
        } else if (args[0].equalsIgnoreCase("delete")) {
            if (args.length == 2) {
                new DeleteCommunity(plugin, sender, args);
            } else {
                //syntax message
            }
        } else if (args[0].equalsIgnoreCase("warp")) {
            if (args.length == 2) {
                new GoToWarp(plugin, sender, args);
            } else {
                //syntax message
            }
        } else if (args[0].equalsIgnoreCase("invite")) {
            if (args.length == 2) {
                new InvitePlayer(plugin, sender, args);
            } else {
                //syntax msg
            }
        } else if (args[0].equalsIgnoreCase("list")) {
            new ListCommunities(plugin, sender, args);
        } else if (args[0].equalsIgnoreCase("setdesc")) {
            if (args.length >= 2) {
                new SetDescription(plugin, sender, args);
            } else {
                //syntax msg
            }
        } else if (args[0].equalsIgnoreCase("setwarp")) {
            if (args.length == 2) {
                new SetWarp(plugin, sender, args);
            } else {
                //syntax msg
            }
        } else if (args[0].equalsIgnoreCase("accept")) {
                if (args.length == 2) {
                    new AcceptInvite(plugin, sender, args);
                } else {
                    //syntax msg
                }
        } else if (args.length == 1) {
            new GetCommunityInfo(plugin, sender, args);
        }
    }
}
