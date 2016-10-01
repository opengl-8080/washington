package washington.domain.propose;

import org.junit.Test;
import washington.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static washington.TestUtil.*;

public class ParticipantsTest {

    private Participants participants = new Participants();

    @Test
    public void 各時間帯の参加人数は0で初期化されていること() throws Exception {
        // exercise, verify
        assertThat(participants.count(Time.MORNING)).as("午前").isEqualTo(0);
        assertThat(participants.count(Time.AFTERNOON1)).as("午後１").isEqualTo(0);
        assertThat(participants.count(Time.AFTERNOON2)).as("午後２").isEqualTo(0);
        assertThat(participants.count(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを追加できる() throws Exception {
        // setup
        Member a = member("a");

        // exercise
        participants.add(Time.MORNING, a);
        participants.add(Time.AFTERNOON1, a);
        participants.add(Time.AFTERNOON2, a);

        // verify
        assertThat(participants.count(Time.MORNING)).as("午前").isEqualTo(1);
        assertThat(participants.count(Time.AFTERNOON1)).as("午後１").isEqualTo(1);
        assertThat(participants.count(Time.AFTERNOON2)).as("午後２").isEqualTo(1);
        assertThat(participants.count(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを削除できる() throws Exception {
        // setup
        Member a = member("a");
        participants.add(Time.MORNING, a);
        participants.add(Time.AFTERNOON1, a);

        // exercise
        participants.remove(Time.AFTERNOON1, a);

        // verify
        assertThat(participants.count(Time.MORNING)).as("午前").isEqualTo(1);
        assertThat(participants.count(Time.AFTERNOON1)).as("午後１").isEqualTo(0);
        assertThat(participants.count(Time.AFTERNOON2)).as("午後２").isEqualTo(0);
        assertThat(participants.count(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 最も参加人数の多い時間帯を取得できる() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");

        participants.add(Time.MORNING, a);

        participants.add(Time.AFTERNOON1, a);
        participants.add(Time.AFTERNOON1, b);

        participants.add(Time.AFTERNOON2, a);

        participants.add(Time.AFTERNOON3, b);

        // exercise
        Optional<Time> time = participants.getMostManyMemberTime();

        // verify
        assertThat(time).hasValue(Time.AFTERNOON1);
    }

    @Test
    public void 全時間帯の参加者が0人の場合は空のOptionalが返される() throws Exception {
        // exercise
        Optional<Time> time = participants.getMostManyMemberTime();

        // verify
        assertThat(time).isEmpty();
    }

    @Test
    public void 最大参加人数を取得できる() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");

        participants.add(Time.MORNING, a);

        participants.add(Time.AFTERNOON1, a);
        participants.add(Time.AFTERNOON1, b);

        participants.add(Time.AFTERNOON2, a);

        participants.add(Time.AFTERNOON3, b);

        // exercise
        int actual = participants.getMaxMemberCount();

        // verify
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void 時間帯を指定してメンバーが含まれることを確認できる_含まれる場合() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");

        participants.add(Time.MORNING, a);
        participants.add(Time.AFTERNOON1, b);

        // exercise
        boolean actual = participants.contains(Time.MORNING, a);

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void 時間帯を指定してメンバーが含まれることを確認できる_含まれない場合() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");

        participants.add(Time.MORNING, a);
        participants.add(Time.AFTERNOON1, b);

        // exercise
        boolean actual = participants.contains(Time.AFTERNOON1, a);

        // verify
        assertThat(actual).isFalse();
    }
}