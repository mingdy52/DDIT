package basic;

public class Reference {
	/* 
	  Content-Disposition
	 	HTTP Response Header에 들어감.
	 	HTTP Response Body에 오는 컨텐츠의 기질/성향을 알려주는 속성
	 	default: inline. web에 전달되는 data라고 생각하면 된다.
	 	특수한 경우- Content-Disposition: attachment; filename="hello.jpg"
	 				attachment는 filename과 함께 사용하면 body에 오는 값을 다운받으라는 뜻
     */
	
	/*
	  Body에 담길 data가 클 경우 => HTTP responser가 여러 번 나감 => multipart
	  
	  EX)
	  	1. Response Header
	  		Content-Type: multipart/form-data; boundary="boundary"
	  	2. Response Header
	  		Content-Disposition: form-data; name="field1"
	  	3. Response Header
	  		Content-Disposition: form-data; name="field2"; filename="example.txt"
	 */
}
