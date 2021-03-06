package GraphReading;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import application.FieldReader;
import jdk.internal.org.objectweb.asm.Opcodes;

public class GraphVizPublicFieldReader implements FieldReader{
	@Override
	public List<FieldNode> getFields(ClassNode c) {
		List<FieldNode> methods = c.fields;
		List<FieldNode> returnMethods = new ArrayList<FieldNode>();
		for(FieldNode method: methods){
			if((method.access & Opcodes.ACC_PUBLIC) > 0){
				returnMethods.add(method);
			}
		}
		return returnMethods;
	}
}
