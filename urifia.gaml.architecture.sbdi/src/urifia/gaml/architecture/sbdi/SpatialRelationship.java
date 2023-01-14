package urifia.gaml.architecture.sbdi;

import msi.gama.common.interfaces.IValue;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.GamaGisGeometry;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.operators.Maths;
import msi.gaml.operators.Spatial;
import msi.gaml.operators.Graphs;
import msi.gaml.types.GamaGeometryType;
import msi.gaml.types.IType;

@vars ({ @variable (
		name = "sr_name",
		type = IType.STRING,
		doc = @doc ("the name of spatial or topological relationship for two regions")),
		@variable (
				name = "rcc8_matrix",
				type = IType.MATRIX,
				doc = @doc ("the value of spatial or topological relationship using RCC8")),
				@variable (
				name = "nine_inter_matrix",
				type = IType.MATRIX,
				doc = @doc ("the agent getting spatial predicate")) 
       })

public class SpatialRelationship implements IValue{
	public static final int[][] DC= {{0,0},{0,0}};
	public static final int[][] EC= {{1,0},{0,0}};
	public static final int[][] PO= {{1,1},{1,1}};
	public static final int[][] EQ= {{1,0},{0,1}};
	public static final int[][] TPP = {{1,1},{0,1}};
	public static final int[][] NTPP = {{0,1},{0,1}};
	public static final int[][] TPP1= {{1,0},{1,1}};
	public static final int[][] NTPP1= {{0,0},{1,1}};
	enum RCC8{DC, EC, PO, EQ, TPP, NTPP, TPP1, NTPP1};
	String ec="0000";
	String sr_name;
	int rcc8_matrix[][];
	int nine_inter_matrix[][];
	GamaGeometryType region_x, region_y;
	String bb, bi, ib, ii;
  
	@getter ("sr_name")
	public String getSr_name() {
		return sr_name;
	}
	
	@getter ("rcc8_matrix")
	public int[][] getRcc8_matrix() {
		return rcc8_matrix;
	}
	
	@getter ("rcc8_matrix")
	public int[][] getNine_inter_matrix() {
		return nine_inter_matrix;
	}

	@Override
	public String stringValue(IScope scope) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValue copy(IScope scope) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	public SpatialRelationship(String sr_name, int rm[][]) {
		super();
		this.sr_name = sr_name; 
		this.rcc8_matrix = rm;
	}

	public SpatialRelationship(String sr_name, int[][] rcc8_matrix, int[][] nine_inter_matrix) {
		super();
		this.sr_name = sr_name;
		this.rcc8_matrix = rcc8_matrix;
		this.nine_inter_matrix = nine_inter_matrix;
	}	
    
	public GamaGeometryType getRegion_x() {
		return region_x;
	}

	public void setRegion_x(GamaGeometryType region_x) {
		this.region_x = region_x;
	}

	public GamaGeometryType getRegion_y() {
		return region_y;
	}

	public void setRegion_y(GamaGeometryType region_y) {
		this.region_y = region_y;
	}
	
	public int[][] getRCC8MatricesValues(String name_rcc8) {
		int [][] relation;
		name_rcc8=name_rcc8.toUpperCase();
		if(name_rcc8.equals("DC")) { relation= this.DC;}
		else if(name_rcc8.equals("EQ")) { relation= this.EQ;}
		else if(name_rcc8.equals("NTPP")) { relation= this.NTPP;}
		else if(name_rcc8.equals("NTPP1")) { relation= this.NTPP1;}
		else if(name_rcc8.equals("TPP")) { relation= this.TPP;}
		else if(name_rcc8.equals("TPP1")) { relation= this.TPP1;}
		else if(name_rcc8.equals("PO")) { relation= this.PO;}
		else relation= this.EC;
		return relation;
	}
	public int[][] getRCC8MatrixValues(String name_rcc8) {
		int [][] relation;
		name_rcc8=name_rcc8.toUpperCase();
		if(name_rcc8.equals("DC")) { relation= this.DC;}
		else if(name_rcc8.equals("EQ")) { relation= this.EQ;}
		else if(name_rcc8.equals("NTPP")) { relation= this.NTPP;}
		else if(name_rcc8.equals("NTPP1")) { relation= this.NTPP1;}
		else if(name_rcc8.equals("TPP")) { relation= this.TPP;}
		else if(name_rcc8.equals("TPP1")) { relation= this.TPP1;}
		else if(name_rcc8.equals("PO")) { relation= this.PO;}
		else relation= this.EC;
		return relation;
	}

	public SpatialRelationship(GamaGeometryType x, GamaGeometryType y, String sr_name) {
		super();
		this.sr_name = sr_name.toUpperCase();
		this.region_x=x;
		this.region_y=y;
		this.rcc8_matrix=this.getRCC8MatrixValues(sr_name);
	}	
	
	//calcul topologiques mentales for RCC8

	
	//calcul topologiques mentales pour 9-intersection
	public Integer topologieDistance(int[][] M1, int[][] M2) {
		int td=0;
		
		for(int i=0; i < M1.length; i++) {
			for(int j=0; j < M2.length; j++) {
				td += Maths.abs(M1[i][j] - M2[i][j]);
			}
		}
       return td;
	}
	
	public Integer topologieDistanceRCC(String rel1, String rel2) {
		int td=0;
	/*	
		if(rel1=this.EC and rel2=this.PO) {
			 
		}else if (rel1=this.EC and rel2=this.PO)
		
		for(int i=0; i < RCC8.DC.length; i++) {
			for(int j=0; j < M2.length; j++) {
				td += Maths.abs(M1[i][j] - M2[i][j]);
			}
		}  */
       return td;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String PO="PO";
		int[][] Mtopo1 = {{1,0,0},{0,0,1},{1,1,1}};
		int[][] Mtopo2 = {{0,1,1},{1,1,1},{1,1,1}};
		
        SpatialRelationship topoDistance = new SpatialRelationship(PO, Mtopo1, Mtopo2);
       System.out.println(topoDistance.topologieDistance(Mtopo1, Mtopo2));
	}

}