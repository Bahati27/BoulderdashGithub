package entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import entity.mobile.Boulder;
import entity.mobile.Diamond;
import entity.mobile.MonsterG;
import entity.mobile.MonsterR;

/**
 * <h1> La classe SpriteTest </h1>
 * @author Kelvin
 *
 */
public class SpriteTest {

	static Boulder boulder;
	static Diamond diamond;
	static MonsterG monsterG;
	static MonsterR monsterR;

	/**
	 * Instatiation d'un roche avant la creation de la table
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		boulder = new Boulder();
		diamond = new Diamond();
		monsterG = new MonsterG();
		monsterR =new MonsterR();
		
	}
	

	/**
	 *  Test sur les images obtenues
	 */
	@Test
	public void testgetImageName() {
		/*final String SExpected = "Rock.png";
		assertEquals(SExpected, boulder.getSprite().getImageName());*/
		final String SExpected1 = "diamond.png";
		assertEquals(SExpected1, diamond.getSprite().getImageName());
		final String SExpected2 = "greenMonster.png";
		assertEquals(SExpected2, monsterG.getSprite().getImageName());
		final String SExpected3 = "redMonster.png";
		assertEquals(SExpected3, monsterR.getSprite().getImageName());
	}

	/**
	 * Test sur le console
	 */
	@Test
	public void testgetConsoleImage() {
		final char CExpected1 = 'O';
		assertEquals(CExpected1, boulder.getSprite().getConsoleImage());
		/*final String SExpected2 = "*";
		assertEquals(SExpected2, diamond.getSprite().getConsoleImage());
		final String SExpected3 = "G";
		assertEquals(SExpected3, monsterG.getSprite().getConsoleImage());
		final String SExpected4 = "R";
		assertEquals(SExpected4, monsterR.getSprite().getConsoleImage());*/
	}

}
