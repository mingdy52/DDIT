package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 XML 문서 생성 예제
 * dom: XMl 파싱 기법
 * @author 306-16
 *
 */
public class T01_DOMCreationTest {
	/*
	 * W3C(World Wide Web Consortium) DOM(Document Object Model) 표준에 대하여
	 * 
	 * DOM은 문서에 접근하는 표준방법으로서, 이를 이용하면 플랫폼 및 개발언어에 독립적으로 문서의 내용, 구조 및 스타일 정보를 동적으로
	 * 처리(접근, 수정, 삭제) 할 수 있다.
	 * 
	 * W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
	 * 
	 * Core DOM - 모든 문서타입을 위한 핵심 표준 모델(API) XML DOM - XML문서를 위한 표준 모델 HTML DOM -
	 * HTML문서를 위한 표준 모델
	 * 
	 * 예를 들면, HTML DOM은 HTML 엘리먼트 요소 및 속성 정보를 핸들링 하기 위한 인터페이스를 제공한다.
	 * 
	 */
	public void createDoc() throws ParserConfigurationException, TransformerException {
		// XML 문서를 생성하기 위한 DocumentBuilder객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// : XML 문서에서 DOM 오브젝트 트리를 생성하는 parser를 얻을 수 있다
		
		DocumentBuilder builder = dbf.newDocumentBuilder();
		// : XML 문서에서 DOM Document instance를 얻는다. 
		// 	  클래스를 사용해서 XML 로부터 Document를 얻을 수 있다.

		// Document 객체 생성
		Document document = builder.newDocument();
		// Document 객체는 웹 페이지 그 자체를 의미.
		// 웹 페이지에 존재하는 HTML 요소에 접근할 때는 반드시 Document 객체부터 시작해야 함.

		// root 엘리먼트 생성
		Element root = document.createElement("root");

		// booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist");

		// book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");

		// 자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");

		// book 엘리먼트에 자식엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

		// booklist 엘리먼트에 자식엘리먼트 추가하기
		booklist.appendChild(book);

		// ---------------------------------------------------
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("홍길동");
		price = document.createElement("price");
		price.setTextContent("28000");
		
		// book 엘리먼트에 자식엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		// booklist 엘리먼트에 자식엘리먼트 추가하기
		booklist.appendChild(book);
		
		// ---------------------------------------------------
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");

		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("이몽룡");
		price = document.createElement("price");
		price.setTextContent("30000");

		// book 엘리먼트에 자식엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

		// booklist 엘리먼트에 자식엘리먼트 추가하기
		booklist.appendChild(book);

		// ---------------------------------------------------
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");

		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("일지매");
		price = document.createElement("price");
		price.setTextContent("40000");

		// book 엘리먼트에 자식엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

		// booklist 엘리먼트에 자식엘리먼트 추가하기
		booklist.appendChild(book);

		// ---------------------------------------------------

		// 루트 엘리먼트에 booklist추가하기
		root.appendChild(booklist);

		// 도큐멘트에 root 엘리먼트 추가하기
		document.appendChild(root);

		// XML 문자열로 변환하기
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		DOMSource source = new DOMSource(document);
		//ㄴ DOM (문서 개체 모델) 트리 형태로 변환 소스 트리의 홀더 역할을 함.
		
		StreamResult result = new StreamResult(out);
		//ㄴ XML, 일반 텍스트, HTML 또는 기타 마크 업 형식이 될 수있는 변환 결과의 홀더 역할을 함.

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		// 출력 인코딩 설정
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
												// ㄴ 들여쓰기
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		// 변환작업 시작
		transformer.transform(source, result);

		// 파일에 문서내용 저장하기
		try (FileOutputStream fos = new FileOutputStream("./src/new_book.xml")) {
			fos.write(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		new T01_DOMCreationTest().createDoc();
	}
	
}
