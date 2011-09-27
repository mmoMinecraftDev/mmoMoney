/*
 * This file is part of mmoMinecraft (https://github.com/mmoMinecraftDev).
 *
 * mmoMinecraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mmo.Money;

import java.util.ArrayList;

public class MMOMoneyAPI {

    protected static MMOMoney pluginInstance;
    protected static ArrayList<MMOMoneyDB> loadedAccounts = new ArrayList<MMOMoneyDB>();

    //MMOMoneyDB
    public static MMOMoneyDB getAccount(String name) {
        MMOMoneyDB account = pluginInstance.getDatabase().find(MMOMoneyDB.class).where().ieq("owner", name.toLowerCase()).findUnique();
        if (account != null) {
            loadedAccounts.add(account);
        }
        return account;
    }

    public static MMOMoneyDB createAccount(String name) {
        MMOMoneyDB account = getAccount(name);
        if (account == null) {
            account = new MMOMoneyDB();
            account.setOwner(name.toLowerCase());
            account.setAmount(MMOMoney.cfgNewAccountMoney);
            pluginInstance.getDatabase().save(account);
            loadedAccounts.add(account);
        }
        return account;
    }

    public static void deleteAccount(MMOMoneyDB account) {
        pluginInstance.getDatabase().delete(account);
        loadedAccounts.remove(account);
    }

    //MMOTransactionDB
    public static MMOTransactionDB getTransaction() {
        return null;
    }
}
