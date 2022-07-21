package kr.or.ddit.autodi;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.controller.SampleController;
import kr.or.ddit.sample.view.SampleView;

public class AutoDITestEntryPoint {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext parent =
				new ClassPathXmlApplicationContext("kr/or/ddit/autoDI/conf/parent-context.xml", "kr/or/ddit/autoDI/conf/ParentAop-Context.xml");
		ConfigurableApplicationContext child =
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/autoDI/conf/child-context.xml"}, parent);
		
		SampleController controller = child.getBean(SampleController.class);
		SampleView view = child.getBean(SampleView.class);
		
		String code = "a001";
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		controller.commandHandler(model, code);
		view.rendering(model);
		
	}
	
}
