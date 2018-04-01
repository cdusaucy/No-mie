package Model;
import Model.Patronus;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable{
	public ArrayList<Patronus> patr = new ArrayList<Patronus>();
	public ArrayList<InvisibilityCloak> sic = new ArrayList<InvisibilityCloak>();
	public ArrayList<BroomStick> sbs = new ArrayList<BroomStick>();
	public ArrayList<MagicalPotion> mp = new ArrayList<MagicalPotion>();
	public ArrayList<Horcruxs> hor = new ArrayList<Horcruxs>();
}
