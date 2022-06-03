package prosthetidist.ifaces;

import java.util.ArrayList;

import prosthetidist.pojos.Material;
import prosthetidist.pojos.Prosthetic;

public interface MaterialManager {
	
	public Material getMaterialByName(String name);
	public ArrayList<Material> getMaterialsFromProstheticCode(Integer code);
	public void uploadMaterialOfProsthetic(Material material, Integer prosCode);

}
