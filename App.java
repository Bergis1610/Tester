import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class App {

	public static void main(String[] args) throws IOException{

        String name = "TesterAdder";
		
		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "TesterAdder", null, "java/lang/Object", null);
	
		//Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}
		
		//create the main method
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			
            mv.visitLdcInsn(34.16);
			mv.visitVarInsn(DSTORE,1);
			mv.visitLdcInsn(76.22);
			mv.visitVarInsn(DSTORE,3);
			mv.visitVarInsn(DLOAD,1);
			mv.visitVarInsn(DLOAD,3);
			mv.visitInsn(DADD);
			mv.visitVarInsn(DSTORE,5);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(DLOAD,5);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

			mv.visitInsn(RETURN); 
			mv.visitMaxs(4, 7);
			mv.visitEnd();
		}
		
        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("TesterAdder.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

        /*
		//save bytecode into disk
		FileOutputStream out=new FileOutputStream("bin/FPNumbers.class");
		out.write(cw.toByteArray());
		out.close();
        */

	}//end main

}//end class

