package basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.stream.FileImageOutputStream;

public class T16_NonSerializableParentTest {
/*
 *  부모 클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 *  부모 객체의 필드값 처리 방법
 *  
 *  	1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야 한다.
 *  	2. 자식클래스에 witeObjec()와 readObject()를 이용하여 
 *  		부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos 
			= new FileOutputStream("d:/D_Other/nonSerializableText.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child);
		oos.flush();
		oos.close();
		
		////////////////////////////////////////////////////////////////////
		
		ObjectInputStream ois 
			= new ObjectInputStream(
					new FileInputStream("d:/D_Other/nonSerializableTest.bin"));
		
		Child child2 = (Child) ois.readObject(); //역직렬화/
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("parentName : " + child2.getChildName());
	}
	
}

//Serializable을 구현하지 않은 부모 클래스
class parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
}

class Child extends parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
		직렬화 될 때 자동으로 호출됨
		(접근제한자가 private이 아니면 자동 호출되지 않음)
		@param out
		@throws IOException
	*/
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getParentName()); // 부모객체 필드값 저장하기
		out.defaultWriteObject();
	}
	
	/**
		역직렬화 될 때 자동으로 호출됨
		(접근제한자가 private이 아니면 자동 호출되지 않음)
		@param in
		@throws IOException
		@throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF()); // 부모객체 필드값 읽어와 설정하기
		in.defaultReadObject();
	}
}

