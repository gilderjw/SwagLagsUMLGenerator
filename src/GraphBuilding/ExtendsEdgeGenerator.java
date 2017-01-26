package GraphBuilding;

import org.objectweb.asm.tree.ClassNode;

import ProgramGraph.ExtendsEdge;
import ProgramGraph.IEdge;
import ProgramGraph.ProgramGraph;

public class ExtendsEdgeGenerator implements IEdgeGenerator{

	@Override
	public void generateEdge(ProgramGraph pg) {
		for(ClassNode node: pg.getNodes()){
			for(ClassNode other: pg.getNodes()){
				if((node.superName != null) && node.superName.equals(other.name)){
					IEdge e = new ExtendsEdge(other, node);
					pg.addEdge(e);
				}
			}
		}
	}

}