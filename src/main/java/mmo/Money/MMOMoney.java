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

import mmo.Core.MMOPlugin;
import org.bukkit.util.config.Configuration;

public class MMOMoney extends MMOPlugin {
	
	private Money money;
	private MoneyDBCleaner dbCleaner;
	private int dbCleanerTask;
	
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
		money = null;
		super.onDisable();
	}
}
