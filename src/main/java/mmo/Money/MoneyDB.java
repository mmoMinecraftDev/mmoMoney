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

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "mmo_Money")
public class MoneyDB {

	@Id
	@NotNull
	@NotEmpty
	@Length(max=128)
	private String owner;
	@NotEmpty
	private long amount;

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
