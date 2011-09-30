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
import org.bukkit.entity.Player;
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
        support.set(Support.MMO_PLAYER);
        return support;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        MMOMoneyAPI.pluginInstance = this;

        MMOMoneyAPI.createAccount("SERVER");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void loadConfiguration(Configuration cfg) {
        cfgNewAccountMoney = cfg.getInt("account.newmoney", (int) cfgNewAccountMoney);
        cfgKeepInMemoryFor = cfg.getInt("account.keepmemory", (int) cfgKeepInMemoryFor);
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
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(MMOMoneyDB.class);
        list.add(MMOTransactionDB.class);
        return list;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        args = MMO.smartSplit(MMO.join(args, " "));
        String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);

        if (args.length == 0) {
            args = new String[1];
            args[0] = "get";
        }

        if (args[0].equalsIgnoreCase("info")) {
            onCommand_info(cs, cmd, label, newArgs);
        } else if (args[0].equalsIgnoreCase("get")) {
        } else if (args[0].equalsIgnoreCase("set")) {
        } else if (args[0].equalsIgnoreCase("take")) {
        } else if (args[0].equalsIgnoreCase("give")) {
        } else if (args[0].equalsIgnoreCase("drop")) {
        } else if (args[0].equalsIgnoreCase("admin")) {
        } else {
            sendMessage(cs, templateSyntaxError, label, "<info|get|set|take|give|drop|admin>");
        }

        return true;
    }

    public boolean onCommand_info(CommandSender cs, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sendMessage(cs, "&f%s v%s -&6%s&f -", this.getDescription().getName(), this.getDescription().getVersion(), this.getDescription().getWebsite());
            sendMessage(cs, "&7Coded by: %s", this.getDescription().getAuthors());
        } else {
            sendMessage(cs, templateSyntaxError, label, "info");
        }
        return true;
    }

    public boolean onCommand_get(CommandSender cs, Command cmd, String label, String[] args) {
        String[] newArgs = (args.length > 0) ? (Arrays.copyOfRange(args, 1, args.length)) : (new String[0]);

        if (args.length == 1) { //Own
            onCommand_get_own(cs, cmd, label, newArgs);
        } else if (args.length == 2) { //Other
            onCommand_get_other(cs, cmd, label, newArgs);
        } else {
            sendMessage(cs, templateSyntaxError, label, "get <name>");
        }

        return true;
    }

    public boolean onCommand_get_own(CommandSender cs, Command cmd, String label, String[] args) {
        MMOAccount userAccount = MMOMoneyAPI.getAccount(cs.getName());
        
        return true;
    }

    public boolean onCommand_get_other(CommandSender cs, Command cmd, String label, String[] args) {
        return true;
    }

    public boolean onCommand_set(CommandSender cs, Command cmd, String label, String[] args) {
        return true;
    }

    public boolean onCommand_take(CommandSender cs, Command cmd, String label, String[] args) {
        return true;
    }

    public boolean onCommand_give(CommandSender cs, Command cmd, String label, String[] args) {
        return true;
    }

    public boolean onCommand_admin(CommandSender cs, Command cmd, String label, String[] args) {
        return true;
    }
    /*
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}
    public boolean onCommand_(CommandSender cs, Command cmd, String label, String[] args) {}*/
}
