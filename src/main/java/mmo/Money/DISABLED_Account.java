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
public class DISABLED_Account {

	private MMOMoneyDB account;
	private Date lastModified;
	private boolean changed;
	private DISABLED_Money api;

	public DISABLED_Account() {
		api = new DISABLED_Money();
		lastModified = new Date();
	}

	protected MMOMoneyDB getAccount() {
		return account;
	}

	protected void setAccount(MMOMoneyDB account) {
		this.account = account;
		this.setChanged(true);
	}

	public String getOwner() {
		return account.getOwner();
	}

	protected void setOwner(String owner) {
		account.setOwner(owner);
	}

	protected long getMoneyAmount() {
		return account.getAmount();
	}

	protected void setMoneyAmount(long moneyAmount) {
		account.setAmount(moneyAmount);
	}

	protected boolean isChanged() {
		return changed;
	}

	protected void setChanged(boolean changed) {
		this.changed = changed;
		if (changed == true) {
			this.lastModified.setTime(System.currentTimeMillis());
		}
	}

	//Helpful functions
	public long getMoney() {
		return account.getAmount();
	}

	public MMOTransactionDB setMoney(long money) {
		MMOTransactionDB tdb;
		long total = money - account.getAmount();
		if (total < 0) {
			tdb = takeMoney(-total);
		} else {
			tdb = giveMoney(total);
		}
		tdb.setReason("setMoney");
		return tdb;
	}

	public MMOTransactionDB giveMoney(long money) {
		MMOTransactionDB tdb = api.getAccount("SERVER").transferMoney(this, money);
		tdb.setReason("giveMoney");
		return tdb;
	}

	public MMOTransactionDB takeMoney(long money) {
		MMOTransactionDB tdb = transferMoney(api.getAccount("SERVER"), money);
		tdb.setReason("takeMoney");
		return tdb;
	}

	public MMOTransactionDB transferMoney(DISABLED_Account to, long amount) {
		MMOTransactionDB tdb = createTransaction(this, to, amount, "transferMoney");
		if (account.getAmount() >= amount) {
			account.setAmount(account.getAmount() - amount);
			to.getAccount().setAmount(to.getAccount().getAmount() + amount);
			setChanged(true);
			to.setChanged(true);
		} else {
			tdb.setFailed(true);
		}
		return tdb;
	}

	public MMOTransactionDB createTransaction(DISABLED_Account from, DISABLED_Account to, long amount, String reason) {
		MMOTransactionDB tdb = new MMOTransactionDB();
		tdb.setFromAccount(from.getAccount());
		tdb.setToAccount(to.getAccount());
		tdb.setAmount(amount);
		tdb.setReason(reason);
		tdb.setOnDate(new Date());
		tdb.setFailed(false);
		return tdb;
	}
}
