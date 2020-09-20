import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        Long.parseLong("100");
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println(map.get("hello")==null?"get Null": map.get("hello"));
    }
}
