package washington.view.bean;

import lombok.Data;
import org.eclipse.collections.impl.factory.Lists;
import washington.domain.member.LoginUser;
import washington.domain.propose.Date;
import washington.domain.propose.ProposedDate;
import washington.domain.propose.ProposedDateRepository;
import washington.domain.propose.ProposedDates;
import washington.domain.propose.Time;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named
@Data
public class ProposedDateBean implements Serializable {
    private String date;
    private String formattedDate;
    private List<TimeLine> timeTable = Lists.mutable.empty();

    @Inject
    private LoginUser loginUser;
    @Inject
    private ProposedDateRepository repository;

    public void init() {
        LocalDate date = LocalDate.parse(this.date, DateTimeFormatter.ISO_DATE);
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy年M月d日"));

        ProposedDate proposedDate = this.repository.find(new Date(date));

        for (Time time : Arrays.asList(Time.MORNING, Time.AFTERNOON1, Time.AFTERNOON2, Time.AFTERNOON3)) {
            TimeLine timeLine = new TimeLine();
            timeLine.setTime(time.format("$name($begin～$end時)"));
            timeLine.setMemberCount(proposedDate.countMember(time));
            timeLine.setParticipate(proposedDate.contains(time, this.loginUser.getMember()));

            this.timeTable.add(timeLine);
        }
    }

    @Data
    public static class TimeLine {
        private String time;
        private int memberCount;
        private boolean participate;
    }
}
