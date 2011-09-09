/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmo.Money;

import com.avaje.ebean.EbeanServer;
import java.util.Date;
import java.util.HashMap;
import org.bukkit.Server;

/**
 *
 * @author Xaymar
 */
public class Money {

	public MMOMoney plugin;
	public Server server;
	public EbeanServer database;
	public HashMap<MoneyDB, Date> loadedAccounts = new HashMap<MoneyDB, Date>();

	public Money(MMOMoney plugin) {
		this.plugin = plugin;
		this.server = plugin.getServer();
		this.database = plugin.getDatabase();
	}

	public MoneyDB loadAccount(MoneyDB account) {
		if (!loadedAccounts.containsKey(account)) {
			MoneyDB accountCheck = null;
			for (MoneyDB account2 : loadedAccounts.keySet()) {
				if (account2.getOwner().equalsIgnoreCase(account.getOwner())) {
					accountCheck = account2;
					break;
				}
			}
			if (accountCheck != account) {
				account = database.find(MoneyDB.class).where().ieq("owner", account.getOwner()).findUnique();
			}
		}
		loadedAccounts.put(account, new Date());
		return account;
	}
	
	public void saveAccount(MoneyDB account) {
		database.save(account);
		if (loadedAccounts.get(account).before(new Date(System.currentTimeMillis()-2592000000l))) {
			loadedAccounts.remove(account);
		}
	}
	
	public MoneyDB getAccount(String owner) {
		for (MoneyDB loadedAccount : loadedAccounts.keySet()) {
			if (loadedAccount.getOwner().equalsIgnoreCase(owner)) {
				return loadAccount(loadedAccount);
			}
		}
		MoneyDB foundAccount = database.find(MoneyDB.class).where().ieq("owner", owner).findUnique();
		if (foundAccount == null) {
			MoneyDB createdAccount = new MoneyDB();
			createdAccount.setOwner(owner);
			createdAccount.setAmount(0);
			return loadAccount(createdAccount);
		} else {
			return loadAccount(foundAccount);
		}
	}
	
	public MoneyDB getAccount(MoneyDB account) {
		return loadAccount(account);
	}
	
	public TransactionDB createTransaction(String from, String to, long amount, String reason) {
		TransactionDB transDB = new TransactionDB();
		transDB.setFromAccount(from);
		transDB.setToAccount(to);
		transDB.setAmount(amount);
		transDB.setReason(reason);
		transDB.setOnDate(new Date());
		database.save(transDB);
		return transDB;
	}

	//Account stuff: Get/Set/Take/Give
	public long getAccountMoney(MoneyDB account) {
		account = getAccount(account);
		return account.getAmount();
	}
	
	public TransactionDB setAccountMoney(MoneyDB account, long amount) {
		account = getAccount(account);
		TransactionDB trans = createTransaction("SERVER", account.getOwner(), amount-account.getAmount(), "");
		if (amount >= 0) {
			account.setAmount(Math.max(amount,0));
			trans.setFailed(false);
		} else {
			trans.setFailed(true);
		}
		return trans;
	}
	
	public TransactionDB takeAccountMoney(MoneyDB account, long amount) {
		account = getAccount(account);
		TransactionDB trans = createTransaction("SERVER", account.getOwner(), -amount, "");
		if (account.getAmount() >= amount & amount >= 0) {
			account.setAmount(account.getAmount()-amount);
			trans.setFailed(false);
		} else {
			trans.setFailed(true);
		}
		return trans;
	}
	
	public TransactionDB giveAccountMoney(MoneyDB account, long amount) {
		account = getAccount(account);
		TransactionDB trans = createTransaction("SERVER", account.getOwner(), amount, "");
		if (amount >= 0) {
			account.setAmount(account.getAmount()+amount);
			trans.setFailed(false);
		} else {
			trans.setFailed(true);
		}
		return trans;
	}
}
