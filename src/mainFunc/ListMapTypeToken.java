package mainFunc;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ListMapTypeToken extends TypeToken<List<Map<String, Object>> > {
    // 기본 생성자
    public ListMapTypeToken() {
        super();
    }

    
}
