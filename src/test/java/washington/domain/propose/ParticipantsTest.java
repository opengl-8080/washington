package washington.domain.propose;

import org.junit.Test;
import washington.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static washington.TestUtil.*;

public class ParticipantsTest {

    private static final Member foo = member("foo");
    private static final Member bar = member("bar");

    private Participants participants = new Participants();

    @Test
    public void 空かどうか確認できる_空でない場合() throws Exception {
        // setup
        participants.add(foo);

        // exercise, verify
        assertThat(participants.isEmpty()).isFalse();
    }

    @Test
    public void 空かどうか確認できる_空の場合() throws Exception {
        // exercise, verify
        assertThat(participants.isEmpty()).isTrue();
    }

    @Test
    public void インスタンス生成直後は人数0() throws Exception {
        // exercise, verify
        assertThat(participants.count()).isEqualTo(0);
    }

    @Test
    public void メンバーを追加したら人数が増える() throws Exception {
        // exercise
        participants.add(foo);

        // verify
        assertThat(participants.count()).isEqualTo(1);
    }

    @Test
    public void メンバーを指定して削除できる() throws Exception {
        // setup
        participants.add(foo);
        participants.add(bar);

        // exercise
        participants.remove(foo);

        // verify
        assertThat(participants.count()).isEqualTo(1);
    }

    @Test
    public void 指定したメンバーが含まれているか確認できる_含まれている場合() throws Exception {
        // setup
        participants.add(foo);
        participants.add(bar);

        // exercise, verify
        assertThat(participants.contains(foo)).isTrue();
    }

    @Test
    public void 指定したメンバーが含まれているか確認できる_含まれていない場合() throws Exception {
        // setup
        participants.add(foo);

        // exercise, verify
        assertThat(participants.contains(bar)).isFalse();
    }

    @Test
    public void 人数で比較できる() throws Exception {
        // setup
        Participants a = new Participants();
        a.add(foo);

        Participants b = new Participants();
        b.add(foo);
        b.add(bar);

        Participants c = new Participants();
        c.add(foo);
        c.add(bar);

        // exercise, verify
        assertThat(a.compareTo(b)).as("few compareTo large").isLessThan(0);
        assertThat(b.compareTo(a)).as("large compareTo few").isGreaterThan(0);
        assertThat(b.compareTo(c)).as("equally").isEqualTo(0);
    }

    //
}