/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmo.Money;

import java.util.ArrayList;
import org.bukkit.util.config.Configuration;

/**
 *
 * @author Xaymar
 */
public class Money {

    protected static MMOMoney plugin;
    protected static Configuration cfg;
    protected static String templateNoPermission = "&cYou don't have permission to do this!";
    protected static String templateSyntaxError = "&fSyntax is &6/%s %s";
    protected static String templateAccountInvalid = "&fAccount '%s' does not exist.";
    protected static String templateNotEnoughMoney = "&fYou don't have enough money to do that.";
    protected static String templateGetOwn = "&fYou hold onto %s %s.";
    protected static String templateGetOther = "&f%s holds onto %s %s.";
    protected static String templateSetOwn = "&fYou now hold onto %s %s.";
    protected static String templateSetOther = "&f%s now holds onto %s %s.";
    protected static String templateDrop = "&fYou dropped %s %s.";
    protected static String templateTake = "&fYou took %s %s from %s.";
    protected static String templateGive = "&fYou gave %s %s %s.";
    protected static String templateCurrency = "Coin(s)";
    protected static String templateDatabaseRefresh = "&fThe Database has been refreshed.";
    protected static long defaultAccountMoney = 0;
/*    protected static ArrayList<MoneyDB> managerMoneyDB = new ArrayList<MoneyDB>();
    protected static ArrayList<TransactionDB> managerTransactionDB = new ArrayList<TransactionDB>();
    protected static ArrayList<Account> usedAccounts = new ArrayList<Account>();*/

    public static Account getAccount(String name) {
/*        MoneyDB dbAccount = plugin.getDatabase().find(MoneyDB.class).;
        if (dbAccount != null) {
            for (Account account : usedAccounts) {
                if (account.getAccount() == dbAccount) {
                    return account;
                }
            }
            Account userAccount = new Account();
            userAccount.setAccount(dbAccount);
            userAccount.setChanged(true);
            usedAccounts.add(userAccount);
            return userAccount;
        } else {
            return null;
        }*/
    }

    public static Account createAccount(String name) {
        return createAccount(name, defaultAccountMoney);
    }

    public static Account createAccount(String name, long money) {
        Account newAccount = getAccount(name);
/*        if (newAccount == null) {
            MoneyDB acc = new MoneyDB();
            managerMoneyDB.add(acc);
            newAccount = new Account();
            newAccount.setAccount(acc);
            newAccount.setOwner(name.toLowerCase());
        }
        newAccount.setMoneyAmount(money);
        usedAccounts.add(newAccount);*/
        return newAccount;
    }

    public static void deleteAccount(Account account) {
/*        plugin.getDatabase().delete(account.getAccount());
        usedAccounts.remove(account);*/
    }
}
