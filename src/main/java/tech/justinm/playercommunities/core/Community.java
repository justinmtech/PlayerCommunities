package tech.justinm.playercommunities.core;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Community implements Comparable<Community> {
    private String name;
    private UUID owner;
    private String description;
    private List<UUID> members;
    private List<Warp> warps;

    public Community(UUID owner, String name) {
        this.owner = owner;
        this.name = name;
        this.members = new LinkedList<>();
        members.add(owner);
        this.warps = new LinkedList<>();
    }

    public Community(String name, String ownerId, String description, List<String> memberUuids, Object warps) {
        this.members = new LinkedList<>();
        this.warps = new LinkedList<>();
        this.name = name;
        this.owner = UUID.fromString(ownerId);
        this.description = description;
        for (String member : memberUuids) {
            members.add(UUID.fromString(member));
        }
        ((JSONArray) warps).forEach(warp -> _addWarps((JSONObject) warp));
    }

    private void _addWarps(JSONObject warp) {
            String name = (String) warp.get("name");
            String description = (String) warp.get("description");
            String world = (String) warp.get("world");

            double x = (Double) warp.get("x");
            double y = (Double) warp.get("y");
            double z = (Double) warp.get("z");
            double yaw = (Double) warp.get("yaw");
            double pitch = (Double) warp.get("pitch");

            this.warps.add(new Warp(name, description, world, x, y, z, (float) yaw, (float) pitch));
    }


    public Community(String name, UUID owner, String description, List<UUID> members, List<Warp> warps) {
        this.members = new LinkedList<>();
        this.warps = new LinkedList<>();
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.members = members;
        this.warps = warps;
    }

    public Community(String name, String description, List<UUID> members, List<Warp> warps) {
        this.name = name;
        this.description = description;
        this.members = new LinkedList<>();
        this.members = members;
        this.warps = new LinkedList<>();
        this.warps = warps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public boolean isOwner(UUID playerUuid) {
        return playerUuid.equals(owner);
    }

    public boolean containsMember(Player player) {
        return members.contains(player);
    }

    public boolean containsWarp(String name) {
        return warps.stream().anyMatch(w -> w.getName().equalsIgnoreCase(name));
    }

    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public List<Warp> getWarps() {
        return warps;
    }

    public void setWarps(List<Warp> warps) {
        this.warps = warps;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("owner", owner);
        json.put("description", description);
        json.put("members", members);
        json.put("warps", warps);
        return json.toString();
    }

    @Override
    public int compareTo(Community o) {
        if (members.size() > o.getMembers().size()) return 1;
        return -1;
    }
}