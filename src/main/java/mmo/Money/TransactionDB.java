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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity()
@Table(name = "mmo_Transaction")
public class TransactionDB {

	@Id
	private int id;
	@NotNull
	@NotEmpty
	@OneToOne
	private MoneyDB fromAccount;
	@NotNull
	@NotEmpty
	@OneToOne
	private MoneyDB toAccount;
	@NotNull
	@NotEmpty
	private long amount;
	@NotNull
	@Length(max = 256)
	private String reason;
	@NotNull
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date onDate;
	@NotNull
	private boolean failed;

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public MoneyDB getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(MoneyDB fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Date getOnDate() {
		return onDate;
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public MoneyDB getToAccount() {
		return toAccount;
	}

	public void setToAccount(MoneyDB toAccount) {
		this.toAccount = toAccount;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}
}
