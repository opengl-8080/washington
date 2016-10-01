package washington.domain.propose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TimeTest {

    @Test
    public void フォーマット指定して開始時間と終了時間を取得できる() throws Exception {
        // setup
        String template = "$name : $begin - $end";

        // verify
        assertThat(Time.MORNING.format(template)).as("午前").isEqualTo("午前 : 10 - 12");
        assertThat(Time.AFTERNOON1.format(template)).as("午後１").isEqualTo("午後１ : 13 - 15");
        assertThat(Time.AFTERNOON2.format(template)).as("午後２").isEqualTo("午後２ : 15 - 17");
        assertThat(Time.AFTERNOON3.format(template)).as("午後３").isEqualTo("午後３ : 17 - 19");
    }
}