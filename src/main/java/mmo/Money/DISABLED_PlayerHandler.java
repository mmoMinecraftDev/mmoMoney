/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmo.Money;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Xaymar
 */
public class DISABLED_PlayerHandler extends PlayerListener {

    DISABLED_Money api = new DISABLED_Money();

    //This should handle the automatic creation and unloading of accounts on connect/disconnect.
    @Override
    public void onPlayerKick(PlayerKickEvent event) {
        super.onPlayerKick(event);
        api.getAccount(event.getPlayer().getName()).setChanged(true);
    }

    @Override
    public void onPlayerLogin(PlayerLoginEvent event) {
        super.onPlayerLogin(event);
        api.createAccount(event.getPlayer().getName());
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        super.onPlayerQuit(event);
        api.getAccount(event.getPlayer().getName()).setChanged(true);
    }
}
