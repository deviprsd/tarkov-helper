package beans;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvNumber;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

/**
 * @author Maxx Persin
 *
 */
public class Ammo {

    @CsvBindByName(column = "Caliber", required = true)
    private String caliber;

    @CsvBindByName(column = "Round", required = true)
    private String name;

    @CsvBindByName(column = "Damage", required = true)
    private int damage;

    @CsvBindByName(column = "Pen Value", required = true)
    private int penetration;

    @CsvBindByName(column = "Frag. Chance*", required = true)
    private int fragmentationChance;

    @CsvBindByName(column = "Armor Damage %", required = true)
    @CsvNumber("###%")
    private double armourDamage;

    @CsvBindAndJoinByName(
            column = "Class [0-9]+", elementType = Integer.class,
            mapType = HashSetValuedHashMap.class, required = true
    )
    private MultiValuedMap<String, Integer>  effectiveness;

    public String getCaliber() {
        return caliber;
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

    public double getArmourDamage() {
        return armourDamage * 100;
    }


    public MultiValuedMap<String, Integer> getEffectiveness() {
        return effectiveness;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPenetration(int penetration) {
        this.penetration = penetration;
    }

    public void setFragmentationChance(int fragmentationChance) {
        this.fragmentationChance = fragmentationChance;
    }

    public void setArmourDamage(double armourDamage) {
        this.armourDamage = armourDamage;
    }

    public void setEffectiveness(MultiValuedMap<String, Integer> effectiveness) {
        this.effectiveness = effectiveness;
    }

    public String toString() {
        return caliber + " " + name + " " + damage + " " + penetration + " " + fragmentationChance + " " + armourDamage;
    }
}
