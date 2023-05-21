package com.justinmtech.guilds.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class TabCompleterTest {

    @Test
    public void getRecommendationsThatStartWith() {
        TabCompleter.getRecommendationsThatStartWith("", TabCompleter.BASE_COMMANDS).forEach(System.out::println);
        TabCompleter.getRecommendationsThatStartWith("le", TabCompleter.HAS_GUILD_COMMANDS).forEach(System.out::println);
        TabCompleter.getRecommendationsThatStartWith("", TabCompleter.GUILD_LEADER_COMMANDS).forEach(System.out::println);

    }
}