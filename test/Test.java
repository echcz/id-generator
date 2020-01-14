import cn.echcz.IdGenerator;

import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.JANUARY, 1);
        long oldId = 0;
        IdGenerator generator = new IdGenerator(calendar.getTimeInMillis(), 1L);
        for (int i = 0; i < 10; i++) {
            long id = generator.generate(2L);
            System.out.printf("bitSet: %s\n", Long.toBinaryString(id));
            System.out.printf("value: %d\n", id);
            System.out.println("-------------------------");
            assert oldId < id;
            oldId = id;
        }
    }
}
