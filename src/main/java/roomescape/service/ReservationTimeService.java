package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.dao.ReservationTimeDao;
import roomescape.dao.dto.AvailableReservationTimeResponse;
import roomescape.domain.ReservationDate;
import roomescape.domain.ReservationTime;
import roomescape.exception.ExistReservationInReservationTimeException;
import roomescape.exception.NotExistReservationTimeException;
import roomescape.exception.ReservationTimeAlreadyExistsException;
import roomescape.service.dto.input.AvailableReservationTimeInput;
import roomescape.service.dto.input.ReservationTimeInput;
import roomescape.service.dto.output.ReservationTimeOutput;

@Service
public class ReservationTimeService {

    private final ReservationTimeDao reservationTimeDao;
    private final ReservationDao reservationDao;

    public ReservationTimeService(ReservationTimeDao reservationTimeDao, ReservationDao reservationDao) {
        this.reservationTimeDao = reservationTimeDao;
        this.reservationDao = reservationDao;
    }

    public ReservationTimeOutput createReservationTime(ReservationTimeInput input) {
        ReservationTime reservationTime = input.toReservationTime();

        if (reservationTimeDao.isExistByStartAt(reservationTime.getStartAtAsString())) {
            throw new ReservationTimeAlreadyExistsException(
                    String.format("%s에 해당하는 시간이 있습니다.", reservationTime.getStartAtAsString()));
        }

        ReservationTime savedReservationTime = reservationTimeDao.create(reservationTime);
        return ReservationTimeOutput.toOutput(savedReservationTime);
    }

    public List<ReservationTimeOutput> getAllReservationTimes() {
        List<ReservationTime> reservationTimes = reservationTimeDao.getAll();
        return ReservationTimeOutput.toOutputs(reservationTimes);
    }

    public List<AvailableReservationTimeResponse> getAvailableTimes(AvailableReservationTimeInput input) {
        return reservationTimeDao.getAvailable(ReservationDate.from(input.date()), input.themeId());
    }

    public void deleteReservationTime(long id) {
        ReservationTime reservationTime = reservationTimeDao.find(id)
                .orElseThrow(() -> new NotExistReservationTimeException(String.format("%d는 없는 id 입니다.", id)));

        if (reservationDao.isExistByTimeId(id)) {
            throw new ExistReservationInReservationTimeException(String.format("%d에 해당하는 예약이 있습니다.", id));
        }

        reservationTimeDao.delete(reservationTime.getId());
    }
}
