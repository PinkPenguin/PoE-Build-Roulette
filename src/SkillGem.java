
import java.io.File;

public class SkillGem {
	
	private String name;
	private boolean vaal;
	private boolean excluded;
	private boolean item;
	private File image;

	public SkillGem(String name, boolean vaal, boolean excluded, File image) {
		this.setName(name);
		this.setVaal(vaal);
		this.setExcluded(excluded);
		this.setImage(image);
	}
	
	public SkillGem() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVaal() {
		return vaal;
	}

	public void setVaal(boolean vaal) {
		this.vaal = vaal;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}

	public boolean isItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}
}
