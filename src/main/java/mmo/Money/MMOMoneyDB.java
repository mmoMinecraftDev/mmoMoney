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
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "mmo_Money")
public class MMOMoneyDB implements Serializable {

    @Id
    @NotNull
    @NotEmpty
    private long accountId;
    @NotNull
    @NotEmpty
    @Length(max = 256)
    private String owner;
    @NotNull
    @NotEmpty
    private long amount;
    @NotNull
    @NotEmpty
    private long timeCreated;
    @NotNull
    @NotEmpty
    private long timeModified;

    public MMOMoneyDB() {
        timeCreated = System.currentTimeMillis();
        timeModified = System.currentTimeMillis();
    }

    public long getAccountId() {
        return accountId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
        timeModified = System.currentTimeMillis();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        timeModified = System.currentTimeMillis();
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public long getTimeModified() {
        return timeModified;
    }
}
