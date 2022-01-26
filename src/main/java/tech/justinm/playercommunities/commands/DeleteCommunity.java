package tech.justinm.playercommunities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.justinm.playercommunities.PlayerCommunities;
import tech.justinm.playercommunities.core.Community;

public class DeleteCommunity implements CommandExecutor {
    private PlayerCommunities plugin;

    public DeleteCommunity(PlayerCommunities plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("pcdelete")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String communityName = args[0];
                Community community = plugin.getData().getAllCommunities().stream().filter(c -> c.getName().equalsIgnoreCase(communityName)).findFirst().orElseThrow(null);
                if (player.equals(community.getOwner())) {
                    plugin.getData().deleteCommunity(community);
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
