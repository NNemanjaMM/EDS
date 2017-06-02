package tas.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.JAXBElement;

import tas.model.diagram.BlockElement;
import tas.model.diagram.Diagram;
import tas.model.diagram.Flow;
import tas.model.risk_pattern.DiagramPiece;

public class DiagramDecomposer {
	
	private Diagram diagram;
	private HashSet<String> hashedFlows;

	public DiagramDecomposer(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public DiagramPiece decomposeSinglePiece(int position) {
		
		Flow flow = diagram.getFlows().getOrBinaryOrHttpOrHttps().get(position).getValue();
		
		DiagramPiece pieces = new DiagramPiece(flow);		
		
		return pieces;
	}
	
	public ArrayList<DiagramPiece> decomposeAllPieces() {
		
		int flowsCount = diagram.getFlows().getOrBinaryOrHttpOrHttps().size();
		ArrayList<DiagramPiece> pieces = new ArrayList<>();
		
		for (int i = 0; i < flowsCount; i++) {
			pieces.add(decomposeSinglePiece(i));
		}
		
		return pieces;
	}
	
	public DiagramPiece decomposeSinglePieceComplex(int position) {
		
		Flow flow = diagram.getFlows().getOrBinaryOrHttpOrHttps().get(position).getValue();
		
		hashedFlows = new HashSet<>();
		hashedFlows.add(flow.getId());
		
		DiagramPiece pieces = new DiagramPiece(flow);	
		
		BlockElement sourceElement = pieces.getCoreSource();
		BlockElement destinationElement = pieces.getCoreDestination();
		
		List<Flow> coreReturnFlows = findRecurringFlowsForElements(sourceElement, destinationElement);		
		List<Flow> coreDestinationFlowsIn = findFlowsForDestinationElement(destinationElement);
		List<Flow> coreDestinationFlowsOut = findFlowsForSourceElement(destinationElement);
		List<Flow> coreSourceFlowsIn = findFlowsForDestinationElement(sourceElement);
		List<Flow> coreSourceFlowsOut = findFlowsForSourceElement(sourceElement);
		
		pieces.setCoreReturnFlows(coreReturnFlows);
		pieces.setCoreDestinationFlowsIn(coreDestinationFlowsIn);
		pieces.setCoreDestinationFlowsOut(coreDestinationFlowsOut);
		pieces.setCoreSourceFlowsIn(coreSourceFlowsIn);
		pieces.setCoreSourceFlowsOut(coreSourceFlowsOut);		
		
		return pieces;
	}
	
	public ArrayList<DiagramPiece> decomposeAllPiecesComplex() {
		
		int flowsCount = diagram.getFlows().getOrBinaryOrHttpOrHttps().size();
		ArrayList<DiagramPiece> pieces = new ArrayList<>();
		
		for (int i = 0; i < flowsCount; i++) {
			pieces.add(decomposeSinglePieceComplex(i));
		}
		
		return pieces;
	}
	
	public int getPiecesCount() {
		return diagram.getFlows().getOrBinaryOrHttpOrHttps().size();
	}
	
	private List<Flow> findFlowsForSourceElement(BlockElement sourceElement) {
		
		String elementId = sourceElement.getId();
		List<Flow> flowsList = new ArrayList<>();
		List<JAXBElement<? extends Flow>> diagramFlows = diagram.getFlows().getOrBinaryOrHttpOrHttps();
		
		for (JAXBElement<? extends Flow> flow : diagramFlows) {
			if (((BlockElement)flow.getValue().getSource()).getId().equals(elementId)) {
				if (!hashedFlows.contains(flow.getValue().getId())) {
					hashedFlows.add(flow.getValue().getId());
					flowsList.add(flow.getValue());
				}
			}
		}
		
		return flowsList;
	}
	
	private List<Flow> findFlowsForDestinationElement(BlockElement destinationElement) {
		
		String elementId = destinationElement.getId();
		List<Flow> flowsList = new ArrayList<>();
		List<JAXBElement<? extends Flow>> diagramFlows = diagram.getFlows().getOrBinaryOrHttpOrHttps();
		
		for (JAXBElement<? extends Flow> flow : diagramFlows) {
			if (((BlockElement)flow.getValue().getDestination()).getId().equals(elementId)) {
				if (!hashedFlows.contains(flow.getValue().getId())) {
					hashedFlows.add(flow.getValue().getId());
					flowsList.add(flow.getValue());
				}
			}
		}
		
		return flowsList;
	}
	
	private List<Flow> findRecurringFlowsForElements(BlockElement firstElement, BlockElement secondElement) {

		String firstId = firstElement.getId();
		String secondId = secondElement.getId();
		
		List<Flow> flowsList = new ArrayList<>();
		List<JAXBElement<? extends Flow>> diagramFlows = diagram.getFlows().getOrBinaryOrHttpOrHttps();
		
		for (JAXBElement<? extends Flow> flow : diagramFlows) {
			
			if ((((BlockElement)flow.getValue().getDestination()).getId().equals(secondId) && 
					((BlockElement)flow.getValue().getSource()).getId().equals(firstId)) ||
					(((BlockElement)flow.getValue().getDestination()).getId().equals(firstId) && 
					((BlockElement)flow.getValue().getSource()).getId().equals(secondId))) {
				
				if (!hashedFlows.contains(flow.getValue().getId())) {
					hashedFlows.add(flow.getValue().getId());
					flowsList.add(flow.getValue());
				}
				
			}
		}
		
		return flowsList;
	}
	
}
