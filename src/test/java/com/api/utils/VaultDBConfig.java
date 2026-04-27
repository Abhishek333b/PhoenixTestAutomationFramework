package com.api.utils;

import java.util.Map;

import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import io.github.jopenlibs.vault.VaultException;
import io.github.jopenlibs.vault.response.LogicalResponse;

public class VaultDBConfig {

	private static VaultConfig vaultConfig;
	private static Vault vault;
	static {

		VaultConfig vaultConfig = null;
		try {
			vaultConfig = new VaultConfig().address(System.getenv("VAULT_SERVER")).token(System.getenv("VAULT_TOKEN")).build();
		} catch (VaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vault = Vault.create(vaultConfig);

	}

	private VaultDBConfig() {

	}

	public static String getSecret(String key) {

		LogicalResponse response = null;
		try {
			response = vault.logical().read("secret/phoenix/qa/database");
		} catch (VaultException e) {
			
			e.printStackTrace();
			return null;
		}

		Map<String, String> dataMap = response.getData();
		String secretValue = dataMap.get(key);
		return secretValue;
	}

}
