package washington.domain.propose;

import org.junit.Test;
import washington.domain.member.LoginId;
import washington.domain.member.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ProposedDateTest {

    private Date date = new Date(LocalDate.now());
    private ProposedDate proposedDate = new ProposedDate(date);

    @Test
    public void 生成直後は各時間帯ごとに参加者ゼロで初期化されていること() throws Exception {
        // exercise
        ProposedDate proposedDate = new ProposedDate(date);

        // verify
        Map<Time, Participants> expected = new HashMap<>();
        expected.put(Time.MORNING, new Participants());
        expected.put(Time.AFTERNOON1, new Participants());
        expected.put(Time.AFTERNOON2, new Participants());
        expected.put(Time.AFTERNOON3, new Participants());

        assertThat(proposedDate.getDate()).as("日付").isEqualTo(date);
        assertThat(proposedDate.getParticipantsMap()).as("時間帯別参加者")
                .containsExactly(expected.entrySet().toArray(new Map.Entry[0]));
    }

    @Test
    public void 時間帯ごとの参加人数を確認できる_生成直後は全て０() throws Exception {
        // exercise, verify
        assertThat(proposedDate.countParticipants(Time.MORNING)).as("午前").isEqualTo(0);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON1)).as("午後１").isEqualTo(0);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON2)).as("午後２").isEqualTo(0);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを追加できる() throws Exception {
        // exercise
        proposedDate.add(Time.AFTERNOON1, member("a"));

        proposedDate.add(Time.AFTERNOON2, member("b"));
        proposedDate.add(Time.AFTERNOON2, member("c"));

        // verify
        assertThat(proposedDate.countParticipants(Time.MORNING)).as("午前").isEqualTo(0);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON1)).as("午後１").isEqualTo(1);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON2)).as("午後２").isEqualTo(2);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
    }

    @Test
    public void 時間帯を指定してメンバーを削除できる() throws Exception {
        // setup
        proposedDate.add(Time.AFTERNOON1, member("a"));

        Member b = member("b");
        proposedDate.add(Time.AFTERNOON2, b);
        proposedDate.add(Time.AFTERNOON2, member("c"));

        // exercise
        proposedDate.remove(Time.AFTERNOON2, b);

        // verify
        assertThat(proposedDate.countParticipants(Time.MORNING)).as("午前").isEqualTo(0);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON1)).as("午後１").isEqualTo(1);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON2)).as("午後２").isEqualTo(1);
        assertThat(proposedDate.countParticipants(Time.AFTERNOON3)).as("午後３").isEqualTo(0);
        assertThat(proposedDate.contains(Time.AFTERNOON2, b)).isFalse();
    }

    @Test
    public void 最も参加人数の多い時間帯を取得できる() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, member("a"));

        proposedDate.add(Time.AFTERNOON2, member("b"));
        proposedDate.add(Time.AFTERNOON2, member("c"));
        proposedDate.add(Time.AFTERNOON2, member("d"));

        proposedDate.add(Time.AFTERNOON3, member("e"));
        proposedDate.add(Time.AFTERNOON3, member("f"));
        proposedDate.add(Time.AFTERNOON3, member("g"));

        proposedDate.add(Time.AFTERNOON1, member("h"));
        proposedDate.add(Time.AFTERNOON1, member("i"));
        proposedDate.add(Time.AFTERNOON1, member("j"));
        proposedDate.add(Time.AFTERNOON1, member("k"));

        // exercise
        Time time = proposedDate.getMostManyParticipantsTime();

        // verify
        assertThat(time).isEqualTo(Time.AFTERNOON1);
    }

    @Test
    public void トップ２つの参加者数が同じ場合は_いずれかの時間が返される() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, member("a"));

        proposedDate.add(Time.AFTERNOON2, member("b"));
        proposedDate.add(Time.AFTERNOON2, member("c"));
        proposedDate.add(Time.AFTERNOON2, member("d"));

        proposedDate.add(Time.AFTERNOON3, member("e"));
        proposedDate.add(Time.AFTERNOON3, member("f"));
        proposedDate.add(Time.AFTERNOON3, member("g"));

        proposedDate.add(Time.AFTERNOON1, member("h"));
        proposedDate.add(Time.AFTERNOON1, member("i"));

        // exercise
        Time time = proposedDate.getMostManyParticipantsTime();

        // verify
        assertThat(time).isIn(Time.AFTERNOON2, Time.AFTERNOON3);
    }

    @Test
    public void 参加人数の最大を取得する() throws Exception {
        // setup
        proposedDate.add(Time.MORNING, member("a"));

        proposedDate.add(Time.AFTERNOON2, member("b"));
        proposedDate.add(Time.AFTERNOON2, member("c"));
        proposedDate.add(Time.AFTERNOON2, member("d"));

        proposedDate.add(Time.AFTERNOON3, member("e"));
        proposedDate.add(Time.AFTERNOON3, member("f"));

        proposedDate.add(Time.AFTERNOON1, member("h"));
        proposedDate.add(Time.AFTERNOON1, member("i"));
        proposedDate.add(Time.AFTERNOON1, member("j"));
        proposedDate.add(Time.AFTERNOON1, member("k"));

        // exercise
        int count = proposedDate.getMostManyParticipantsCount();

        // verify
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void 時間帯とメンバーを指定して_既にその時間帯に参加申請しているか確認できる_参加していない場合() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");
        proposedDate.add(Time.MORNING, a);
        proposedDate.add(Time.MORNING, b);

        // exercise
        boolean actual = proposedDate.contains(Time.AFTERNOON1, a);

        // verify
        assertThat(actual).isFalse();
    }

    @Test
    public void 時間帯とメンバーを指定して_既にその時間帯に参加申請しているか確認できる_参加している場合() throws Exception {
        // setup
        Member a = member("a");
        Member b = member("b");
        proposedDate.add(Time.MORNING, a);
        proposedDate.add(Time.MORNING, b);

        // exercise
        boolean actual = proposedDate.contains(Time.MORNING, a);

        // verify
        assertThat(actual).isTrue();
    }

    private Member member(String loginId) {
        return new Member(new LoginId(loginId));
    }
}