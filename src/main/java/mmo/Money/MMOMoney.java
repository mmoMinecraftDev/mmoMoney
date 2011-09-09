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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {

	private Money money;
	private MoneyDBCleaner dbCleaner;
	private int dbCleanerTask;
	private String msgPrefix = ChatColor.GOLD+"["+ChatColor.AQUA+"Money"+ChatColor.GOLD+"]"+ChatColor.WHITE+" ";

	@Override
	public void onEnable() {
		super.onEnable();
		money = new Money(this);
		dbCleaner = new MoneyDBCleaner(money);
		money.server.getScheduler().scheduleAsyncRepeatingTask(this, dbCleaner, 1, 10);
	}

	@Override
	public void loadConfiguration(Configuration cfg) {
	}

	@Override
	public void onDisable() {
		money.server.getScheduler().cancelTask(dbCleanerTask);
		dbCleaner = null;
		for (MoneyDB account : money.loadedAccounts.keySet()) {
			money.saveAccount(account);
		}
		money = null;
		super.onDisable();
	}
	
	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MoneyDB.class);
		list.add(TransactionDB.class);
		return list;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String as, String[] args) {
		args = util.reparseArgs(args);
		if (args.length > 0) {
			String[] newArgs = (String[])util.resizeArray(args, 1, args.length);
			if (args[0].equals("get")) {
				return onCommand_get(cs,cmd,as,newArgs);
			} else if (args[0].equals("set")) {
				return onCommand_set(cs,cmd,as,newArgs);
			} else if (args[0].equals("take")) {
				return onCommand_take(cs,cmd,as,newArgs);
			} else if (args[0].equals("give")) {
				return onCommand_give(cs,cmd,as,newArgs);
			} else if (args[0].equals("info")) {
				return onCommand_info(cs,cmd,as,newArgs);
			//} else if (args[0].equals("")) {
			//	
			}
		} else {
			cs.sendMessage(msgPrefix+"Syntax is /"+as+" get/set/take/give/info ...");
			return true;
		}
		return false;
	}
	
	public boolean onCommand_get(CommandSender cs, Command cmd, String as, String[] args) {
		String getHeldTemplate = ChatColor.WHITE+"%s %s "+ChatColor.YELLOW+"%l"+ChatColor.WHITE+" Coin(s).";
		String getAccount = (cs instanceof Player) ? ((Player)cs).getName() : "Server";
		long getMoney = money.getAccount(getAccount).getAmount(); //Getting NPE here
		if (args.length == 1) {
			if (cs.hasPermission("mmoMoney.getholdings.other")) {
				getAccount = args[1];
				getMoney = money.getAccount(getAccount).getAmount();
				this.sendMessage(true, cs, getHeldTemplate, getAccount, "has", getMoney);
			} else {
				this.sendMessage(true, cs, ChatColor.RED+"You are not allowed to do this!");
			}
		} else if (args.length == 0) {
			this.sendMessage(true, cs, getHeldTemplate, "You", "have", getMoney);
		} else {
			this.sendMessage(true, cs, "Syntax is /%s get (player)", as);
			return true;
		}
		return false;
	}

	public boolean onCommand_set(CommandSender cs, Command cmd, String as, String[] args) {
		if (args[0].equals("")) {
			
		} else if (args[0].equals("")) {
			
		}
		return false;
	}

	public boolean onCommand_take(CommandSender cs, Command cmd, String as, String[] args) {
		if (args[0].equals("")) {
			
		} else if (args[0].equals("")) {
			
		}
		return false;
	}

	public boolean onCommand_give(CommandSender cs, Command cmd, String as, String[] args) {
		if (args[0].equals("")) {
			
		} else if (args[0].equals("")) {
			
		}
		return false;
	}
	
	public boolean onCommand_info(CommandSender cs, Command cmd, String as, String[] args) {
		if (args[0].equals("")) {
			
		} else if (args[0].equals("")) {
			
		}
		return false;
	}
}
