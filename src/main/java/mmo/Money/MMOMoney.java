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
import java.util.List;
import mmo.Core.MMOPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {

	//Internal Values
	private ArrayList<Money> loadedAccounts = new ArrayList<Money>();
	//Configurable values
	private long accountKeepTime = 600000l;
	//String Templates
	private String templateUserDoesNotHavePermission = "&cYou don't have permission to do this!";
	private String templateSyntaxError = "&fSyntax is &6/%s %s";
	private String templateGetOwn = "&fYou hold onto %s %s.";
	private String templateGetOther = "&f%s holds onto %s %s.";
	private String templateSetOwn = "&fYou now hold onto %s %s.";
	private String templateSetOther = "&f%s now holds onto %s %s.";
	private String templateDrop = "&fYou dropped %s %s.";
	private String templateTake = "&fYou took %s %s from %s.";
	private String templateGive = "&fYou gave %s %s %s.";

	@Override
	public void onEnable() {
		super.onEnable();
	}

	@Override
	public void loadConfiguration(Configuration cfg) {
		templateUserDoesNotHavePermission = cfg.getString("StringTemplates.UserDoesNotHavePermission", templateUserDoesNotHavePermission);
		templateSyntaxError = cfg.getString("StringTemplates.SyntaxError", templateSyntaxError);
		templateGetOwn = cfg.getString("StringTemplates.GetOwn", templateGetOwn);
		templateGetOther = cfg.getString("StringTemplates.GetOther", templateGetOther);
		templateSetOwn = cfg.getString("StringTemplates.SetOwn", templateSetOwn);
		templateSetOther = cfg.getString("StringTemplates.SetOther", templateSetOther);
		templateDrop = cfg.getString("StringTemplates.Drop", templateDrop);
		templateTake = cfg.getString("StringTemplates.Take", templateTake);
		templateGive = cfg.getString("StringTemplates.Give", templateGive);
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MoneyDB.class);
		list.add(TransactionDB.class);
		return list;
	}
	/* IF YOU SEE THIS IT'S BECAUSE OF A MAJOR REWORK
	public Money getAccount(String owner) {
	//Check if the Account is already loaded
	for (Money anAccount : loadedAccounts) {
	if (anAccount.getOwner().equalsIgnoreCase(owner)) {
	return anAccount;
	}
	}
	//Didn't find anything so lets check the Database
	MoneyDB dbEntry = getDatabase().find(MoneyDB.class).where().ieq("owner", owner).findUnique();
	if (dbEntry != null) {
	
	}
	return null;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String as, String[] args) {
	args = util.reparseArgs(args);
	if (args.length > 0) {
	String[] newArgs = (String[]) util.resizeArray(args, 1, args.length);
	if (args[0].equals("get")) {
	return onCommand_get(cs, cmd, as, newArgs);
	} else if (args[0].equals("set")) {
	return onCommand_set(cs, cmd, as, newArgs);
	} else if (args[0].equals("take")) {
	return onCommand_take(cs, cmd, as, newArgs);
	} else if (args[0].equals("give")) {
	return onCommand_give(cs, cmd, as, newArgs);
	} else if (args[0].equals("info")) {
	return onCommand_info(cs, cmd, as, newArgs);
	//} else if (args[0].equals("")) {
	//	
	}
	} else {
	this.sendMessage(true, cs, templateSyntaxError, as, "get/set/drop/take/give");
	return true;
	}
	return false;
	}
	
	public boolean onCommand_get(CommandSender cs, Command cmd, String as, String[] args) {
	MoneyDB account = null;
	if (cs instanceof Player) {
	account = money.getAccount((args.length >= 1) ? args[0] : ((Player) cs).getName());
	if (account == null) {
	this.sendMessage(true, cs, templateSyntaxError, as, "get <player>");
	return true;
	}
	} else {
	account = money.getAccount("SERVER");
	}
	if ((args.length > 1) || ((args.length == 0) && (cs instanceof ConsoleCommandSender))) {
	this.sendMessage(true, cs, templateSyntaxError, as, "get <player>");
	return true;
	} else if (args.length == 1) {
	if (cs.hasPermission("mmomoney.get.other")) {
	this.sendMessage(true, cs, templateGetOther, account.getOwner(), String.valueOf(account.getAmount()), "Coin(s)");
	return true;
	} else {
	this.sendMessage(true, cs, templateUserDoesNotHavePermission);
	return true;
	}
	} else {
	if (cs.hasPermission("mmomoney.get.own")) {
	this.sendMessage(true, cs, templateGetOwn, String.valueOf(money.getAccountMoney(account)), "Coin(s)");
	return true;
	} else {
	this.sendMessage(true, cs, templateUserDoesNotHavePermission);
	return true;
	}
	}
	return false;
	}
	
	public boolean onCommand_set(CommandSender cs, Command cmd, String as, String[] args) {
	return false;
	}
	
	public boolean onCommand_take(CommandSender cs, Command cmd, String as, String[] args) {
	return false;
	}
	
	public boolean onCommand_give(CommandSender cs, Command cmd, String as, String[] args) {
	return false;
	}
	
	public boolean onCommand_info(CommandSender cs, Command cmd, String as, String[] args) {
	return false;
	}*/
}
