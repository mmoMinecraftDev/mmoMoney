/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmo.Money;

import com.avaje.ebean.EbeanServer;
import java.util.Date;
import java.util.HashMap;
import org.bukkit.Server;

/**
 *
 * @author Xaymar
 */
public class Money {

	private MMOMoney plugin;
	private Server server;
	private EbeanServer database;

	public Money(MMOMoney plugin) {
		this.plugin = plugin;
		this.server = plugin.getServer();
		this.database = plugin.getDatabase();
	}

}
