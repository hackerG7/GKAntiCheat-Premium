package me.club.gkanticheat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public class PlayerData {
	public UUID uid = null;
	public String playerName = "";
	public String key = "";

	public static String generateCode() {
		return "bypass: " + Math.random() * 1000000 + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
	}

	public PlayerData(UUID uid, String playerName) {
		this.uid = uid;
		this.playerName = playerName;
		key = generateCode();
	}

}