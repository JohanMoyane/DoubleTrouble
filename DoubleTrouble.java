//STARTING BOT

import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;
import java.awt.Color;

public class DoubleTrouble extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new DoubleTrouble().start();
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {

        // Repeat while the bot is running
        while (isRunning()) {
            forward(170);
            turnGunRight(360);
            back(120);
            turnGunRight(360);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(1);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
}
