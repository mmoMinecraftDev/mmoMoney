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
import java.util.Arrays;
import java.util.List;
import mmo.Core.MMO;
import mmo.Core.MMOPlugin;
import mmo.Core.util.EnumBitSet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {

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
    protected static long cfgNewAccountMoney = 60;
    protected static long cfgKeepInMemoryFor = 60;
    
	@Override
	public EnumBitSet mmoSupport(EnumBitSet support) {
		support.set(Support.MMO_DATABASE);
		return support;
	}

	@Override
    public void onEnable() {
        super.onEnable();
        
        MMOMoneyAPI.pluginInstance = this;
    }
    
    @Override
    public void loadConfiguration(Configuration cfg) {
        cfgNewAccountMoney = cfg.getInt("account.newmoney", (int)cfgNewAccountMoney);
        cfgKeepInMemoryFor = cfg.getInt("account.keepmemory", (int)cfgKeepInMemoryFor);
        /* Will be in the i18n implementation for mmoCore
         * DO NOT UNCOMMENT
        templateNoPermission = cfg.getString("i18n.NoPermission", templateNoPermission);
        templateSyntaxError = cfg.getString("i18n.SyntaxError", templateSyntaxError);
        templateGetOwn = cfg.getString("i18n.GetOwn", templateGetOwn);
        templateGetOther = cfg.getString("i18n.GetOther", templateGetOther);
        templateSetOwn = cfg.getString("i18n.SetOwn", templateSetOwn);
        templateSetOther = cfg.getString("i18n.SetOther", templateSetOther);
        templateDrop = cfg.getString("i18n.Drop", templateDrop);
        templateTake = cfg.getString("i18n.Take", templateTake);
        templateGive = cfg.getString("i18n.Give", templateGive);
        templateAccountInvalid = cfg.getString("i18n.AccountInvalid", templateAccountInvalid);
        templateNotEnoughMoney = cfg.getString("i18n.NotEnoughMoney", templateNotEnoughMoney);
        templateCurrency = cfg.getString("i18n.Currency", templateCurrency);*/
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(MMOMoneyDB.class);
        list.add(MMOTransactionDB.class);
        return list;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
 /*       args = MMO.smartSplit(MMO.join(args, " "));
        String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);

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
                */sendMessage(cs, templateSyntaxError, label, "<stats|get|set|take|give|drop|admin>");/*
            }
        }*/
        return true;
    }

/*    public boolean onCommand_Stats(CommandSender cs, Command cmd, String label, String[] args) {
        sendMessage(cs, "&f%s v%s - &6%s&f -", this.getDescription().getName(), this.getDescription().getVersion(), this.getDescription().getWebsite());
        return true;
    }

    private boolean onCommand_Get(CommandSender cs, Command cmd, String label, String[] args) {
        String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);

        if (args.length == 0) {
            return onCommand_Get_Own(cs, cmd, label, newArgs);
        } else if (args.length == 1) {
            return onCommand_Get_Other(cs, cmd, label, newArgs);
        } else {
            sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "get <player>");
        }
        return true;
    }

    private boolean onCommand_Get_Own(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.get.own")) {
            String chkAccount = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER"; //If it's the console, set it to SERVER
            DISABLED_Account aCheck = api.getAccount(chkAccount);
            if (aCheck != null) {
                String amount = String.valueOf(aCheck.getMoney());
                sendMessage(cs, DISABLED_Money.templateGetOwn, amount, DISABLED_Money.templateCurrency);
            } else {
                sendMessage(cs, DISABLED_Money.templateAccountInvalid, chkAccount);
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Get_Other(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.get.other")) {
            String chkAccount = args[0];
            DISABLED_Account aCheck = api.getAccount(chkAccount);
            if (aCheck != null) {
                String amount = String.valueOf(aCheck.getMoney());
                sendMessage(cs, DISABLED_Money.templateGetOther, aCheck.getOwner(), amount, DISABLED_Money.templateCurrency);
            } else {
                sendMessage(cs, DISABLED_Money.templateAccountInvalid, chkAccount);
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Set(CommandSender cs, Command cmd, String label, String[] args) {
        String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);

        if (args.length == 1) {
            return onCommand_Set_Own(cs, cmd, label, newArgs);
        } else if (args.length == 2) {
            return onCommand_Set_Other(cs, cmd, label, newArgs);
        } else {
            sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "set <player|amount> (amount)");
        }
        return true;
    }

    private boolean onCommand_Set_Own(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.set.own")) {
            String from = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
            String to = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
            DISABLED_Account aTo = api.getAccount(to);
            if (aTo == null) {
                sendMessage(cs, DISABLED_Money.templateAccountInvalid, to);
            } else {
                Long amount = Long.parseLong(args[0]);
                TransactionDB tdb = aTo.setMoney(amount);
                tdb.setReason("/" + label + " set " + String.valueOf(amount) + " called by " + from);
                if (tdb.isFailed()) {
                    sendMessage(cs, "ERRORCODE: ChatCallSetOwnTDBFail, please notify a Developer");
                } else {
                    sendMessage(cs, DISABLED_Money.templateSetOwn, String.valueOf(amount), DISABLED_Money.templateCurrency);
                }
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Set_Other(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.set.other")) {
            String from = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
            String to = args[0];
            DISABLED_Account aTo = api.getAccount(to);
            if (aTo == null) {
                sendMessage(cs, DISABLED_Money.templateAccountInvalid, to);
            } else {
                Long amount = Long.parseLong(args[1]);
                TransactionDB tdb = aTo.setMoney(amount);
                tdb.setReason("/" + label + " set " + to + " " + String.valueOf(amount) + " called by " + from);
                if (tdb.isFailed()) {
                    sendMessage(cs, "ERRORCODE: ChatCallSetOtherTDBFail, please notify a Developer");
                } else {
                    sendMessage(cs, DISABLED_Money.templateSetOther, aTo.getOwner(), String.valueOf(amount), DISABLED_Money.templateCurrency);
                }
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Take(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.take")) {
            if (args.length == 2) {
                String from = args[0];
                String to = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
                Long amount = Long.parseLong(args[1]);

                DISABLED_Account aFrom = api.getAccount(from);
                DISABLED_Account aTo = api.getAccount(to);
                if (aFrom == null) {
                    sendMessage(cs, DISABLED_Money.templateAccountInvalid, from);
                }
                if (aTo == null) {
                    sendMessage(cs, DISABLED_Money.templateAccountInvalid, to);
                }
                TransactionDB tdb = aFrom.transferMoney(aTo, amount);
                tdb.setReason("/" + label + " take " + from + " " + String.valueOf(amount) + " called by " + to);
                if (tdb.isFailed()) {
                    sendMessage(cs, "ERRORCODE: ChatCallTakeTDBFail, please notify a Developer");
                } else {
                    sendMessage(cs, DISABLED_Money.templateTake, String.valueOf(amount), DISABLED_Money.templateCurrency, aFrom.getOwner());
                }
            } else {
                sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "take <player> <amount>");
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Give(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.give")) {
            if (args.length == 2) {
                String from = (cs instanceof Player) ? ((Player) cs).getName() : "SERVER";
                String to = args[0];
                Long amount = Long.parseLong(args[1]);

                DISABLED_Account aFrom = api.getAccount(from);
                DISABLED_Account aTo = api.getAccount(to);
                if (aFrom == null) {
                    sendMessage(cs, DISABLED_Money.templateAccountInvalid, from);
                }
                if (aTo == null) {
                    sendMessage(cs, DISABLED_Money.templateAccountInvalid, to);
                }
                TransactionDB tdb = aFrom.transferMoney(aTo, amount);
                tdb.setReason("/" + label + " give " + from + " " + String.valueOf(amount) + " called by " + from);
                if (tdb.isFailed()) {
                    sendMessage(cs, "ERRORCODE: ChatCallGiveTDBFail, please notify a Developer");
                } else {
                    sendMessage(cs, DISABLED_Money.templateGive, aTo.getOwner(), String.valueOf(amount), DISABLED_Money.templateCurrency);
                }
            } else {
                sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "give <player> <amount>");
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Admin(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.admin")) {
            String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);
            if (args.length == 0) {
                sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin <account|database>");
            } else {
                if (args[0].equalsIgnoreCase("account")) {
                    return onCommand_Admin_Account(cs, cmd, label, newArgs);
                } else if (args[0].equalsIgnoreCase("database")) {
                    return onCommand_Admin_Database(cs, cmd, label, newArgs);
                } else {
                    sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin <account|database>");
                }
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Admin_Account(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.admin.account")) {
            String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);
            if (args.length == 0) {
                sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin account <list|create|reset|remove>");
            } else {
                if (args[0].equals("list")) {
                    return onCommand_Admin_Account_List(cs, cmd, label, newArgs);
                } else if (args[0].equals("create")) {
                    return onCommand_Admin_Account_Create(cs, cmd, label, newArgs);
                } else if (args[0].equals("remove")) {
                    return onCommand_Admin_Account_Remove(cs, cmd, label, newArgs);
                } else if (args[0].equals("reset")) {
                    return onCommand_Admin_Account_Reset(cs, cmd, label, newArgs);
                } else {
                    sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin account <list|create|reset|remove>");
                }
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
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
        if (cs.hasPermission("mmomoney.admin.database")) {
            String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);
            if (args.length == 0) {
                sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin database <purge|empty|refresh>");
            } else {
                if (args[0].equals("purge")) {
                    return onCommand_Admin_Database_Purge(cs, cmd, label, newArgs);
                } else if (args[0].equals("empty")) {
                    return onCommand_Admin_Database_Empty(cs, cmd, label, newArgs);
                } else if (args[0].equals("refresh")) {
                    return onCommand_Admin_Database_Refresh(cs, cmd, label, newArgs);
                } else {
                    sendMessage(cs, DISABLED_Money.templateSyntaxError, label, "admin database <purge|empty|refresh>");
                }
            }
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Admin_Database_Purge(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.admin.database.purge")) {
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Admin_Database_Empty(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.admin.database.empty")) {
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }

    private boolean onCommand_Admin_Database_Refresh(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs.hasPermission("mmomoney.admin.database.refresh")) {
            getDatabase().refresh(MoneyDB.class);
            getDatabase().refresh(TransactionDB.class);
            sendMessage(cs, DISABLED_Money.templateDatabaseRefresh);
        } else {
            sendMessage(cs, DISABLED_Money.templateNoPermission);
        }
        return true;
    }*/
}
