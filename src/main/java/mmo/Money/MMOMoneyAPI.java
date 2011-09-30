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
	public static MMOAccount getAccount(String name) {
		MMOMoneyDB account = pluginInstance.getDatabase().find(MMOMoneyDB.class).where().ieq("owner", name.toLowerCase()).findUnique();
		if (account != null) {
			loadedAccounts.add(account);
		}
		return new MMOAccount(account, pluginInstance);
	}

	public static MMOAccount createAccount(String name) {
		MMOAccount account = getAccount(name);
		if (account == null) {
			MMOMoneyDB dbaccount = new MMOMoneyDB();
			dbaccount.setOwner(name.toLowerCase());
			dbaccount.setAmount(MMOMoney.cfgNewAccountMoney);
			pluginInstance.getDatabase().save(dbaccount);
			loadedAccounts.add(dbaccount);
			account = new MMOAccount(dbaccount, pluginInstance);
		}
		return account;
	}

	public static void deleteAccount(MMOAccount account) {
		pluginInstance.getDatabase().delete(account.linkedAccount);
		loadedAccounts.remove(account.linkedAccount);
	}

	//MMOTransactionDB
	public static MMOTransactionDB getTransaction() {
		return null;
	}
}
