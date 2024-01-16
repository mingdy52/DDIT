package kr.or.ddit.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDIVO {

	private String[] array;
	private List<?> list;
	private Set<String> set;
	private Map<String, ?> map;
	private Properties props;
	
}
