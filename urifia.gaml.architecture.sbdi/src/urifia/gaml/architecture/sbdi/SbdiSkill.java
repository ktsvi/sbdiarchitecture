package urifia.gaml.architecture.sbdi;

import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.GamlAnnotations.action;
import msi.gama.precompiler.GamlAnnotations.arg;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.skill;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gaml.skills.Skill;
import msi.gaml.types.IType;

@vars ({ 
	@variable (
		name = "agentsbdi_in",
		type = IType.LIST,
		of = IType.LIST,
		doc = @doc ("for each land of the region, the list of agents for each region")),
	@variable (
		name = "all_agentsdi",
		type = IType.LIST,
		of = IType.AGENT,
		doc = @doc ("the list of sbdi agents in all the regions (shapefile)")),
	@variable (
				name = "maxspeed",
				type = IType.FLOAT,
				doc = @doc ("the maximal speed on the rregion"))
})
	@skill (
	name = "skill_road",
	concept = { IConcept.TRANSPORT, IConcept.SKILL },
	doc = @doc ("A skill for agents representing roads in traffic simulations"))
	@SuppressWarnings ({ "unchecked", "rawtypes" })

//this skills allow to verify if regionX is in regionY (Overlap or Partial Overlap)
public class SbdiSkill extends Skill {
	public final static String MAXSPEED = "maxspeedinregion";
	public final static String AGENTSBDI = "all_agentsbdi";
	public final static String AGENTSBDI_IN = "agentsdi_in";
	
	@getter (AGENTSBDI_IN)
	public static List getAgentsOn(final IAgent agent) {
		return (List) agent.getAttribute(AGENTSBDI_IN);
	}
	
	public static void setAgentsOn(final IAgent agent, final List agents) {
		agent.setAttribute(AGENTSBDI_IN, agents);
	}
	
	@getter (AGENTSBDI)
	public static List getAgents(final IAgent agent) {
		return (List) agent.getAttribute(AGENTSBDI);
	}
	
	public static void setAgents(final IAgent agent, final List agents) {
		agent.setAttribute(AGENTSBDI, agents);
	}
	
	@getter (MAXSPEED)
	public static Double getMaxSpeed(final IAgent agent) {
		return (Double) agent.getAttribute(MAXSPEED);
	}

	@setter (MAXSPEED)
	public static void setMaxSpeed(final IAgent agent, final Double sp) {
		agent.setAttribute(MAXSPEED, sp);
	}
	
	
/*	public static String GEOAGENTS_IN = "all_agents_in";
	public static String GEOAGENTS_INCLUDING = "all_agents_including";
	public static String REGION_IN = "region_in";
	public static String REGION_INCLUDING = "region_including";
	
	@getter(GEOAGENTS_IN)
	public static String getGeoagentsIn() {
		return GEOAGENTS_IN;
	}
	
	@getter(GEOAGENTS_INCLUDING)
	public static String getGeoagentsIncluding() {
		return GEOAGENTS_INCLUDING;
	}
	
	@getter(REGION_IN)
	public static String getRegionIn() {
		return REGION_IN;
	}
	
	@getter(REGION_INCLUDING)
	public static String getRegionIncluding() {
		return REGION_INCLUDING;
	}

	@setter(GEOAGENTS_IN)
	public static void setGeoagentsIn(String geoagentsIn) {
		GEOAGENTS_IN = geoagentsIn;
	}

	@setter(GEOAGENTS_INCLUDING)
	public static void setGeoagentsIncluding(String geoagentsIncluding) {
		GEOAGENTS_INCLUDING = geoagentsIncluding;
	}

	@setter(REGION_IN)
	public static void setRegionIn(String regionIn) {
		REGION_IN = regionIn;
	}

	@setter(REGION_INCLUDING)
	public static void setRegionIncluding(String regionIncluding) {
		REGION_INCLUDING = regionIncluding;
	}
	
*/	
	
}

//function XinY