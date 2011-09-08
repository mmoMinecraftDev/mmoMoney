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
public class MoneyDBCleaner implements Runnable {
	private Money money;

	public MoneyDBCleaner(Money money) {
		this.money = money;
	}
	
	@Override
	public void run() {
		//Save all loaded accounts & remove from memory if too old.
		Date old = new Date(System.currentTimeMillis()-2592000000l);
		for (MoneyDB account : money.loadedAccounts.keySet()) {
			Date changed = money.loadedAccounts.get(account);
			Date nextSave = (Date) changed.clone();
			Date dontSave = (Date) changed.clone();
			nextSave.setTime(changed.getTime()+15000l);
			nextSave.setTime(changed.getTime()+15150l);
			
			//Only save a minute after last use
			if (changed.after(nextSave) & changed.before(dontSave)) {
				money.saveAccount(account);
			}
			if (money.loadedAccounts.get(account).before(old)) {
				money.loadedAccounts.remove(old);
			}
		}
	}
	
}
