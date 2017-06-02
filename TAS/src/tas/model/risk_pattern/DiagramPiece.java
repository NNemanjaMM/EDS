package tas.model.risk_pattern;

import java.util.ArrayList;
import java.util.List;

import tas.model.diagram.BlockElement;
import tas.model.diagram.Flow;

public class DiagramPiece {
	
	private Flow coreFlow;
	private BlockElement coreSource;
	private BlockElement coreDestination;
	
	private List<Flow> coreReturnFlows;
	
	private List<Flow> coreDestinationFlowsIn;
	private List<Flow> coreDestinationFlowsOut;
	private List<Flow> coreSourceFlowsIn;
	private List<Flow> coreSourceFlowsOut;
	
	
	public DiagramPiece(Flow coreFlow) {
		this.coreFlow = coreFlow;
		this.coreSource = (BlockElement) coreFlow.getSource();
		this.coreDestination = (BlockElement) coreFlow.getDestination();
		
		coreReturnFlows = new ArrayList<Flow>();		
		coreDestinationFlowsIn = new ArrayList<Flow>();
		coreDestinationFlowsOut = new ArrayList<Flow>();
		coreSourceFlowsIn = new ArrayList<Flow>();
		coreSourceFlowsOut = new ArrayList<Flow>();
	}
	
	
	public Flow getCoreFlow() {
		return coreFlow;
	}
	
	public BlockElement getCoreSource() {
		return coreSource;
	}
	
	public BlockElement getCoreDestination() {
		return coreDestination;
	}
	
	public List<Flow> getCoreReturnFlows() {
		return coreReturnFlows;
	}
	
	public List<Flow> getCoreDestinationFlowsIn() {
		return coreDestinationFlowsIn;
	}
	
	public List<Flow> getCoreDestinationFlowsOut() {
		return coreDestinationFlowsOut;
	}
	
	public List<Flow> getCoreSourceFlowsIn() {
		return coreSourceFlowsIn;
	}
	
	public List<Flow> getCoreSourceFlowsOut() {
		return coreSourceFlowsOut;
	}
	
	
	public void setCoreFlow(Flow coreFlow) {
		this.coreFlow = coreFlow;
	}
	
	public void setCoreSource(BlockElement coreSource) {
		this.coreSource = coreSource;
	}
	
	public void setCoreDestination(BlockElement coreDestination) {
		this.coreDestination = coreDestination;
	}
	
	public void setCoreReturnFlows(List<Flow> coreReturnFlows) {
		this.coreReturnFlows = coreReturnFlows;
	}
	
	public void setCoreDestinationFlowsIn(List<Flow> coreDestinationFlows) {
		this.coreDestinationFlowsIn = coreDestinationFlows;
	}
	
	public void setCoreDestinationFlowsOut(List<Flow> coreSourceFlows) {
		this.coreDestinationFlowsOut = coreSourceFlows;
	}
	
	public void setCoreSourceFlowsIn(List<Flow> coreDestinationFlows) {
		this.coreSourceFlowsIn = coreDestinationFlows;
	}
	
	public void setCoreSourceFlowsOut(List<Flow> coreSourceFlows) {
		this.coreSourceFlowsOut = coreSourceFlows;
	}
	
	
	public void addCoreReturnFlow(Flow coreReturnFlow) {
		this.coreReturnFlows.add(coreReturnFlow);
	}
	
	public void addCoreDestinationFlowIn(Flow coreDestinationFlowIn) {
		this.coreDestinationFlowsIn.add(coreDestinationFlowIn);
	}
	
	public void addCoreDestinationFlowOut(Flow coreDestinationFlowOut) {
		this.coreDestinationFlowsOut.add(coreDestinationFlowOut);
	}
	
	public void addCoreSourceFlowIn(Flow coreSourceFlowIn) {
		this.coreSourceFlowsIn.add(coreSourceFlowIn);
	}
	
	public void addCoreSourceFlowOut(Flow coreSourceFlowOut) {
		this.coreSourceFlowsOut.add(coreSourceFlowOut);
	}
	
	public String toString() {
		return this.coreFlow.getId();
	}
}
