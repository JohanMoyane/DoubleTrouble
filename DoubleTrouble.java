//STARTING BOT

import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;

public class DoubleTrouble extends Bot {

    int turnDirection = 1;
    // The main method starts our bot
    public static void main(String[] args) {
        new DoubleTrouble().start();
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        setBodyColor(Color.BLACK);
        setTracksColor(Color.RED);
        setRadarColor(Color.GREEN);


        while (isRunning()) {
            turnGunRight(10);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        turnToFaceTarget(e.getX(), e.getY());
        var bearingFromGun = gunBearingTo(e.getX(), e.getY());
        turnGunLeft(bearingFromGun);

        if (Math.abs(bearingFromGun) <= 3 && getGunHeat() == 0) {
            fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
        }
        var distance = distanceTo(e.getX(), e.getY());
        forward(distance + 5);

    }
    @Override
    public void onHitBot(HitBotEvent e) {
        turnToFaceTarget(e.getX(), e.getY());

        // Determine a shot that won't kill the bot...
        // We want to ram him instead for bonus points
        if (e.getEnergy() > 16) {
            fire(3);
        } else if (e.getEnergy() > 10) {
            fire(2);
        } else if (e.getEnergy() > 4) {
            fire(1);
        } else if (e.getEnergy() > 2) {
            fire(.5);
        } else if (e.getEnergy() > .4) {
            fire(.1);
        }
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
    private void turnToFaceTarget(double x, double y) {
        var bearing = bearingTo(x, y);
        if (bearing >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnLeft(bearing);
    }
}