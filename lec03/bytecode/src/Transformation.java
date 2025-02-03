import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.IntInsnNode;

public class Transformation {

    public static void incrementConsts(MethodNode method) {
        InsnList methodBody = method.instructions;

        for (AbstractInsnNode insn : methodBody) {
            if (insn.getOpcode() != Opcodes.BIPUSH) continue;

            IntInsnNode inst = (IntInsnNode) insn;
            inst.operand = inst.operand + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        String className = args[0];

        FileInputStream inputStream = new FileInputStream(className);
        ClassReader classReader = new ClassReader(inputStream);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("main")) {
                incrementConsts(method);
            }
        }

        className = className.replace(".class", "Transformed.class");
        FileOutputStream outputStream = new FileOutputStream(className);
        ClassWriter classWriter = new ClassWriter(0);
        classNode.accept(classWriter);
        outputStream.write(classWriter.toByteArray());
        outputStream.close();

    }
}
