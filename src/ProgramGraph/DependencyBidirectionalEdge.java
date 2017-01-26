package ProgramGraph;

import org.objectweb.asm.tree.ClassNode;

public class DependencyBidirectionalEdge extends IEdge {


	//Notation is on the head
	private String manyHead = "";

	//Notation is on the tail
	private String manyTail = "";

	public DependencyBidirectionalEdge(ClassNode head, ClassNode tail) {
		super(head, tail);
	}

	@Override
	public String getDescription() {
		return "dbidirectional" + this.manyHead + this.manyTail; //lgbtq friendly edge
	}

	public void setManyHead(){
		this.manyHead = "manyH";
	}

	public void setManyTail(){
		this.manyTail = "manyT";
	}

}