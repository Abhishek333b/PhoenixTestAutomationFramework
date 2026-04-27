package com.api.utils;

import java.util.Map;

import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import io.github.jopenlibs.vault.VaultException;
import io.github.jopenlibs.vault.response.LogicalResponse;

public class VaultDemo {

	public static void main(String[] args) throws VaultException {

		String data = System.getenv("VAULT_SERVER");
		System.out.println(data);
		
		String data2 = System.getenv("VAULT_TOKEN");
		System.out.println(data2);

		/*
		 * VaultConfig vaultConfig = new VaultConfig()
		 * .address("http://13.62.49.234:8200") .token("root") .build();
		 * 
		 * Vault vault = Vault.create(vaultConfig); LogicalResponse
		 * response=vault.logical().read("secret/phoenix/qa/database");
		 * 
		 * Map<String ,String>dataMap= response.getData();
		 * System.out.println(dataMap.get("DB_URL"));
		 * System.out.println(dataMap.get("DB_USERNAME"));
		 * System.out.println(dataMap.get("DB_PASSWORD"));
		 * 
		 */

	}

}
