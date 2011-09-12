/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmo.Money;

import java.util.Date;

/**
 *
 * @author Xaymar
 */
public class Account {

	private MoneyDB account;
	private Date lastModified;
	private boolean changed;

	public MoneyDB getAccount() {
		return account;
	}

	public void setAccount(MoneyDB account) {
		this.account = account;
		this.setChanged(true);
	}

	public String getOwner() {
		return account.getOwner();
	}

	public void setOwner(String owner) {
		account.setOwner(owner);
	}

	public long getMoneyAmount() {
		return account.getAmount();
	}

	public void setMoneyAmount(long moneyAmount) {
		account.setAmount(moneyAmount);
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
		if (changed == true) {
			this.lastModified.setTime(System.currentTimeMillis());
		}
	}
}
