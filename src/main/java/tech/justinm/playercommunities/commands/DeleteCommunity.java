package tech.justinm.playercommunities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.justinm.playercommunities.PlayerCommunities;
import tech.justinm.playercommunities.core.Community;

public class DeleteCommunity implements CommandExecutor {
    private final PlayerCommunities plugin;

    public DeleteCommunity(PlayerCommunities plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("pc") &&
                args.length == 2 && args[0].equalsIgnoreCase("delete")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String communityName = args[1];
                Community community = plugin.getData().getCommunity(communityName);
                if (community.getOwner().equals(player.getUniqueId())) {
                    plugin.getData().deleteCommunity(communityName);
                    player.sendMessage("You deleted your community!");
                    return true;
                } else {
                    player.sendMessage("You must be the owner of a community to delete it!");
                }
            }
        }


        return false;
    }
}
