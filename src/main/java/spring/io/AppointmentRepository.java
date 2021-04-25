package spring.io;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	public default void updateHour(int hour, Integer id) {

	}

	public default void updateDay(int day, Integer id) {

	}

	public default void updateMonth(int month, Integer id) {

	}

	public default void updateYear(int year, Integer id) {

	}

	public default void editAppointment(Appointment appointment, Integer id) {

	}
}
