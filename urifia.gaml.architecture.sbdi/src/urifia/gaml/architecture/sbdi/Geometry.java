package urifia.gaml.architecture.sbdi;

public class Geometry {
	   int id_geometry;
	   String name_geometry;
	   String type_geometry;
	   
	public int getId_geometry() {
		return id_geometry;
	}
	public void setId_geometry(int id_geometry) {
		this.id_geometry = id_geometry;
	}
	public String getName_geometry() {
		return name_geometry;
	}
	public void setName_geometry(String name_geometry) {
		this.name_geometry = name_geometry;
	}
	public String getType_geometry() {
		return type_geometry;
	}
	public void setType_geometry(String type_geometry) {
		this.type_geometry = type_geometry;
	}
	public Geometry(int id_geometry, String name_geometry, String type_geometry) {
		super();
		this.id_geometry = id_geometry;
		this.name_geometry = name_geometry;
		this.type_geometry = type_geometry;
	}
	
}
