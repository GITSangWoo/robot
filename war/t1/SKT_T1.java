import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import java.util.Random;
// ------------------------------------------------------------------
// MyFirstBot
// ------------------------------------------------------------------
// A sample bot original made for Robocode by Mathew Nelson.
// Ported to Robocode Tank Royale by Flemming N. Larsen.
//
// Probably the first bot you will learn about.
// Moves in a seesaw motion, and spins the gun around at each end.
// ------------------------------------------------------------------
public class SKT_T1 extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new SKT_T1().start();
    }

    // Constructor, which loads the bot config file
    SKT_T1() {
        super(BotInfo.fromFile("SKT_T1.json"));
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        setBodyColor(Color.fromString("#FF0000"));  // light red
        setTurretColor(Color.fromString("#FF0000")); // dark red
        setRadarColor(Color.fromString("#FF0000"));  // bright red
        setBulletColor(Color.fromString("#FF0000")); // soft red
        setScanColor(Color.fromString("#FF0000"));   // pale red
        Random random = new Random();
        int arenaX=getArenaWidth();
        int arenaY=getArenaHeight();
        // Repeat while the bot is running
        while (isRunning()) {
            turnGunRight(180);
            int myX = (int) getX();
            int myY = (int) getY();
            int movement= random.nextInt(4)+250;
            setForward(movement); 
            if (myX < 50 || myY > arenaX - 50 ){
                setBack(100);
            }
            if (myX > 50 || myY == 50){
                setBack(100);
            }
            if (myX > arenaX-50 || arenaY == 50){
                setBack(100);
            }
            if (myX > arenaX- 50 || arenaY >arenaY- 50){
                setBack(100);
            }
            turnRight(90);
            if (myX < 50 || myY > arenaX - 50 ){
                setBack(100);
            }
            if (myX > 50 || myY == 50){
                setBack(100);
            }
            if (myX > arenaX-50 || arenaY == 50){
                setBack(100);
            }
            if (myX > arenaX- 50 || arenaY >arenaY- 50){
                setBack(100);
            }
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        var distance = distanceTo(e.getX(), e.getY());
        if (distance < 150){
            fire(3);
        }

            
    }
    
    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());
        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }

}
