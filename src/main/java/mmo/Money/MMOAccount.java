/*
 * This file is part of mmoMoney <http://github.com/mmoMinecraftDev/mmoMoney>.
 *
 * mmoMoney is free software: you can redistribute it and/or modify
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

public class MMOAccount {
	protected MMOMoneyDB linkedAccount;
	protected MMOMoney pluginInstance;

	public MMOAccount(MMOMoneyDB linkedAccount, MMOMoney pluginInstance) {
		this.linkedAccount = linkedAccount;
		this.pluginInstance = pluginInstance;
	}

	public boolean exists() {
		MMOMoneyDB tempLinkSearch = pluginInstance.getDatabase().find(MMOMoneyDB.class).where().ieq("owner", linkedAccount.getOwner()).findUnique();
		if ((linkedAccount != null) && (tempLinkSearch != null)) {
			if (tempLinkSearch != linkedAccount) {
				linkedAccount = tempLinkSearch;
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public String getOwner() {
		if (exists()) {
			return linkedAccount.getOwner();
		} else {
			throw new NullPointerException();
		}
	}

	public boolean hasEnough(long checkAmount) {
		if (exists()) {
			return (linkedAccount.getAmount() >= checkAmount) ? true : false;
		} else {
			throw new NullPointerException();
		}
	}

	public long getMoney() {
		if (exists()) {
			return linkedAccount.getAmount();
		} else {
			throw new NullPointerException();
		}
	}

	public boolean setMoney(long amount) {
		if (exists()) {
			linkedAccount.setAmount(amount);
			return true;
		} else {
			throw new NullPointerException();
		}
	}

	public boolean giveMoney(MMOAccount who, long amount) {
		if (exists()) {
			if (who.exists()) {
				if (hasEnough(amount)) {
					who.setMoney(who.getMoney() + amount);
					linkedAccount.setAmount(linkedAccount.getAmount() - amount);
					return true;
				} else {
					return false;
				}
			} else {
				throw new NullPointerException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	public boolean takeMoney(MMOAccount who, long amount) {
		if (exists()) {
			if (who.exists()) {
				if (who.hasEnough(amount)) {
					who.setMoney(who.getMoney() - amount);
					linkedAccount.setAmount(linkedAccount.getAmount() + amount);
					return true;
				} else {
					return false;
				}
			} else {
				throw new NullPointerException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	public void delete() {
		if (exists()) {
			MMOMoneyAPI.deleteAccount(this);
		} else {
			throw new NullPointerException();
		}
	}
}
