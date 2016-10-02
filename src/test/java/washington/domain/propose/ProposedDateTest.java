package washington.domain.propose;

import org.junit.Test;
import washington.domain.member.Member;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static washington.TestUtil.*;


public class ProposedDateTest {
    private static final Member foo = member("foo");
    private static final Member bar = member("bar");

    private Date date = new Date(LocalDate.now());
    private ProposedDate proposedDate = new ProposedDate(date);

    @Test
    public void 各時間帯の参加人数は0で初期化されていること() throws Exception {
        // exercise, verify
        assertThat(proposedDate.countMember(Time.MORNING)).as("午前").isEqualTo(0);
        assertThat(proposedDate.countMember(Time.AFTERNOON1)).as("午後１").isEqualTo(0);
        assertThat(proposedDate.countMember(Time.AFTERNOON2)).as("午後２").isEqualTo(0);
        assertThat(proposedDate.countMember(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを追加できる() throws Exception {
        // exercise
        proposedDate.add(Time.MORNING, foo);
        proposedDate.add(Time.AFTERNOON1, foo);
        proposedDate.add(Time.AFTERNOON2, foo);

        // verify
        assertThat(proposedDate.countMember(Time.MORNING)).as("午前").isEqualTo(1);
        assertThat(proposedDate.countMember(Time.AFTERNOON1)).as("午後１").isEqualTo(1);
        assertThat(proposedDate.countMember(Time.AFTERNOON2)).as("午後２").isEqualTo(1);
        assertThat(proposedDate.countMember(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを削除できる() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, foo);
        proposedDate.add(Time.AFTERNOON1, foo);

        // exercise
        proposedDate.remove(Time.AFTERNOON1, foo);

        // verify
        assertThat(proposedDate.countMember(Time.MORNING)).as("午前").isEqualTo(1);
        assertThat(proposedDate.countMember(Time.AFTERNOON1)).as("午後１").isEqualTo(0);
        assertThat(proposedDate.countMember(Time.AFTERNOON2)).as("午後２").isEqualTo(0);
        assertThat(proposedDate.countMember(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 最も参加人数の多い時間帯を取得できる() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, foo);

        proposedDate.add(Time.AFTERNOON1, foo);
        proposedDate.add(Time.AFTERNOON1, bar);

        proposedDate.add(Time.AFTERNOON2, foo);

        proposedDate.add(Time.AFTERNOON3, bar);

        // exercise
        Optional<Time> time = proposedDate.getMostManyMemberTime();

        // verify
        assertThat(time).hasValue(Time.AFTERNOON1);
    }

    @Test
    public void 全時間帯の参加者が0人の場合は空のOptionalが返される() throws Exception {
        // exercise
        Optional<Time> time = proposedDate.getMostManyMemberTime();

        // verify
        assertThat(time).isEmpty();
    }

    @Test
    public void 最大参加人数を取得できる() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, foo);

        proposedDate.add(Time.AFTERNOON1, foo);
        proposedDate.add(Time.AFTERNOON1, bar);

        proposedDate.add(Time.AFTERNOON2, foo);

        proposedDate.add(Time.AFTERNOON3, bar);

        // exercise
        int actual = proposedDate.getMaxMemberCount();

        // verify
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void 時間帯を指定してメンバーが含まれることを確認できる_含まれる場合() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, foo);
        proposedDate.add(Time.AFTERNOON1, bar);

        // exercise
        boolean actual = proposedDate.contains(Time.MORNING, foo);

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void 時間帯を指定してメンバーが含まれることを確認できる_含まれない場合() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, foo);
        proposedDate.add(Time.AFTERNOON1, bar);

        // exercise
        boolean actual = proposedDate.contains(Time.AFTERNOON1, foo);

        // verify
        assertThat(actual).isFalse();
    }
}