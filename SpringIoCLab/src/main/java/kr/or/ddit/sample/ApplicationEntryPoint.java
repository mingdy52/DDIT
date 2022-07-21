package kr.or.ddit.sample;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.collection.properties.DBInfoVO;
import kr.or.ddit.sample.controller.SampleController;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.view.SampleView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationEntryPoint {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext parentContainer 
				= new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/parent-context.xml"); // 상위가 없는 최상위 컨테이너. 자식은 둘(child1,2)
		ConfigurableApplicationContext childContainer1 
				= new ClassPathXmlApplicationContext(
						new String[] {"kr/or/ddit/sample/conf/child-context.xml"}, parentContainer); 
		ConfigurableApplicationContext childContainer2 
				= new ClassPathXmlApplicationContext(
						new String[] {"kr/or/ddit/collection/properties/conf/Properties-Context.xml"}, parentContainer);
		parentContainer.registerShutdownHook();
		childContainer1.registerShutdownHook();
		childContainer2.registerShutdownHook();
		
		SampleController controller = childContainer1.getBean(SampleController.class);
		SampleView view = childContainer1.getBean(SampleView.class);
		
		String code = "a001";
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		controller.commandHandler(model, code);
		view.rendering(model);
		
		SampleService service = childContainer1.getBean(SampleService.class);
		System.err.println(service);
		
//		SampleController controller2 = parentContainer.getBean(SampleController.class);
//		System.err.println(controller2); // 안되는 코드
		
		DBInfoVO infoVO1 = childContainer2.getBean("infoVO1", DBInfoVO.class);
		System.err.println(infoVO1.getService());
		
		childContainer1.getBean("infoVO1", DBInfoVO.class);
	}
}
