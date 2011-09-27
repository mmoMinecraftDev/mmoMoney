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

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "mmo_Transaction")
public class MMOTransactionDB implements Serializable {

    @Id
    private long transactionId;
    @NotNull
    @NotEmpty
    private long timeDone;
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private MMOMoneyDB fromAccount;
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private MMOMoneyDB toAccount;
    @NotNull
    private String reasonWhy;

    public MMOTransactionDB() {
        timeDone = System.currentTimeMillis();
    }

    public MMOMoneyDB getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(MMOMoneyDB fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getReasonWhy() {
        return reasonWhy;
    }

    public void setReasonWhy(String reasonWhy) {
        this.reasonWhy = reasonWhy;
    }

    public long getTimeDone() {
        return timeDone;
    }

    public MMOMoneyDB getToAccount() {
        return toAccount;
    }

    public void setToAccount(MMOMoneyDB toAccount) {
        this.toAccount = toAccount;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
