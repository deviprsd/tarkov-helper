package res;

/**
 * @author Maxx Persin
 *
 */
public class Ammo implements Comparable {

    private String roundType;
    private String name;
    private int damage;
    private int penetration;
    private int fragmentationChance;
    private int armourDamage;
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


    public int[] getEffectiveness() {
        return effectiveness;
    }

    public String toString() {
        return roundType + " " + name + " " + damage + " " + penetration + " " + fragmentationChance + " " + armourDamage;
    }

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
