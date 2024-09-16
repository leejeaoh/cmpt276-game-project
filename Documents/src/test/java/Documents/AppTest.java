// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import Documents.main.GamePanel;
// import Documents.main.KeyControl;
// import Documents.entity.Entity;
// import Documents.entity.MainCharacter;
// import Documents.tile.TileFactory;

// public class AppTest {
//     GamePanel gamePanel = new GamePanel();
//     KeyControl keyControl = new KeyControl();

//     @Test
//     void testMovementUp() {
//         MainCharacter character = new MainCharacter(gamePanel, keyControl);
//         character.settingDefaultValue();
//         int originY = character.wyPos;
//         keyControl.upPressed = true;
//         character.move();

//         assertEquals(originY - character.vel, character.wyPos, "Character did not move up as expected");
//     }
// }