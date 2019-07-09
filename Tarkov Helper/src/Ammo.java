
/**
 * @author Maxx Persin
 *
 */
public class Ammo {

	private String roundType;
	private String name;
	private int damage;
	private int penetration;
	private int fragmentationChance;
	private int armourDamage;
	private int highestArmourLevelCanPen;
	private int[] effectiveness;

	public Ammo(String rT, String n, int d, int pen, int f, int a, int[] e) {
		roundType = rT;
		name = n;
		damage = d;
		penetration = pen;
		fragmentationChance = f;
		armourDamage = a;
		effectiveness = e;
		int temp = 0;
		highestArmourLevelCanPen = 1;
		if (effectiveness[0] != 0) {
			for (int i = 0; i < effectiveness.length; i++) {
				if (effectiveness[i] >= temp && effectiveness[i] > 3) {
					temp = effectiveness[i];
					highestArmourLevelCanPen = i + 1;
				}
			}
		}
	}

	public String getRoundType() {
		return roundType;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int getPenetration() {
		return penetration;
	}

	public int getFragmentationChance() {
		return fragmentationChance;
	}

	public int getArmourDamage() {
		return armourDamage;
	}
	
	public int getHighestArmourPen() {
		return highestArmourLevelCanPen;
	}
	
	public int[] getEffectiveness() {
		return effectiveness;
	}
	
	public String toString() {
		return roundType + " " + name + " " + damage + " " + penetration + " " + fragmentationChance + " " + armourDamage + " " + highestArmourLevelCanPen;
	}

}
