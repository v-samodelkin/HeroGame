package Actions;
// +
import java.util.HashMap;
import java.util.Map;

public class BoolMapper {
	public static final Map<Boolean, Integer> BoolMapper;	
	public static final Map<Integer, Boolean> IntMapper;	
	
	static {
		BoolMapper = new HashMap<Boolean, Integer>();
		BoolMapper.put(false, 0);
		BoolMapper.put(true, 1);
		
		IntMapper = new HashMap<Integer, Boolean>();
		IntMapper.put(0, false);
		IntMapper.put(1, true);
	}
	
	public static int Get(boolean value) {
		return BoolMapper.get(value);
	}
}
