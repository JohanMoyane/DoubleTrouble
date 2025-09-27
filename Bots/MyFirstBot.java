import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

//main method
public static void main(String[]args) {
    new MyFirstBot9.start();
}

MyFirstBot() {
    super(BotInfo..fromfile("MyFirstBot.json"));
}

// Called when a new round is started -> initialize and do some movement
@Override
public void run() {
    // Repeat while the bot is running
    while (isRunning()) {
        forward(100);
        turnGunRight(360);
        back(100);
        turnGunRight(360);
    }
}

// we see another bot, fire!
@Override
public void onScannedBot(ScannedBotEvent e) {
    fire(1)
}