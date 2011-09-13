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
import mmo.Core.util.util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {

	private Money api;

	@Override
	public void onEnable() {
		super.onEnable();
		getDatabase().find(MoneyDB.class);
		getDatabase().find(TransactionDB.class);

		Money api = new Money();
		api.createAccount("SERVER", 0);
	}

	@Override
	public void loadConfiguration(Configuration cfg) {
		Money.templateNoPermission = util.colorize(cfg.getString("StringTemplates.NoPermission", Money.templateNoPermission));
		Money.templateSyntaxError = util.colorize(cfg.getString("StringTemplates.SyntaxError", Money.templateSyntaxError));
		Money.templateGetOwn = util.colorize(cfg.getString("StringTemplates.GetOwn", Money.templateGetOwn));
		Money.templateGetOther = util.colorize(cfg.getString("StringTemplates.GetOther", Money.templateGetOther));
		Money.templateSetOwn = util.colorize(cfg.getString("StringTemplates.SetOwn", Money.templateSetOwn));
		Money.templateSetOther = util.colorize(cfg.getString("StringTemplates.SetOther", Money.templateSetOther));
		Money.templateDrop = util.colorize(cfg.getString("StringTemplates.Drop", Money.templateDrop));
		Money.templateTake = util.colorize(cfg.getString("StringTemplates.Take", Money.templateTake));
		Money.templateGive = util.colorize(cfg.getString("StringTemplates.Give", Money.templateGive));
		Money.templateAccountInvalid = util.colorize(cfg.getString("StringTemplates.AccountInvalid", Money.templateAccountInvalid));
	}

	@Override
	public void onDisable() {
		super.onDisable();
		Money api = null;
		getDatabase().save(MoneyDB.class);
		getDatabase().save(TransactionDB.class);
	}

	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MoneyDB.class);
		list.add(TransactionDB.class);
		return list;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		args = util.reparseArgs(args);
		String[] newArgs = ((String[]) util.resizeArray(args, 1, args.length));

		if (args.length == 0) {
			return onCommand_Get(cs, cmd, label, newArgs);
		} else {
			if (args[0].equalsIgnoreCase("stats")) {
				return onCommand_Stats(cs, cmd, label, newArgs);
			} else if (args[0].equalsIgnoreCase("get")) {
				return onCommand_Get(cs, cmd, label, newArgs);
			} else if (args[0].equalsIgnoreCase("set")) {
				return onCommand_Set(cs, cmd, label, newArgs);
			} else if (args[0].equalsIgnoreCase("take")) {
				return onCommand_Take(cs, cmd, label, newArgs);
			} else if (args[0].equalsIgnoreCase("give")) {
				return onCommand_Give(cs, cmd, label, newArgs);
			} else if (args[0].equalsIgnoreCase("admin")) {
				return onCommand_Admin(cs, cmd, label, newArgs);
			} else {
				sendMessage(cs, Money.templateSyntaxError, label, "<stats|get|set|take|give|admin>");
			}
		}
		return false;
	}

	public boolean onCommand_Stats(CommandSender cs, Command cmd, String label, String[] args) {
		sendMessage(cs, "%s v%s - %s -", this.getDescription().getName(), this.getDescription().getVersion(), this.getDescription().getWebsite());
		return true;
	}

	private boolean onCommand_Get(CommandSender cs, Command cmd, String label, String[] args) {
		String from = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
		String to = (args.length == 0) ? ((cs instanceof Player) ? ((Player) cs).getName() : "SERVER") : args[0];

		if (from.equalsIgnoreCase(to)) {
			if (cs.hasPermission("mmomoney.get.own")) {
				Account aFrom = api.getAccount(from);
				if (aFrom != null) {
					sendMessage(cs, Money.templateGetOwn, String.valueOf(aFrom.getMoney()), "Coin(s).");
				} else {
					sendMessage(cs, Money.templateAccountInvalid, from);
				}
			} else {
				sendMessage(cs, Money.templateNoPermission);
			}
		} else {
			if (cs.hasPermission("mmomoney.get.other")) {
				Account aTo = api.getAccount(to);
				if (aTo != null) {
					sendMessage(cs, Money.templateGetOther, to, String.valueOf(aTo.getMoney()), "Coin(s).");
				} else {
					sendMessage(cs, Money.templateAccountInvalid, to);
				}
			} else {
				sendMessage(cs, Money.templateNoPermission);
			}
		}

		return false;
	}

	private boolean onCommand_Set(CommandSender cs, Command cmd, String label, String[] args) {
		String from = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
		String to = (args.length == 0) ? ((cs instanceof Player) ? ((Player) cs).getName() : "SERVER") : args[0];
		long amount = (args.length == 1) ? Long.parseLong(args[0]) : ((args.length == 2) ? Long.parseLong(args[1]) : 0l);

		if (from.equalsIgnoreCase(to)) {
			if (cs.hasPermission("mmomoney.set.own")) {
				Account aTo = api.getAccount(from);
				if (aTo != null) {
					aTo.setMoney(amount);
					sendMessage(cs, Money.templateSetOwn, String.valueOf(amount), "Coin(s).");
				} else {
					sendMessage(cs, Money.templateAccountInvalid, from);
				}
			} else {
				sendMessage(cs, Money.templateNoPermission);
			}
		} else {
			if (cs.hasPermission("mmomoney.set.other")) {
				Account aTo = api.getAccount(to);
				if (aTo != null) {
					aTo.setMoney(amount);
					sendMessage(cs, Money.templateSetOther, to, String.valueOf(amount), "Coin(s).");
				} else {
					sendMessage(cs, Money.templateAccountInvalid, to);
				}
			} else {
				sendMessage(cs, Money.templateNoPermission);
			}
		}

		return false;
	}

	private boolean onCommand_Take(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Give(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin(CommandSender cs, Command cmd, String label, String[] args) {
		String[] newArgs = ((String[]) util.resizeArray(args, 1, args.length));
		if (cs.hasPermission("mmomoney.admin")) {
			if (args.length == 0) {
				sendMessage(cs, Money.templateSyntaxError, label, "admin <account|database>");
			} else {
				if (args[0].equalsIgnoreCase("account")) {
					return onCommand_Admin_Account(cs, cmd, label, newArgs);
				} else if (args[0].equalsIgnoreCase("database")) {
					return onCommand_Admin_Database(cs, cmd, label, newArgs);
				} else {
					sendMessage(cs, Money.templateSyntaxError, label, "admin <account|database>");
				}
			}
		} else {
			sendMessage(cs, Money.templateNoPermission);
			return true;
		}
		return false;
	}

	private boolean onCommand_Admin_Account(CommandSender cs, Command cmd, String label, String[] args) {
		String[] newArgs = ((String[]) util.resizeArray(args, 1, args.length));
		return false;
	}

	private boolean onCommand_Admin_Account_List(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin_Account_Create(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin_Account_Remove(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin_Account_Reset(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin_Database(CommandSender cs, Command cmd, String label, String[] args) {
		String[] newArgs = ((String[]) util.resizeArray(args, 1, args.length));
		return false;
	}

	private boolean onCommand_Admin_Database_Purge(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}

	private boolean onCommand_Admin_Database_Empty(CommandSender cs, Command cmd, String label, String[] args) {
		return false;
	}
}
