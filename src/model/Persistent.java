package model;

public interface Persistent {
	public boolean isPersistent();

	public boolean hasChanged();
	
	public int getId();

	public void setId(int id);

}
