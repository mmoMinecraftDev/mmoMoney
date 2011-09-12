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
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {

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
	}

	@Override
	public void onDisable() {
		super.onDisable();
		Money api = new Money();
		getDatabase().delete(api.getAccount("SERVER").getAccount());
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
}
