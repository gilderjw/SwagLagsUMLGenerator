package GraphBuilding;

import java.io.IOException;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import ProgramGraph.ProgramGraph;

public class RecursiveNodeGenerator implements INodeGenerator {
	HashSet<String> visited = new HashSet<>();
	HashSet<String> blacklist = new HashSet<>();

	@Override
	public void generateNodes(ProgramGraph pg, String[] classes) throws IOException {
		for(String s : classes) {
			this.readClass(s, pg, true);
		}
	}

	public void addBlackListed(String arg){
		this.blacklist.add(arg);
	}

	private boolean isBlackListed(String path){
		for(String black: this.blacklist){
			if(path.startsWith(black)){
				return true;
			}
		}
		return false;
	}

	private void readClass(String s, ProgramGraph pg, boolean isWhiteList) throws IOException {
		s = s.replaceAll("\\.","/");
		if(this.visited.contains(s) || (this.isBlackListed(s) && !isWhiteList)){
			return;
		} else {
			this.visited.add(s);
		}
		ClassReader reader = new ClassReader(s);
		ClassNode node = new ClassNode();
		reader.accept(node, ClassReader.EXPAND_FRAMES);

		pg.addNode(node);

		if(node.superName != null) {
			this.readClass(node.superName, pg, false);
		}

		if(node.interfaces != null) {
			for (int i = 0; i < node.interfaces.size(); i++){
				this.readClass((String) node.interfaces.get(i), pg, false);
			}
		}
	}
}
